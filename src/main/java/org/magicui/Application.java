/*
 * Magic UI
 * Copyright (C) 2006  Filipe Tavares, Belmiro Sotto-Mayor
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package org.magicui;

import java.awt.Component;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;

import org.magicui.config.XMLAppConfig;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.View;
import org.magicui.ui.awt.AwtFactory;
import org.magicui.ui.factory.ComponentFactory;
import org.magicui.ui.swing.SwingFactory;
import org.magicui.ui.web.WebFactory;
import org.magicui.ui.web.WebTag;
import org.magicui.xml.XMLParser;

/**
 * Application is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class Application<T> {
	/**
	 * The config <code>XMLAppConfig</code> field.
	 */
	private static AppConfig config;
	/**
	 * The resources <code>ResourceBundle</code> field.
	 */
	private final ResourceBundle resources;
	/**
	 * The mainView <code>View</code> field.
	 */
	private View<? extends T> mainView;
	
	/**
	 * The factory <code>ComponentFactory<T></code> field.
	 */
	private final ComponentFactory<T> factory;
	
	/**
	 * The actions <code>Map<String,Action></code> field.
	 */
	private final Map<String, Action> actions = new Hashtable<String, Action>();
	
	/**
	 * The app <code>Application<?></code> field.
	 */
	private static Application<?> app;
	
	/**
	 * @throws MagicUIException 
	 * 
	 */
	public Application() throws MagicUIException {
		this.resources = ResourceBundle.getBundle(this.config.getMessageResources());
        this.factory = (ComponentFactory<T>) createFactory();
        app = this;
	}

	/**
	 * The getter method for the mainView property.
	 * @return the mainView
	 */
	public final View<? extends T> getMainView() {
		return this.mainView;
	}

	/**
	 * The setter method for the mainView property.
	 * @param mainView the mainView to set
	 */
	public final void setMainView(View<T> mainView) {
		this.mainView = mainView;
	}
	
	/**
	 * Register an action.
	 * @param actionId The action's id
	 * @param action The action implementation
	 */
	public void registerAction(String actionId, Action action) {
		this.actions.put(actionId, action);
	}
	
	/**
	 * The getter method for the factory property.
	 * @return the factory
	 */
	public ComponentFactory<T> getFactory() {
		return this.factory;
	}
	
	/**
	 * Get a i18n message.
	 * @param key The message's key
	 * @return The message
	 */
	public String getMessage(String key) {
		try {
			return this.resources.getString(key);
		} catch(MissingResourceException e) {
			return "{" + key + "}";
		}
	}
	
	/**
	 * Get an action's implementation by it's id.
	 * @param id The action's id
	 * @return The action
	 */
	public Action getAction(String id) {
		return this.actions.get(id);
	}
	
	/**
	 * Start the application.
	 * @param vars The main template vars
	 * @return The window
	 * @throws MagicUIException 
	 */
	public Object start(Object... vars) throws MagicUIException {
        this.mainView = loadStandaloneWidget(this.config.getMainWidget(), vars);
		return showWidget(this.config.getName(), this.mainView);
	}
    
    /**
     * Load the component that starts the application.
     * @param vars The main template vars
     * @return A toolkit/format specific component
     * @throws MagicUIException 
     */
    public T loadMainComponent(Object... vars) throws MagicUIException {
        return loadStandaloneWidget(this.config.getMainWidget(), vars).getComponent();
    }
	
	/**
	 * Load a widget.
	 * @param widget The widget's name
	 * @param vars The widget's arguments
	 * @return The widget's main view
	 * @throws MagicUIException 
	 */
	private View<? extends T> internalLoadWidget(final String widget,
            final boolean standalone, Object... vars) throws MagicUIException {
		View<? extends T> widgetMainView = null;
		final Collection<View<? extends T>> widgetViews =
			XMLParser.load(this, widget, standalone, vars);
		for (View<? extends T> view : widgetViews) {
			if (widgetMainView == null) {
				widgetMainView = view;
			} else {
                widgetMainView.registerView(view.getId(), view);
			}
		}
		return widgetMainView;
	}
    
    /**
     * Load a widget.
     * @param widget The widget's name
     * @param vars The widget's arguments
     * @return The widget's main view
     * @throws MagicUIException 
     */
    public View<? extends T> loadStandaloneWidget(final String widget,
            Object... vars) throws MagicUIException {
        return internalLoadWidget(widget, true, vars);
    }
    
    /**
     * Load a widget.
     * @param widget The widget's name
     * @param vars The widget's arguments
     * @return The widget's main view
     * @throws MagicUIException 
     */
    public View<? extends T> loadWidget(final String widget,
            Object... vars) throws MagicUIException {
        return internalLoadWidget(widget, false, vars);
    }
    
    private Object showWidget(final String title,
            final View<?extends T> widgetMainView) {
        return this.factory.createWindow(title, widgetMainView);
    }
	
	/**
	 * Access the application singleton instance.
	 * @return The application singleton
	 */
	public static Application<?> getInstance() {
		return app;
	}
    
    public static Application<?> create() throws MagicUIException {
        config = new XMLAppConfig();
        final String type = config.getToolkit();
        if (type.equals(Globals.TYPE_SWING)) {
            return new Application<JComponent>();
        } else if (type.equals(Globals.TYPE_AWT)) {
            return new Application<Component>();
        } else if (type.equals(Globals.TYPE_WEB)) {
            return new Application<WebTag>();
        }
        return null;
    }
    
    /**
     * @return A component factory
     */
    private ComponentFactory<?> createFactory() {
        final String type = config.getToolkit();
        if (type.equals(Globals.TYPE_SWING)) {
            return new SwingFactory();
        } else if (type.equals(Globals.TYPE_AWT)) {
            return new AwtFactory();
        } else if (type.equals(Globals.TYPE_WEB)) {
            return new WebFactory();
        }
        return null;
    }
	
}

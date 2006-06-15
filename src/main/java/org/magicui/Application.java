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

import java.util.Hashtable;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.View;
import org.magicui.ui.factory.ComponentFactory;
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
	 * The config <code>AppConfig</code> field.
	 */
	private final AppConfig config;
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
	protected ComponentFactory<T> factory;
	
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
		this.config = new AppConfig();
		this.resources = ResourceBundle.getBundle(this.config.getMessageResources());
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
	 * @param actionId
	 * @param action
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
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		try {
			return this.resources.getString(key);
		} catch(MissingResourceException e) {
			return key;
		}
	}
	
	/**
	 * @param id
	 * @return The action
	 */
	public Action getAction(String id) {
		return this.actions.get(id);
	}
	
	/**
	 * Start the application.
	 * @param vars The main template vars
	 * @throws MagicUIException 
	 */
	public void start(Object... vars) throws MagicUIException {
		this.mainView = XMLParser.load(this, this.config.getMainWidget(), vars);
		this.factory.createWindow(this.config.getName(), this.mainView);
	}
	
	/**
	 * @return
	 * @throws MagicUIException
	 */
	public static Application<?> create() throws MagicUIException {
		if (app == null) {
			app = new Application<Object>();
		}
		return app;
	}
	
	/**
	 * @return
	 */
	public static Application<?> getInstance() {
		return app;
	}
	
}

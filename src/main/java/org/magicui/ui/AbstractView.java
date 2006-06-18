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
package org.magicui.ui;

import java.util.Collection;
import java.util.Hashtable;

import org.magicui.State;

/**
 * AbstractView is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 */
public abstract class AbstractView<C> extends AbstractComponent<C> implements View<C> {
	private Collection<ActionItem> top;
	private Collection<ActionItem> bottom;
	private Collection<ActionItem> left;
	private Collection<ActionItem> right;
	private Collection<Object> menu;
	
	private State state = null;
	
	/**
	 * The components <code>Hashtable</code> field.
	 */
	private final Hashtable<String, Component<?>> components =
		new Hashtable<String, Component<?>>();
    
    /**
     * The auxViews <code>Hashtable</code> field.
     */
    private final Hashtable<String, View<?>> auxViews =
        new Hashtable<String, View<?>>();
	

	/**
	 * @see org.magicui.ui.View#add(org.magicui.ui.Component, int, int, int, int)
	 */
	public final void add(Component<?> child, int x, int y, int xWeight, int yWeight) {
		child.setParent(this);
		this.components.put(child.getId(), child);
		addComponent(child, x, y, xWeight, yWeight);
	}

	/**
	 * 
	 */
	protected abstract void addComponent(Component<?> child, int x, int y, int xWeight, int yWeight);
	
	/**
	 * @see org.magicui.ui.View#getComponentById(java.lang.String)
	 */
	public Component<?> getComponentById(String id) {
        final int index = id.indexOf('.');
        if (index == 0) {
            return getParent().getComponentById(id.substring(index+1));
        } else if (index > 0) {
            return ((View<?>) this.components.get(id.substring(0, index)))
                .getComponentById(id.substring(index+1));
        } else {
            return this.components.get(id);
        }
	}
	
	/**
	 * @see org.magicui.ui.View#setToolbars(java.util.Collection, java.util.Collection, java.util.Collection, java.util.Collection)
	 */
	public void setToolbars(Collection<ActionItem> top, Collection<ActionItem> bottom, Collection<ActionItem> left, Collection<ActionItem> right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * @see org.magicui.ui.View#setMenus(java.util.Collection)
	 */
	public void setMenus(Collection<Object> menu) {
		this.menu = menu;
	}

	/**
	 * The getter method for the bottom property.
	 * @return the bottom
	 */
	public final Collection<ActionItem> getBottom() {
		return this.bottom;
	}

	/**
	 * The getter method for the left property.
	 * @return the left
	 */
	public final Collection<ActionItem> getLeft() {
		return this.left;
	}

	/**
	 * The getter method for the menu property.
	 * @return the menu
	 */
	public final Collection<Object> getMenus() {
		return this.menu;
	}

	/**
	 * The getter method for the right property.
	 * @return the right
	 */
	public final Collection<ActionItem> getRight() {
		return this.right;
	}

	/**
	 * The getter method for the top property.
	 * @return the top
	 */
	public final Collection<ActionItem> getTop() {
		return this.top;
	}
	
	/**
	 * @see org.magicui.ui.View#getState()
	 */
	public State getState() {
		if (this.state == null) {
			this.state = new ViewState(this);
		}
		return this.state;
	}
    
    /**
     * @see org.magicui.ui.View#registerView(java.lang.String, org.magicui.ui.View)
     */
    public void registerView(String id, View<?> auxView) {
        this.auxViews.put(id, auxView);
    }
    
    /**
     * @see org.magicui.ui.View#getAuxiliarView(java.lang.String)
     */
    public View<?> getAuxiliarView(String id) {
        return this.auxViews.get(id);
    }

}

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

/**
 * AbstractContainer is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 */
public abstract class AbstractContainer<C> extends AbstractComponent<C> implements View<C> {
	Collection<ActionItem> top;
	Collection<ActionItem> bottom;
	Collection<ActionItem> left;
	Collection<ActionItem> right;
	Collection<ActionItem> menu;

	/**
	 * @see org.magicui.ui.View#add(org.magicui.ui.Component, int, int, int, int)
	 */
	public final void add(Component component, int x, int y, int xWeight, int yWeight) {
		component.setParent(this);
		addComponent(component, x, y, xWeight, yWeight);
	}

	/**
	 * 
	 */
	protected abstract void addComponent(Component component, int x, int y, int xWeight, int yWeight);
	
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
	public void setMenus(Collection<ActionItem> menu) {
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
	public final Collection<ActionItem> getMenu() {
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

}

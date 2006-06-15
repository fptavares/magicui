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

import org.magicui.State;

/**
 * View is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 */
public interface View<C> extends Component<C> {

	/**
	 * @param component
	 * @param xCounter
	 * @param yCounter
	 * @param xWeight
	 * @param yWeight
	 */
	void add(Component<?> component, int xCounter, int yCounter, int xWeight, int yWeight);
	
	/**
	 * Get a component by it's id.
	 * @param id The compnent's id
	 * @return The component
	 */
	public Component<?> getComponentById(final String id);

	/**
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	void setToolbars(Collection<ActionItem> top, Collection<ActionItem> bottom, Collection<ActionItem> left, Collection<ActionItem> right);

	/**
	 * @param menus
	 */
	void setMenus(Collection<Object> menus);
	
	/**
	 * The getter method for the bottom property.
	 * @return the bottom toolbar items
	 */
	public Collection<ActionItem> getBottom();

	/**
	 * The getter method for the left property.
	 * @return the left toolbar items
	 */
	public Collection<ActionItem> getLeft();

	/**
	 * The getter method for the menus property.
	 * @return the menus
	 */
	public Collection<Object> getMenus();

	/**
	 * The getter method for the right property.
	 * @return the right toolbar items
	 */
	public Collection<ActionItem> getRight();

	/**
	 * The getter method for the top property.
	 * @return the top toolbar items
	 */
	public Collection<ActionItem> getTop();
	
	/**
	 * Return a <code>State</code> instance for this view.
	 * @return The state instance
	 */
	public State getState();

}

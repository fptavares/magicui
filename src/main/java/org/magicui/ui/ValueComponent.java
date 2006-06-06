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

import org.magicui.Action;

/**
 * ValueComponent is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 * @param <T> The type of the content
 */
public interface ValueComponent<C, T> extends Component<C> {

	/**
	 * The setter method for the value property.
	 * @param value The value
	 */
	public void setValue(T value);
	/**
	 * The getter method for the value property.
	 * @return The value
	 */
	public T getValue();

	/**
	 * @param type
	 * @param action
	 */
	public void addListener(String type, Action action);
	
}

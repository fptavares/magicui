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

/**
 * Component is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 * @param <T> The type of the content
 */
public interface Component<C,T> {
	/**
	 * Create the component.
	 * @return The component
	 */
	public C createComponent();
	
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
	 * @return The rendered component
	 */
	public C render();
	
	/**
	 * The getter method for the height property.
	 * @return the height
	 */
	public String getHeight();

	/**
	 * The setter method for the height property.
	 * @param height the height to set
	 */
	public void setHeight(String height);

	/**
	 * The getter method for the id property.
	 * @return the id
	 */
	public String getId();

	/**
	 * The setter method for the id property.
	 * @param id the id to set
	 */
	public void setId(String id);

	/**
	 * The getter method for the width property.
	 * @return the width
	 */
	public String getWidth();

	/**
	 * The setter method for the width property.
	 * @param width the width to set
	 */
	public void setWidth(String width);

	/**
	 * The getter method for the x property.
	 * @return the x
	 */
	public int getX();

	/**
	 * The setter method for the x property.
	 * @param x the x to set
	 */
	public void setX(int x);

	/**
	 * The getter method for the y property.
	 * @return the y
	 */
	public int getY();

	/**
	 * The setter method for the y property.
	 * @param y the y to set
	 */
	public void setY(int y);
}

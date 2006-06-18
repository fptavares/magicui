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
 */
public interface Component<C> {
	/**
	 * The EVENT_CLICK <code>String</code> field.
	 */
	public static final String EVENT_CLICK = "click";
	/**
	 * The EVENT_HOVER <code>Object</code> field.
	 */
	public static final Object EVENT_HOVER = "hover";
	
	/**
	 * Create the component.
	 * @return The component
	 */
	public C createComponent();
	
	/**
	 * @return The rendered component
	 */
	public C getComponent();

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
	 * The setter method for the parent property.
	 * @param parent the parent to set
	 */
	public void setParent(View<?> parent);

    /**
     * The getter method for the parent property.
     * @return the parent to set
     */
    public View<?> getParent();
}

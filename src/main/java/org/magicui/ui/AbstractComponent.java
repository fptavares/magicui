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
 * AbstractComponent is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 */
public abstract class AbstractComponent<C> implements Component<C> {

	/**
	 * The parent <code>View</code> field.
	 */
	protected View parent;
	
	/**
	 * 
	 */
	public AbstractComponent() {
		this.component = createComponent();
	}
	
	/**
	 * The component <code>C</code> field.
	 */
	protected C component;
	
	/**
	 * The id <code>String</code> field.
	 */
	private String id = "";

	/**
	 * @see org.magicui.ui.Component#getId()
	 */
	public final String getId() {
		return this.id;
	}

	/**
	 * @see org.magicui.ui.Component#setId(java.lang.String)
	 */
	public final void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @see org.magicui.ui.Component#getComponent()
	 */
	public C getComponent() {
		return this.component;
	}
	
	/**
	 * The setter method for the parent property.
	 * @param parent the parent to set
	 */
	public void setParent(View parent) {
		this.parent = parent;
	}
	
	
}

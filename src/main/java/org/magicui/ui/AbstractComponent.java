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
 * @param <T> The type of the content
 */
public abstract class AbstractComponent<C,T> implements Component<C,T> {
	/**
	 * The component <code>C</code> field.
	 */
	protected C component;
	
	/**
	 * The id <code>String</code> field.
	 */
	private String id = "";
	/**
	 * The x <code>String</code> field.
	 */
	private int x = 0;
	/**
	 * The y <code>int</code> field.
	 */
	private int y = 0;
	/**
	 * The height <code>String</code> field.
	 */
	private String height = "";
	/**
	 * The width <code>String</code> field.
	 */
	private String width = "";
	
	/**
	 * @see org.magicui.ui.Component#render()
	 */
	public C render() {
		this.component = createComponent();
		return this.component;
	}

	/**
	 * @see org.magicui.ui.Component#getHeight()
	 */
	public final String getHeight() {
		return this.height;
	}

	/**
	 * @see org.magicui.ui.Component#setHeight(java.lang.String)
	 */
	public final void setHeight(String height) {
		this.height = height;
	}

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
	 * @see org.magicui.ui.Component#getWidth()
	 */
	public final String getWidth() {
		return this.width;
	}

	/**
	 * @see org.magicui.ui.Component#setWidth(java.lang.String)
	 */
	public final void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @see org.magicui.ui.Component#getX()
	 */
	public final int getX() {
		return this.x;
	}

	/**
	 * @see org.magicui.ui.Component#setX(int)
	 */
	public final void setX(int x) {
		this.x = x;
	}

	/**
	 * @see org.magicui.ui.Component#getY()
	 */
	public final int getY() {
		return this.y;
	}

	/**
	 * @see org.magicui.ui.Component#setY(int)
	 */
	public final void setY(int y) {
		this.y = y;
	}
	
	
}

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
package org.magicui.ui.awt;

import java.awt.Panel;

/**
 * AwtPlaceholder is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class AwtPlaceholder
		extends AbstractAwtComponent<Panel, AwtView> {
	private AwtView view;

	/**
	 * @see org.magicui.ui.ValueComponent#getValue()
	 */
	public AwtView getValue() {
		return this.view;
	}

	/**
	 * @see org.magicui.ui.ValueComponent#setValue(java.lang.Object)
	 */
	public void setValue(AwtView value) {
		this.view = value;
        this.component.removeAll();
		this.component.add(value.getComponent());
        this.component.getParent().validate();
	}

	/**
	 * @see org.magicui.ui.Component#createComponent()
	 */
	public Panel createComponent() {
		return new Panel();
	}

}
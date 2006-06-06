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
package org.magicui.ui.web;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.magicui.Action;
import org.magicui.ui.AbstractValueComponent;
import org.magicui.ui.swing.AbstractSwingComponent;

/**
 * WebLabel is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public final class WebLabel extends AbstractValueComponent<WebTag,String> {

	/**
	 * @see org.magicui.ui.Component#createComponent()
	 */
	public WebTag createComponent() {
		return new WebTag("", "");
	}

	/**
	 * @see org.magicui.ui.Component#getValue()
	 */
	public String getValue() {
		return this.component.getValue();
	}

	/**
	 * @see org.magicui.ui.Component#setValue(java.lang.Object)
	 */
	public void setValue(String value) {
		this.component.setValue(value);
	}

	/**
	 * @see org.magicui.ui.ValueComponent#addListener(java.lang.String, org.magicui.Action)
	 */
	public void addListener(String type, Action action) {
		if (type.equals(EVENT_CLICK)) {
			this.component.addEvent("onMouseClick", action);
		} else if (type.equals(EVENT_HOVER)) {
			
		} else {
			return;
		}
	}

}

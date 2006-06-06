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
 * ActionItem is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public final class ActionItem {
	private final String place;
	private final String text;
	private final Action action;
	private final String icon;
	/**
	 * @param text
	 * @param action
	 * @param place 
	 */
	public ActionItem(final String text, final Action action, final String place, final String icon) {
		this.text = text;
		this.action = action;
		this.place = place;
		this.icon = icon;
	}
	/**
	 * The getter method for the action property.
	 * @return the action
	 */
	public final Action getAction() {
		return this.action;
	}
	/**
	 * The getter method for the text property.
	 * @return the text
	 */
	public final String getText() {
		return this.text;
	}
	/**
	 * The getter method for the place property.
	 * @return the place
	 */
	public String getPlace() {
		return this.place;
	}
	/**
	 * The getter method for the icon property.
	 * @return the icon
	 */
	public String getIcon() {
		return this.icon;
	}
}

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
package org.magicui.ui.menu;

import java.util.HashMap;
import java.util.Map;

import org.magicui.ui.ActionItem;

/**
 * Menu is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class Menu extends ActionItem {
    /**
     * The items <code>Map<String,ActionItem></code> field.
     */
    final Map<String, ActionItem> items = new HashMap<String, ActionItem>();
    
	/**
	 * Creates a new <code>Menu</code> instance.
	 * @param text
	 * @param place
	 * @param icon
	 */
	public Menu(String text, String place, String icon) {
		super(text, null, place, icon);
	}
	
	/**
     * Adds an item to this menu.
	 * @param id
	 * @param item
	 */
	public void addItem(String id, ActionItem item) {
		this.items.put(id, item);
	}
}

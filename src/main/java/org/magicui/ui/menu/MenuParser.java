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

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.magicui.Application;
import org.magicui.Globals;
import org.magicui.ui.ActionItem;
import org.magicui.ui.View;
import org.magicui.ui.factory.ComponentFactory;

/**
 * MenuParser is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <T> The base type of all components
 */
public class MenuParser<T> {
	private ComponentFactory<T> factory;
	private Application<T> app;
	private View<? extends T> view;
	
	private final Map<String, T> menus = new TreeMap<String, T>();
	private final Collection<Object> baseMenus = new LinkedList<Object>();
	
	/**
	 * @param app
	 * @param view 
	 */
	public MenuParser(Application<T> app, View<? extends T> view) {
		this.factory = app.getFactory();
		this.app = app;
		this.view = view;
	}

	/**
	 * @param items 
	 * @return The root menus
	 */
	public Collection<Object> parse(Collection<ActionItem> items) {
		String lastPlace = null;
		T currentMenu = null;
		for (ActionItem item : items) {
			if (!item.getPlace().equals(lastPlace)) {
				lastPlace = item.getPlace();
				currentMenu = getMenu(item.getPlace());
			}
			this.factory.createMenuItem(currentMenu, item, this.view);
		}
		return this.baseMenus;
	}

	/**
	 * @param place
	 */
	private T getMenu(String place) {
		if (this.menus.containsKey(place)) {
			return this.menus.get(place);
		} else {
			final T parentMenu;
			int lastDot = place.lastIndexOf('.');
			if (lastDot >= 0) {
				parentMenu = getMenu(place.substring(0, lastDot));
			} else {
				parentMenu = null; // base menu
			}
			final T menu = this.factory.createMenu(parentMenu,
					this.app.getMessage(Globals.KEY_MENU_PREFIX + place));
			this.menus.put(place, menu); 
			if (parentMenu == null) {
				this.baseMenus.add(menu);
			}
			return menu;
		}
	}
}

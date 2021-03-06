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
package org.magicui.ui.factory;

import org.magicui.ui.ActionItem;
import org.magicui.ui.Component;
import org.magicui.ui.View;

/**
 * ComponentFactory is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public interface ComponentFactory<T> {
    public Object createWindow(String title, View<? extends T> content, boolean isMainWindow);
    public Component<? extends T> createView();
    public Component<? extends T> createPlaceholder();
    public Component<? extends T> createLabel();
	/**
	 * @param nodeName
	 */
	public Component<? extends T> create(String nodeName);
    
    public Object createMenuItem(Object menu, ActionItem item, View<? extends T> view);
    public Object createMenu(Object parentMenu, String name);
}

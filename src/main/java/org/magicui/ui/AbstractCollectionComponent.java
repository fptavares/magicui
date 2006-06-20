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

import java.util.Collection;
import java.util.LinkedList;

/**
 * AbstractCollectionComponent is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public abstract class AbstractCollectionComponent<C> extends AbstractValueComponent<C, Collection<?>>
		implements CollectionComponent {
	final Collection<Item> items = new LinkedList<Item>();

	/**
	 * @see org.magicui.ui.CollectionComponent#addItem(java.lang.String, java.lang.String)
	 */
	public void addItem(String key, String value) {
		this.items.add(new Item(key, value));
	}
	
	private class Item {
		private final String key;
		private final String value;
		
		/**
		 * @param key
		 * @param value
		 */
		public Item(final String key, final String value) {
			super();
			this.key = key;
			this.value = value;
		}

		/**
		 * The getter method for the key property.
		 * @return the key
		 */
		public final String getKey() {
			return this.key;
		}

		/**
		 * The getter method for the value property.
		 * @return the value
		 */
		public final String getValue() {
			return this.value;
		}
		
	}

}

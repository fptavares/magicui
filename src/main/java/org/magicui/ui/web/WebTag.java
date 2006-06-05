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

/**
 * WebTag is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class WebTag {
	private final String prefix;
	private final String sufix;
	
	private String value = "";
	
	/**
	 * @param prefix
	 * @param sufix
	 */
	public WebTag(final String prefix, final String sufix) {
		this.prefix = prefix;
		this.sufix = sufix;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return prefix + value + sufix;
	}

	/**
	 * The getter method for the value property.
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * The setter method for the value property.
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}

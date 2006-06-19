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
 * WebValueTag is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class WebValueTag<T> extends AbstractWebTag {
	private final String tag;
	
	private String [] params;
    
    private T value;
	
	/**
	 * @param tag
	 */
	public WebValueTag(final String tag) {
		this.tag = tag;
	}
    
	public String getCode() {
		String result = this.tag;
        if (this.params != null) {
    		for (String param : this.params) {
    			result = result.replaceFirst("%s", param);
    		}
        }
		return result.replaceAll("%v", String.valueOf(this.value));
	}
	
	/**
	 * The setter method for the params property.
	 * @param params the params to set
	 */
	public void setParams(String... params) {
		this.params = params;
	}

    /**
     * The getter method for the value property.
     * @return the value
     */
    public T getValue() {
        return this.value;
    }

    /**
     * The setter method for the value property.
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }
	
}

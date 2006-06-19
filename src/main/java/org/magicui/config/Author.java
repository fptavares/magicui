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
package org.magicui.config;

/**
 * Author is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class Author {
    private final String name;
    private final String email;
    private final String position;
    public Author(final Object name, final Object email, final Object position) {
        this.name = (String) name;
        this.email = (String) email;
        this.position = (String) position;
    }
    /**
     * The getter method for the email property.
     * @return the email
     */
    public final String getEmail() {
        return this.email;
    }
    /**
     * The getter method for the name property.
     * @return the name
     */
    public final String getName() {
        return this.name;
    }
    /**
     * The getter method for the position property.
     * @return the position
     */
    public final String getPosition() {
        return this.position;
    }
    
}

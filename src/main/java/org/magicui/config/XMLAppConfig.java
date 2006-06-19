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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.magicui.AppConfig;
import org.magicui.exceptions.MagicUIException;

/**
 * XMLAppConfig is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class XMLAppConfig implements AppConfig {

	private final Configuration config;
	private Collection<Author> authors = null;
	
	/**
	 * @throws MagicUIException 
	 * 
	 */
	public XMLAppConfig() throws MagicUIException {
		try {
			this.config = new XMLConfiguration("magicui.xml");
		} catch (ConfigurationException e) {
			throw new MagicUIException("Error while loading the main configuration.", e);
		}
	}
	
	/**
     * @see org.magicui.AppConfig#getName()
     */
	public String getName() {
		return this.config.getString("name");
	}
	
	/**
     * @see org.magicui.AppConfig#getDescription()
     */
	public String getDescription() {
		return this.config.getString("description");
	}
	
	/**
     * @see org.magicui.AppConfig#getURL()
     */
	public String getURL() {
		return this.config.getString("url");
	}
	
	/**
     * @see org.magicui.AppConfig#getIcon()
     */
	public String getIcon() {
		return this.config.getString("icon");
	}
	
	/**
     * @see org.magicui.AppConfig#getLogo()
     */
	public String getLogo() {
		return this.config.getString("logo");
	}
	
	/**
     * @see org.magicui.AppConfig#getAuthors()
     */
	public Collection<Author> getAuthors() {
		if (this.authors == null) {
			this.authors = new LinkedList<Author>();
			for (Iterator names = this.config.getList("author.name").iterator(),
					emails = this.config.getList("author.email").iterator(),
					positions = this.config.getList("author.position").iterator(); names.hasNext(); ) {
				this.authors.add(new Author(names.next(), emails.next(), positions.next()));
			}
		}
		return this.authors;
	}
	
	/**
     * @see org.magicui.AppConfig#getToolkit()
     */
	public String getToolkit() {
		return this.config.getString("toolkit");
	}
	
	/**
     * @see org.magicui.AppConfig#getMainWidget()
     */
	public String getMainWidget() {
		return this.config.getString("main-widget");
	}
	
	/**
     * @see org.magicui.AppConfig#getMessageResources()
     */
	public String getMessageResources() {
		return this.config.getString("message-resources");
	}
    
}

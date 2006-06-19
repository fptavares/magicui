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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.magicui.AppConfig;
import org.magicui.exceptions.MagicUIException;

/**
 * XMLAppConfig is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class PropertiesAppConfig implements AppConfig {

	private final Properties config;
	
	/**
	 * @throws MagicUIException 
	 * 
	 */
	public PropertiesAppConfig() throws MagicUIException {
		try {
			this.config = new Properties();
            this.config.load(new FileInputStream(new File("magicui.properties")));
		} catch (FileNotFoundException e) {
            throw new MagicUIException("Error while loading the main configuration.", e);
        } catch (IOException e) {
            throw new MagicUIException("Error while loading the main configuration.", e);
        }
	}
	
	/**
     * @see org.magicui.AppConfig#getName()
     */
	public String getName() {
		return this.config.getProperty("name");
	}
	
	/**
     * @see org.magicui.AppConfig#getDescription()
     */
	public String getDescription() {
		return this.config.getProperty("description");
	}
	
	/**
     * @see org.magicui.AppConfig#getURL()
     */
	public String getURL() {
		return this.config.getProperty("url");
	}
	
	/**
     * @see org.magicui.AppConfig#getIcon()
     */
	public String getIcon() {
		return this.config.getProperty("icon");
	}
	
	/**
     * @see org.magicui.AppConfig#getLogo()
     */
	public String getLogo() {
		return this.config.getProperty("logo");
	}
	
	/**
     * @see org.magicui.AppConfig#getAuthors()
     */
	public Collection<Author> getAuthors() {
		return null;
	}
	
	/**
     * @see org.magicui.AppConfig#getToolkit()
     */
	public String getToolkit() {
		return this.config.getProperty("toolkit");
	}
	
	/**
     * @see org.magicui.AppConfig#getMainWidget()
     */
	public String getMainWidget() {
		return this.config.getProperty("main-widget");
	}
	
	/**
     * @see org.magicui.AppConfig#getMessageResources()
     */
	public String getMessageResources() {
		return this.config.getProperty("message-resources");
	}
	
}

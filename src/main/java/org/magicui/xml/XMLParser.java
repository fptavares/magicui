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
package org.magicui.xml;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.magicui.Template;
import org.magicui.ui.Component;
import org.magicui.ui.Container;
import org.magicui.ui.factory.ComponentFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class bla bla bla.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class XMLParser {
	private final ComponentFactory<?> factory;
	
	private int xCounter = 0;
	private int yCounter = 0;
	
	/**
	 * @param template
	 * @param vars
	 * @param factory
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public XMLParser(final String template, Object [] vars, ComponentFactory<?> factory)
			throws SAXException, IOException, ParserConfigurationException {
		this.factory = factory;
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = builderFactory.newDocumentBuilder();
		final InputSource is = new InputSource("templates/" + template + ".xml");
		final Document doc = builder.parse(is);
		
		NodeList nodes = doc.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (node.getNodeName().equals("var")) {
				
			}
		}
		parse(null, doc.getChildNodes());		
	}
	
	private void parse(Container parent, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {    // element
            	
            	final Component component = factory.create(node.getNodeName());
            	
            	int xWeight = 1;
            	int yWeight = 1;
            	String name = null;
            	String property = null;
            	String value = null;
            	
            	// id
            	final String id = node.getAttributes().getNamedItem("id").getNodeValue();
            	
            	// x
            	Node tempNode = node.getAttributes().getNamedItem("x");
            	if (tempNode == null) {
            		this.xCounter = convertPosition(this.xCounter, "+");
            	} else {
            		this.xCounter = convertPosition(this.xCounter, tempNode.getNodeValue());
            	}
            	
            	// y
            	tempNode = node.getAttributes().getNamedItem("y");
            	if (tempNode != null) {
            		this.yCounter = convertPosition(this.yCounter, tempNode.getNodeValue());
            	}
            	
            	for (int j = 0; j < node.getAttributes().getLength(); j++) {
            		final Node attr = node.getAttributes().item(j);
            		if (attr.getNodeName().equals("name")) {
            			name = tempNode.getNodeValue();
            		} else if (attr.getNodeName().equals("property")) {
            			property = tempNode.getNodeValue();
            		} else if (attr.getNodeName().equals("value")) {
            			value = tempNode.getNodeValue();
            		} else if (attr.getNodeName().equals("xWeight")) {
            			xWeight = Integer.parseInt(tempNode.getNodeValue());
            		} else if (attr.getNodeName().equals("yWeight")) {
            			yWeight = Integer.parseInt(tempNode.getNodeValue());
            		}
            	}
            	
                parent.add(component, this.xCounter, this.yCounter, xWeight, yWeight);
            } else if ((node.getNodeType() == Node.TEXT_NODE) // text node
                    && (!node.getNodeValue().matches("^\\s$"))) { // !whitespace
                bean.getParent()
                    .set(bean.getName(), node.getNodeValue().trim());
            }
        }
    }

	/**
	 * @param val
	 */
	private int convertPosition(int counter, final String val) {
		switch (val.charAt(0)) {
			case '+':
				if (val.length() > 1) {
					counter += Integer.parseInt(val.substring(1));
				} else {
					counter++;
				}
				break;
			case '-':
				if (val.length() > 1) {
					counter -= Integer.parseInt(val.substring(1));
				} else {
					counter--;
				}
				break;
			default:
				counter = Integer.parseInt(val);
		}
		return counter;
	}
}

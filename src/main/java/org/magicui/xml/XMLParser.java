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
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.magicui.Action;
import org.magicui.Application;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.ActionItem;
import org.magicui.ui.CollectionComponent;
import org.magicui.ui.Component;
import org.magicui.ui.ValueComponent;
import org.magicui.ui.View;
import org.magicui.ui.factory.ComponentFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import static org.magicui.Globals.*;

/**
 * This class bla bla bla.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class XMLParser<T> {
	private final ComponentFactory<T> factory;
	private final Application<T> app;
	
	private int xCounter = 1;
	private int yCounter = 1;
	
	private final Map<String, Object> varMap;
	
	/**
	 * @param <T> 
	 * @param app
	 * @param widget
	 * @param vars
	 * @return 
	 * @throws MagicUIException
	 */
	public static <T> View<T> load(Application<T> app, final String widget, Object [] vars) throws MagicUIException {
		return new XMLParser<T>(app, widget, vars).start(widget, vars);
	}
	
	/**
	 * @param app 
	 * @param widget
	 * @param vars
	 * @throws MagicUIException 
	 */
	public XMLParser(Application<T> app, final String widget, Object [] vars)
			throws MagicUIException {
		
		this.varMap = new Hashtable<String, Object>(vars.length);
		
		this.factory = app.getFactory();
		this.app = app;
	}
	
	private View<T> start(final String widget, Object [] vars) throws MagicUIException {
		try {
			
			final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = builderFactory.newDocumentBuilder();
			final InputSource is = new InputSource("widgets/" + widget + ".xml");
			final Document doc = builder.parse(is);
		
			Collection<ActionItem> toolbar = null;
			Collection<ActionItem> menu = null;
			int varCounter = 0;
			Node tempNode = null;
			final NodeList nodes = doc.getChildNodes().item(0).getChildNodes();
			for (int i = 0; i < nodes.getLength()-1; i++) {
				final Node node = nodes.item(i);
				
				// <var>
				if (node.getNodeName().equals(ELEMENT_VAR)) {
					this.varMap.put(node.getAttributes().getNamedItem(ATTR_NAME).getNodeValue(), vars[varCounter]);
					varCounter++;
					
					// <action>
				} else if (node.getNodeName().equals(ELEMENT_ACTION)) {
					// key
					tempNode = node.getAttributes().getNamedItem(ATTR_KEY);
					final String text;
					if (tempNode == null) { // use default key
						text = messageFor(widget + ".action." + getAttribute(node, ATTR_ID));
					} else {
						text = messageFor(tempNode.getNodeValue());
					}
					// id
					final Action action = this.app.getAction(getAttribute(node, ATTR_ID));
					// toolbar
					if (hasAttribute(node, ATTR_TOOLBAR)) {
						if (toolbar == null) {
							toolbar = new LinkedList<ActionItem>();
						}
						toolbar.add(new ActionItem(text, action, getAttribute(node, ATTR_TOOLBAR)));
					}
					// menu
					if (hasAttribute(node, ATTR_MENU)) {
						if (menu == null) {
							menu = new LinkedList<ActionItem>();
						}
						menu.add(new ActionItem(text, action, getAttribute(node, ATTR_MENU)));
					}
				}
			}
			final Node root = nodes.item(-1);
			
			final View<T> view = (View<T>) factory.create(root.getNodeName());
			
			parse(view, root.getChildNodes()); // parse child elements

			if (toolbar != null) {
				view.addToolbars(toolbar);
			}
			if (menu != null) {
				view.addMenus(menu);
			}

			return view;

		} catch(SAXException e) {
			throw new MagicUIException(e);
		} catch (ParserConfigurationException e) {
			throw new MagicUIException(e);
		} catch (IOException e) {
			throw new MagicUIException(e);
		}
	}
	
	private static String getAttribute(Node node, String attr) {
		return node.getAttributes().getNamedItem(attr).getNodeValue();
	}
	
	private static boolean hasAttribute(Node node, String attr) {
		return node.getAttributes().getNamedItem(attr) != null;
	}
	
	/**
	 * @param key
	 * @return The message
	 */
	private String messageFor(String key) {
		return this.app.getMessage(key);
	}

	private void parse(View parent, NodeList nodes) throws MagicUIException {
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {    // element
            	
            	final Component<?> component = factory.create(node.getNodeName());
            	
            	int xWeight = 1;
            	int yWeight = 1;
            	String name = null;
            	String property = null;
            	String key = null;
            	
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
            			name = attr.getNodeValue();
            		} else if (attr.getNodeName().equals("property")) {
            			property = attr.getNodeValue();
            		} else if (attr.getNodeName().equals("key")) {
            			key = attr.getNodeValue();
            		} else if (attr.getNodeName().equals("xWeight")) {
            			xWeight = Integer.parseInt(attr.getNodeValue());
            		} else if (attr.getNodeName().equals("yWeight")) {
            			yWeight = Integer.parseInt(attr.getNodeValue());
            		}
            	}
            	
            	// item + event
        		final NodeList subNodes = node.getChildNodes();
        		for (int j = 0; j < subNodes.getLength(); j++) {
        			final Node subNode = subNodes.item(j);
        			if (subNode.getNodeName().equals("item")) {
            			((CollectionComponent) component).addItem(
            					subNode.getAttributes().getNamedItem("key").getNodeValue(),
            					subNode.getAttributes().getNamedItem("value").getNodeValue()
            					);
        			} else if (subNode.getNodeName().equals("event")) {
            			((ValueComponent) component).addListener(
            					subNode.getAttributes().getNamedItem("type").getNodeValue(),
            					this.app.getAction(subNode.getAttributes().getNamedItem("action").getNodeValue())
            					);
        			}
        		}
            	
            	if (component instanceof View) {
            		parse((View) component, node.getChildNodes());
            	} else {
            		// set value
	            	final Object actualValue;
	            	if (name == null) {
	            		actualValue = messageFor(key);
	            	} else {
	            		if (property == null) {
	            			actualValue = this.varMap.get(name);
	            		} else {
		            		try {
								actualValue = BeanUtils.getProperty(this.varMap.get(name), property);
							} catch (IllegalAccessException e) {
								throw new MagicUIException(e);
							} catch (InvocationTargetException e) {
								throw new MagicUIException(e);
							} catch (NoSuchMethodException e) {
								throw new MagicUIException(e);
							}
	            		}
	            	}
	            	((ValueComponent) component).setValue(actualValue);
	            	// set id
	            	component.setId(id);
            	}
            	
            	if (parent != null) {
            		parent.add(component, this.xCounter, this.yCounter, xWeight, yWeight);
            	}
                
            } else if ((node.getNodeType() == Node.TEXT_NODE) // text node
                    && (!node.getNodeValue().matches("^\\s$"))) { // !whitespace
                /*bean.getParent()
                    .set(bean.getName(), node.getNodeValue().trim());*/
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

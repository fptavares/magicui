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

import static org.magicui.Globals.ATTR_ICON;
import static org.magicui.Globals.ATTR_ID;
import static org.magicui.Globals.ATTR_KEY;
import static org.magicui.Globals.ATTR_MENU;
import static org.magicui.Globals.ATTR_NAME;
import static org.magicui.Globals.ATTR_TOOLBAR;
import static org.magicui.Globals.ELEMENT_ACTION;
import static org.magicui.Globals.ELEMENT_VAR;
import static org.magicui.Globals.ELEMENT_VIEW;

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
import org.magicui.Globals;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.ActionItem;
import org.magicui.ui.CollectionComponent;
import org.magicui.ui.Component;
import org.magicui.ui.ValueComponent;
import org.magicui.ui.View;
import org.magicui.ui.factory.ComponentFactory;
import org.magicui.ui.menu.MenuParser;
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
     * @throws MagicUIException
     */
    public static <T> Collection<View<? extends T>> load(Application<T> app,
            final String widget, Object [] vars) throws MagicUIException {
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

    private Collection<View<? extends T>> start(final String widget, Object [] vars) throws MagicUIException {
        try {

            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final InputSource is = new InputSource("widgets/" + widget + ".xml");
            final Document doc = builder.parse(is);

            return parse(widget, vars, doc);

        } catch(SAXException e) {
            throw new MagicUIException(e);
        } catch (ParserConfigurationException e) {
            throw new MagicUIException(e);
        } catch (IOException e) {
            throw new MagicUIException(e);
        }
    }

    /**
     * @param widget
     * @param vars
     * @param doc
     * @return
     * @throws MagicUIException
     */
    private Collection<View<? extends T>> parse(final String widget, Object[] vars, final Document doc) throws MagicUIException {
        final Collection<View<? extends T>> views = new LinkedList<View<? extends T>>();

        Collection<ActionItem> top = null;
        Collection<ActionItem> bottom = null;
        Collection<ActionItem> left = null;
        Collection<ActionItem> right = null;
        LinkedList<ActionItem> menu = null;
        int varCounter = 0;
        boolean isFirstView = true;
        Node tempNode = null;
        final NodeList nodes = doc.getChildNodes().item(0).getChildNodes();
        for (int i = 0; i < nodes.getLength()-1; i++) {
            final Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // <var>
                if (node.getNodeName().equals(ELEMENT_VAR)) {
                    this.varMap.put(node.getAttributes().getNamedItem(
                            ATTR_NAME).getNodeValue(), vars[varCounter++]);

                    // <action>
                } else if (node.getNodeName().equals(ELEMENT_ACTION)) {
                    // id
                    final Action action = this.app.getAction(getAttribute(node, ATTR_ID));
                    if (action == null) {
                        continue; // action wasn't registered, so ignore it
                    }
                    // key
                    tempNode = node.getAttributes().getNamedItem(ATTR_KEY);
                    final String text;
                    if (tempNode == null) { // use default key
                        text = messageFor(widget + ".action." + getAttribute(node, ATTR_ID));
                    } else {
                        text = messageFor(tempNode.getNodeValue());
                    }
                    // icon
                    String icon = null;
                    if (hasAttribute(node, ATTR_ICON)) {
                        icon = getAttribute(node, ATTR_ICON);
                    }
                    // toolbar
                    if (hasAttribute(node, ATTR_TOOLBAR)) {
                        final String place = getAttribute(node, ATTR_TOOLBAR);
                        if (place.equals(Globals.TOOLBAR_TOP)) {
                            top = addItemToToolbar(top, text, action, place, icon);
                        } else if (place.equals(Globals.TOOLBAR_LEFT)) {
                            left = addItemToToolbar(left, text, action, place, icon);
                        } else if (place.equals(Globals.TOOLBAR_BOTTOM)) {
                            bottom = addItemToToolbar(bottom, text, action, place, icon);
                        } else if (place.equals(Globals.TOOLBAR_RIGHT)) {
                            right = addItemToToolbar(right, text, action, place, icon);
                        }
                    }
                    // menu
                    if (hasAttribute(node, ATTR_MENU)) {
                        if (menu == null) {
                            menu = new LinkedList<ActionItem>();
                        }
                        menu.add(new ActionItem(text, action, getAttribute(node, ATTR_MENU), icon));
                    }
                } else if (node.getNodeName().equals(ELEMENT_VIEW)) {
                    final View<? extends T> view = (View<? extends T>) this.factory.create(node.getNodeName());
                    if (isFirstView) {
                        parseComponents(view, node.getChildNodes(), widget); // parse child elements
                        view.setToolbars(top, bottom, left, right);
                        view.setMenus(new MenuParser<T>(this.app, view).parse(menu));
                        isFirstView = false;
                    } else {
                        parseComponents(view, node.getChildNodes()); // parse child elements
                    }
                    views.add(view);
                }
            }
        }

        return views;
    }

    /**
     * @param toolbar
     * @param text
     * @param action
     * @param place
     * @param icon
     * @return
     */
    private Collection<ActionItem> addItemToToolbar(Collection<ActionItem> toolbar, final String text, final Action action, final String place, String icon) {
        if (toolbar == null) {
            toolbar = new LinkedList<ActionItem>();
        }
        toolbar.add(new ActionItem(text, action, place, icon));
        return toolbar;
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
    private void parseComponents(View parent, NodeList nodes)
    throws MagicUIException {
        parseComponents(parent, nodes, null);
    }
    private void parseComponents(View parent, NodeList nodes, 
            String viewId) throws MagicUIException {
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {    // element

                final Component<?> component = this.factory.create(node.getNodeName());

                int xWeight = 1;
                int yWeight = 1;
                String name = null;
                String property = null;
                String key = null;

                // id
                final String id;
                if (viewId == null) {
                    id = node.getAttributes().getNamedItem("id").getNodeValue();
                } else {
                    id = viewId;
                }

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
                                messageFor(getAttribute(subNode, ("key"))),
                                getAttribute(subNode, "value")
                        );
                    } else if (subNode.getNodeName().equals("event")) {
                        ((ValueComponent) component).addListener(
                                getAttribute(subNode, "type"),
                                this.app.getAction(getAttribute(subNode, "action"))
                        );
                    }
                }

                if (component instanceof View) {
                    parseComponents((View) component, node.getChildNodes());
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

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
import static org.magicui.Globals.ATTR_PROPERTY;
import static org.magicui.Globals.ATTR_TOOLBAR;
import static org.magicui.Globals.ATTR_VALUE;
import static org.magicui.Globals.ATTR_VIEW;
import static org.magicui.Globals.ELEMENT_ACTION;
import static org.magicui.Globals.ELEMENT_VAR;
import static org.magicui.Globals.ELEMENT_VIEW;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.ConfigurationUtils;
import org.magicui.Action;
import org.magicui.Application;
import org.magicui.Globals;
import org.magicui.actions.ActionFactory;
import org.magicui.actions.CompositeAction;
import org.magicui.actions.UIAction;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.ActionItem;
import org.magicui.ui.CollectionComponent;
import org.magicui.ui.Component;
import org.magicui.ui.ValueComponent;
import org.magicui.ui.View;
import org.magicui.ui.factory.ComponentFactory;
import org.magicui.ui.menu.MenuParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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

    private int xCounter = -1; // because x increments by default
    private int yCounter = 0;

    private final Map<String, Object> varMap;

    /**
     * @param <T> 
     * @param app
     * @param widget
     * @param vars
     * @throws MagicUIException
     */
    public static <T> Collection<View<? extends T>> load(Application<T> app,
            final String widget, final boolean standalone, Object [] vars)
            throws MagicUIException {
        return new XMLParser<T>(app, widget, vars).start(widget, standalone, vars);
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

    private Collection<View<? extends T>> start(final String widget,
            final boolean standalone, Object [] vars) throws MagicUIException {
        try {

            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final InputSource is = new InputSource(ConfigurationUtils.locate("widgets/" + widget + ".xml").openStream());
            final Document doc = builder.parse(is);

            return parse(widget, standalone, vars, doc);

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
    private Collection<View<? extends T>> parse(final String widget,
            final boolean standalone, Object[] vars, final Document doc)
            throws MagicUIException {
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
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // <var>
                if (node.getNodeName().equals(ELEMENT_VAR)) {
                    this.varMap.put(node.getAttributes().getNamedItem(
                            ATTR_NAME).getNodeValue(), vars[varCounter++]);

                    // <action>
                } else if (node.getNodeName().equals(ELEMENT_ACTION)) {
                    // id
                    final String id = getAttribute(node, ATTR_ID);
                    // fetch action
                    Action action = this.app.getAction(id);
                    if (action == null) {
                        if (node.hasChildNodes()) { // actions defined in the XML
                            action = parsePredefinedActions(node.getChildNodes());
                            this.app.registerAction(id, action);
                        } else {
                            continue; // action wasn't registered, so ignore it
                        }
                    }
                    // key
                    tempNode = node.getAttributes().getNamedItem(ATTR_KEY);
                    final String text;
                    if (tempNode == null) { // use default key
                        text = messageFor(widget + ".action." + id);
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
                    parseComponents(view, node.getChildNodes()); // parse child elements
                    if (!isFirstView) {
                        view.setId(getAttribute(node, ATTR_ID)); // auxViews have id
                    } else if (standalone) { // (isFirstView && standalone)
                        view.setToolbars(top, bottom, left, right);
                        if (menu != null) {
                            view.setMenus(new MenuParser<T>(this.app, view).parse(menu));
                        }
                        isFirstView = false;
                    }
                    views.add(view);
                }
            }
        }

        return views;
    }

    private Action parsePredefinedActions(NodeList childNodes) throws MagicUIException {
        final Collection<Action> actions = new LinkedList<Action>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            final Node node = childNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                final UIAction action = ActionFactory.create(node.getNodeName());
                // set the parameters
                if (node.hasAttributes()) {
                    final NamedNodeMap attrs = node.getAttributes();
                    for (int j = 0; j < attrs.getLength(); j++) {
                        final Node attr = attrs.item(j);
                        action.setParameter(attr.getNodeName(), attr.getNodeValue());
                    }
                }
                // view related actions have <var>s
                action.setChildren(parseViewVars(node.getChildNodes()));
                
                actions.add(action);
            }
        }
        return new CompositeAction(actions);
    }

    /**
     * @param varNodes
     * @throws MagicUIException 
     */
    private Object [] parseViewVars(final NodeList varNodes) throws MagicUIException {
        final Collection<Object> vars = new ArrayList<Object>();
        for (int j = 0; j < varNodes.getLength(); j++) {
            final Node var = varNodes.item(j);

            if (var.getNodeType() == Node.ELEMENT_NODE) {
                final Object value;
                if (hasAttribute(var, ATTR_NAME)) {
                    value = getValueFor(getAttribute(var, ATTR_NAME),
                            getAttribute(var, ATTR_PROPERTY));
                } else {
                    value = getAttribute(var, ATTR_VALUE);
                }
                
                vars.add(value);
            }
        }
        return vars.toArray();
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
        final Node attrNode = node.getAttributes().getNamedItem(attr);
        if (attrNode == null) {
            return null;
        }
        return attrNode.getNodeValue();
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
    
    private void parseComponents(View parent, NodeList nodes) throws MagicUIException {
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
                } else if (node.getNodeName().equals(Globals.ELEMENT_PLACE)) {
                    if (hasAttribute(node, ATTR_VIEW)) {
                        this.app.loadWidget(
                                getAttribute(node, getAttribute(node, ATTR_VIEW)),
                                parseViewVars(node.getChildNodes()));
                    }
                } else {
                    // set value
                    final Object actualValue;
                    if (name != null) {
                        actualValue = getValueFor(name, property);
                    } else {
                        actualValue = messageFor(key);
                    }
                    ((ValueComponent) component).setValue(actualValue);
                }
                // set id
                component.setId(id);

                if (parent != null) {
                    parent.add(component, this.xCounter, this.yCounter, xWeight, yWeight);
                }

            }
        }
    }

    /**
     * @param name
     * @param property
     * @return The actual value of the object/property
     * @throws MagicUIException
     */
    private Object getValueFor(String name, String property) throws MagicUIException {
        if (property == null) {
            return this.varMap.get(name);
        }
        try {
            return BeanUtils.getProperty(this.varMap.get(name), property);
        } catch (IllegalAccessException e) {
            throw new MagicUIException(e);
        } catch (InvocationTargetException e) {
            throw new MagicUIException(e);
        } catch (NoSuchMethodException e) {
            throw new MagicUIException(e);
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
        case '_':
            break;
        default:
            counter = Integer.parseInt(val);
        }
        return counter;
    }
}

package com.example.administrator.reflection.reflection;

import android.content.Context;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ModuleControllerCore {
	private XmlParser mConfiguration;
	private Context mContext;
	private InputStream inputStream;

	public ModuleControllerCore(Context context, Integer rawResId) {
		this.mContext = context;
		inputStream = mContext.getResources().openRawResource(rawResId);
		mConfiguration = new XmlParser(inputStream);
	}

	public LinkedHashMap<Integer,FunctionModule> read(String name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		LinkedHashMap<Integer, FunctionModule> linkedHashMap = new LinkedHashMap<Integer, FunctionModule>();

		List<NodeList> allParentNodes = new ArrayList<NodeList>();
		getConfigNodes(name, allParentNodes);

		for (NodeList nodes : allParentNodes) {
			int length = nodes.getLength();
			for (int i = 0; i < length; i++) {
				Node n = nodes.item(i);
				if (n.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				

				Element element = (Element) nodes.item(i);
				String className = element.getAttribute("className");

				Class<?> classType = Class.forName(className);
				Constructor<?> cons = classType
						.getConstructor(new Class[] { Context.class });
				FunctionModule functionModule = (FunctionModule) cons
						.newInstance(new Object[] { mContext });
				
				linkedHashMap.put(i, functionModule);
			}
		}

		return linkedHashMap;

	}
	
	public List<FunctionModule> readModuleList(String name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		List<FunctionModule> linkedHashMap = new ArrayList<FunctionModule>();

		List<NodeList> allParentNodes = new ArrayList<NodeList>();
		getConfigNodes(name, allParentNodes);

		for (NodeList nodes : allParentNodes) {
			int length = nodes.getLength();
			for (int i = 0; i < length; i++) {
				Node n = nodes.item(i);
				if (n.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				

				Element element = (Element) nodes.item(i);
				String className = element.getAttribute("className");

				Class<?> classType = Class.forName(className);
				Constructor<?> cons = classType
						.getConstructor(new Class[] { Context.class });
				FunctionModule functionModule = (FunctionModule) cons
						.newInstance(new Object[] { mContext });
				
				linkedHashMap.add(functionModule);
			}
		}

		return linkedHashMap;

	}

	private void getConfigNodes(String rootNodeName, List<NodeList> nodes) {
		if (!validateString(rootNodeName)) {
			return;
		}

		NodeList rootNodes = mConfiguration.getRootElement()
				.getElementsByTagName(rootNodeName);
		if (rootNodes.getLength() > 1) {
			throw new RuntimeException("The config name must be unique----->:"
					+ rootNodeName);
		}

		if (rootNodes.getLength() == 0) {
			return;
		}

		Node rootNode = rootNodes.item(0);
		if (rootNode.getNodeType() != Node.ELEMENT_NODE) {
			return;
		}

		String parentName = ((Element) rootNode).getAttribute("parent");
		getConfigNodes(parentName, nodes);

		if (rootNode.hasChildNodes()) {
			nodes.add(rootNode.getChildNodes());
		}

		return;
	}
	public static boolean validateString(String src) {
		if (src == null || src.trim().length() == 0) {
			return false;
		}

		return true;
	}
}

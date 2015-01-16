package framework.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import framework.ErrorGeneral;
import framework.util.Fecha;
import framework.util.Hora;
import framework.util.Transform;

public class DocumentoXML {

	private Element root;
	private Document docXML;
	private String origenXML;

	public static String getRootDefault() {
		BuildDocumentoXML bdXML = new BuildDocumentoXML();
		bdXML.createCabecera();
		bdXML.createElement("ROOT");
		bdXML.closeElement("ROOT");
		return bdXML.getDocumentoXML();
	}

	public DocumentoXML(File file) throws ErrorGeneral {
		docXML = getDocumentXML(file);
		root = docXML.getDocumentElement();
	}

	public DocumentoXML(InputStream iS) throws ErrorGeneral {
		docXML = getDocumentXML(iS);
		root = docXML.getDocumentElement();
	}

	public DocumentoXML(URL url) throws ErrorGeneral {
		docXML = getDocumentXML(url);
		root = docXML.getDocumentElement();
	}

	public Element getRoot() {
		return root;
	}

	public DocumentoXML(String strxml) throws ErrorGeneral {
		docXML = getDocumentXML(strxml);
		root = docXML.getDocumentElement();
		origenXML = strxml;
	}

	public Document getDocumentXML(String cad) throws ErrorGeneral {
		DocumentBuilder db;
		InputSource iSrc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ErrorGeneral(e);
		}

		StringReader sr = new StringReader(cad);
		iSrc = new InputSource(sr);
		try {
			Document result = db.parse(iSrc);
			return result;
		} catch (SAXException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	private Document getDocumentXML(File file) throws ErrorGeneral {
		DocumentBuilder db;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ErrorGeneral(e);
		}
		try {
			return db.parse(file);
		} catch (SAXException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	private Document getDocumentXML(InputStream iS) throws ErrorGeneral {
		DocumentBuilder db;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ErrorGeneral(e);
		}
		try {
			return db.parse(iS);
		} catch (SAXException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	private Document getDocumentXML(URL url) throws ErrorGeneral {
		DocumentBuilder db;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ErrorGeneral(e);
		}
		try {
			return db.parse(url.openStream());
		} catch (SAXException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	/**
	 * Obtiene el primer nivel de descendencia de un nodo seleccionando los de
	 * tipo Element.
	 */
	public static Element[] getDescendencia(Node nodo) {
		if (nodo == null) {
			return new Element[0];
		}
		NodeList lista = nodo.getChildNodes();
		ArrayList<Node> array = new ArrayList<Node>();
		for (int i = 0; i < lista.getLength(); i++) {
			Node hijo = lista.item(i);
			if (hijo.getNodeType() == Node.ELEMENT_NODE)
				array.add(hijo);
		}
		return (Element[]) array.toArray(new Element[array.size()]);
	}

	public static boolean getBooleanValueElement(Element element) {
		String value = getValueElement(element);
		return value.trim().equalsIgnoreCase("S");
	}

	public static long getLongValueElement(Element element) {
		String value = getValueElement(element);
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			try {
				if (value.endsWith(".00")) {
					double aux = Double.parseDouble(value);
					return (new Double(aux)).longValue();
				} else if (value.endsWith(".0")) {
					double aux = Double.parseDouble(value);
					return (new Double(aux)).longValue();
				}
			} catch (Exception e1) {
				return 0;
			}
			return 0;
		}
	}

	public static int getIntValueElement(Element element) {
		String value = getValueElement(element);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static double getDoubleValueElement(Element element) {
		String value = getValueElement(element);
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static Fecha getFechaValueElement(Element element) {
		String value = getValueElement(element);
		Fecha fecha = new Fecha(value);
		return fecha;
	}

	/**
	 * Obtiene el valor asignado a una etiqueta. En caso de estar vacío,
	 * devuelve un string vacío.
	 */
	public static String getValueElement(Element element) {
		if (element != null) {
			NodeList lista = element.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				if (nodo.getNodeType() == Node.TEXT_NODE || nodo.getNodeType() == Node.CDATA_SECTION_NODE)
					return nodo.getNodeValue();
			}
		}
		return "";
	}

	public static String xmlToHtml(String valor) {
		char[] cars = valor.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cars.length; i++) {
			switch (cars[i]) {
			case '>':
				sb.append("&gt;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '\'':
				sb.append("&#39;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				sb.append(cars[i]);
			}
		}
		return sb.toString();
	}

	public static int getValueInt(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return (int) getValueDouble(padre, nameTag);
		}
	}

	public static double getValueDouble(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getValueLong(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		return Transform.toLong(value);
	}

	public static String getValueHTML(Element padre, String nameTag) {
		return xmlToHtml(getValue(padre, nameTag));
	}

	public static boolean getValueBoolean(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		return value.equalsIgnoreCase("S");
	}

	public static String getValue(Element padre, String nameTag) {
		if (padre == null)
			return "";
		Element[] elementos = getDescendencia(padre);
		if (elementos.length == 0) {
			if (padre.getNodeName().equalsIgnoreCase(nameTag)) {
				return getValueElement(padre);
			}
		}
		for (int i = 0; i < elementos.length; i++) {
			if (elementos[i].getNodeName().equalsIgnoreCase(nameTag)) {
				return getValueElement(elementos[i]);
			}
		}
		if (elementos.length > 0) {
			return getValue(elementos[0], nameTag);
		}
		return "";
	}

	public String buscaValorString(Element element, String nombre) {
		Element elemento = buscarElemento(element, nombre);
		if (elemento == null)
			return "";
		return getValueElement(elemento);
	}

	/**
	 * Busca un elemento de forma recursiva partiendo del nodo recibido como
	 * argumento.
	 */
	public Element buscarElemento(Node nodo, String nombre) {
		Element elemento = getElemento(nodo, nombre);
		return elemento;
	}

	private Element getElemento(Node nodo, String nombre) {
		Node aux = nodo == null ? root : nodo;
		Element[] elementos = getDescendencia(aux);
		for (int i = 0; i < elementos.length; i++) {
			// System.out.println("elementos[i].getNodeName() -->" +
			// elementos[i].getNodeName());
			if (elementos[i].getNodeName().equalsIgnoreCase(nombre)) {
				return elementos[i];
			}
		}
		for (int i = 0; i < elementos.length; i++) {
			Element auxele = getElemento(elementos[i], nombre);
			if (auxele != null)
				return auxele;
		}

		return null;
	}

	public static Element buscarNodo(Node nodo, String nombre) {
		Element[] elementos = getDescendencia(nodo);
		for (int i = 0; i < elementos.length; i++) {
			if (elementos[i].getNodeName().equalsIgnoreCase(nombre)) {
				return elementos[i];
			}
		}
		for (int i = 0; i < elementos.length; i++) {
			Element auxele = buscarNodo(elementos[i], nombre);
			if (auxele != null)
				return auxele;
		}
		return null;
	}

	public static Element[] buscarNodoS(Node nodo, String nombre) {
		Element[] elementos = getDescendencia(nodo);
		ArrayList<Element> elementosBuscados = new ArrayList<Element>();

		for (int i = 0; i < elementos.length; i++) {
			if (elementos[i].getNodeName().equalsIgnoreCase(nombre)) {
				elementosBuscados.add(elementos[i]);
			}
		}
		Element[] result = new Element[elementosBuscados.size()];
		for (int i = 0; i < elementosBuscados.size(); i++) {
			result[i] = elementosBuscados.get(i);
		}
		return result;
	}

	public static boolean existenAtributos(Element element) {
		return element.getAttributes().getLength() > 0 ? true : false;
	}

	/**
	 * Obtiene un array de atributos perteneciente a un elemento. Posteriormente
	 * a cada elemento del array,se podrá utilizar los métodos
	 * Node.getNodeName() para obtener el nombre de atributo y
	 * Node.getNodeValue() para obtener el valor del atributo.
	 */
	public static Node[] getAtributos(Element element) {
		if (element == null)
			return new Node[0];
		NamedNodeMap nnm = element.getAttributes();
		Node[] nodos = new Node[nnm.getLength()];
		for (int i = 0; i < nodos.length; i++) {
			nodos[i] = nnm.item(i);
		}
		return nodos;
	}

	/**
	 * Obtiene el valor de un atributo dado partiendo de un elemento
	 * determinado.
	 */
	public static String getValueAtributo(Element element, String nombre) {
		Node[] nodos = getAtributos(element);
		for (int i = 0; i < nodos.length; i++) {
			if (nodos[i].getNodeName().equalsIgnoreCase(nombre))
				return nodos[i].getNodeValue();
		}
		return "";
	}

	public static Fecha getValueFecha(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		try {
			return new Fecha(value, "dd/MM/yyyy");
		} catch (Exception e) {
			return new Fecha("01/01/1900", "dd/MM/yyyy");
		}
	}

	public static Hora getValueHora(Element padre, String nameTag) {
		String value = getValue(padre, nameTag);
		try {
			return new Hora(value);
		} catch (Exception e) {
			return new Hora("01/01/1900");
		}
	}

	public String toString() {
		return origenXML;
	}

	public static String getCadenaAtributos(Element elemento) {
		/* Devuelve los atributos de un nodo en forma cadena emifer 7/1/09 */
		StringBuilder cadenaAtributos = new StringBuilder();
		Node[] atributos = DocumentoXML.getAtributos(elemento);
		for (int aux = 0; aux < atributos.length; aux++) {
			cadenaAtributos.append(atributos[aux].getNodeName() + "=" + "\"" + atributos[aux].getNodeValue() + "\" ");
		}
		return cadenaAtributos.toString();
	}

	public static Element buscarNodoRoot(Node nodo, String nombre) {
		// Identico a buscar nodo pero sin hacer recursion, solo si lo encuentra
		// en los nodos de raiz del entrante emifer 19/2/2008 //
		Element[] elementos = getDescendencia(nodo);
		for (int i = 0; i < elementos.length; i++) {
			if (elementos[i].getNodeName().equalsIgnoreCase(nombre)) {
				return elementos[i];
			}
		}
		return null;
	}

}
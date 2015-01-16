package framework.xml;

import java.util.Vector;

import framework.util.Fecha;
import framework.util.Hora;
import framework.util.Transform;
import framework.util.Util;

public final class BuildDocumentoXML {

	private StringBuilder documentoXML;
	private int identacion = 0;

	public BuildDocumentoXML() {
		documentoXML = new StringBuilder("");
	}

	public void createCabecera() {
		createCabecera("");
	}

	public void createCabecera(String cabecera) {
		documentoXML = new StringBuilder("");
		if (cabecera.equals("")) {
			documentoXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>");
		} else {
			documentoXML.append(cabecera);
		}
		addRetorno();
	}

	public void createElement(String element, StrAttrXML[] attrXML, String value) {
		doIdentacion();
		String atributos = this.getAtributos(attrXML);
		String tagXML = "<" + element + " " + atributos + ">" + Util.replaceEntity(value) + "</" + element + ">";
		documentoXML.append(tagXML);
		addRetorno();
	}

	public void createElementAtributeConValor(String element, String name, String value, String valorElemento) {
		StrAttrXML[] atri = { new StrAttrXML(name, value) };
		createElement(element, atri, valorElemento);
	}

	public void createElementConValor(String element, StrAttrXML[] attrXML, String valorElemento) {
		doIdentacion();
		String atributos = this.getAtributos(attrXML);
		String tagXML = "<" + element + " " + atributos + ">" + valorElemento;
		documentoXML.append(tagXML);
		addRetorno();
	}

	public void createElementAtribute(String element, String name, String value) {
		StrAttrXML[] atri = { new StrAttrXML(name, value) };
		createElement(element, atri);
	}

	public void createElement(String element, StrAttrXML[] attrXML) {
		doIdentacion();
		String atributos = this.getAtributos(attrXML);
		String tagXML = "<" + element + " " + atributos + ">";
		documentoXML.append(tagXML);
		addRetorno();
	}

	public void createElement(String element, Vector<Object> vectAttrXML, String value) {
		// Transformamos el vector en un array de AttrXML
		StrAttrXML[] attrXML = new StrAttrXML[vectAttrXML.size()];
		for (int i = 0; i < vectAttrXML.size(); i++) {
			attrXML[i] = (StrAttrXML) vectAttrXML.elementAt(i);
		}
		this.createElement(element, attrXML, value);
	}

	public void createElement(String element, Vector<Object> vectAttrXML) {
		// Transformamos el vector en un array de AttrXML
		StrAttrXML[] attrXML = new StrAttrXML[vectAttrXML.size()];
		for (int i = 0; i < vectAttrXML.size(); i++) {
			attrXML[i] = (StrAttrXML) vectAttrXML.elementAt(i);
		}
		this.createElement(element, attrXML);
	}

	public void createElement(String element, Fecha value) {
		createElement(element, value, "dd/MM/yyyy");
	}

	public void createElement(String element, Hora value) {
		createElement(element, value, Hora.formatoFechaHora);
	}

	public void createElement(String element, Fecha value, String formato) {
		String str_value;
		if (value == null)
			str_value = "";
		else {
			if (value.isNull())
				str_value = "";
			else
				str_value = value.toChar(formato);
		}
		createElement(element, str_value);
	}

	public void createElement(String element, String value) {
		if (element.length() == 0)
			return;
		doIdentacion();
		if (value == null)
			value = "";
		StringBuilder tagXML = new StringBuilder();
		tagXML.append("<");
		tagXML.append(element);
		tagXML.append(">");
		String valueAux = Util.replaceEntity(value);
		tagXML.append(valueAux);
		tagXML.append("</");
		tagXML.append(element);
		tagXML.append(">");
		this.documentoXML.append(tagXML.toString());
		addRetorno();
	}

	public void createElement(String element, int value) {
		createElement(element, String.valueOf(value));
	}

	public void createElement(String element, long value) {
		createElement(element, String.valueOf(value));
	}

	public void createElement(String element, double value) {
		createElement(element, Transform.toString(value));
	}

	public void createElementImporte(String element, double value) {
		createElement(element, Util.formatImportes(value));
	}

	public void createElementTasa(String element, double value) {
		createElement(element, Util.formatTasa(value));
	}

	public void createElement(String element, boolean value) {
		createElement(element, value ? "S" : "N");
	}

	public void createElement(String element) {
		doIdentacion();
		String tagXML = "<" + element + ">";
		documentoXML.append(tagXML);
		addRetorno();
	}

	public void createEmptyElement(String element) {
		doIdentacion();
		documentoXML.append("<" + element + "/>");
		addRetorno();
	}

	public void closeElement(String element) {
		doIdentacion();
		documentoXML.append("</" + element + ">");
		addRetorno();
	}

	public void appendXML(String xml) {
		if (xml != null) {
			doIdentacion();
			documentoXML.append(xml);
			addRetorno();
		}
	}

	public String getDocumentoXML() {
		return documentoXML.toString();
	}

	private String getAtributos(StrAttrXML[] attrXML) {
		String atributos = "";
		if (attrXML.length == 1) {
			atributos += attrXML[0].getAttributeName() + "=\"" + Util.replaceEntity(attrXML[0].getAttributeValue()) + "\"";
		} else {
			for (int i = 0; i < attrXML.length; i++) {
				if (i != (attrXML.length - 1)) {
					atributos += attrXML[i].getAttributeName() + "=\"" + Util.replaceEntity(attrXML[i].getAttributeValue()) + "\" ";
				} else {
					atributos += attrXML[i].getAttributeName() + "=\"" + Util.replaceEntity(attrXML[i].getAttributeValue()) + "\"";
				}
			}
		}
		return atributos;
	}

	public void masIdentacion() {
		identacion = identacion + 2;
	}

	public void menosIdentacion() {
		identacion = identacion - 2;
	}

	public void resetIdentacion() {
		identacion = 0;
	}

	private void addRetorno() {
		documentoXML.append("\n");
	}

	private void doIdentacion() {
		for (int i = 0; i < identacion; i++) {
			documentoXML.append(' ');
		}
	}
}

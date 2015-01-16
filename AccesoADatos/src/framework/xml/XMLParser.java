package framework.xml;

import java.io.InputStream;
import java.net.URL;

import org.w3c.dom.Element;

import framework.ErrorGeneral;

public class XMLParser {

	private DocumentoXML docXML;
	
	public XMLParser(URL url) throws ErrorGeneral {
		this.docXML = new DocumentoXML(url);
	}
	
	public XMLParser(InputStream iS) throws ErrorGeneral {
		this.docXML = new DocumentoXML(iS);
	}
	
	public XMLConfiguracion getXMLConfiguracion() {
		Element root = docXML.getRoot();
		return new XMLConfiguracion(root);
	}
}

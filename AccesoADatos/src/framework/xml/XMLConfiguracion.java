package framework.xml;

import org.w3c.dom.Element;

public class XMLConfiguracion {

	public static final String ROOT = "CHAOS";
	public static final String URLSQL = "URLSQL";
	public static final String INSTSQL = "INSTSQL";
	public static final String USERSQL = "USERSQL";
	public static final String PASSSQL = "PASSSQL";
	
	private final String urlSql; 
	private final String instSql; 
	private final String userSql; 
	private final String passSql;
	
    public XMLConfiguracion(Element elemento) {
    	this.urlSql = DocumentoXML.getValue(elemento, URLSQL);
    	this.instSql = DocumentoXML.getValue(elemento, INSTSQL);
    	this.userSql = DocumentoXML.getValue(elemento, USERSQL);
    	this.passSql = DocumentoXML.getValue(elemento, PASSSQL);
    }

	public String getUrlSql() {
		return urlSql;
	}
	
	public String getInstSql() {
		return instSql;
	}

	public String getUserSql() {
		return userSql;
	}

	public String getPassSql() {
		return passSql;
	}
}

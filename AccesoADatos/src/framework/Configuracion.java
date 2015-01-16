package framework;

import java.io.IOException;
import java.io.InputStream;

import framework.util.Util;
import framework.xml.XMLConfiguracion;
import framework.xml.XMLParser;

public class Configuracion {

	private final String urlSql;
	private final String instSql;
	private final String userSql;
	private final String passSql;

	private Configuracion() throws ErrorGeneral {
		try {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream iS = classLoader.getResourceAsStream("framework/configuracion/chaos.xml");

			XMLParser parser = new XMLParser(iS);
			XMLConfiguracion config = parser.getXMLConfiguracion();
			urlSql = config.getUrlSql();
			instSql = config.getInstSql();
			userSql = config.getUserSql();
			passSql = config.getPassSql();

			iS.close();
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static Configuracion getInstance() throws ErrorGeneral {
		return new Configuracion();
	}

	public String getUrlSql() {
		return urlSql;
	}

	public String getInstanciaSql() {
		return instSql;
	}

	public String getUserSql() {
		return userSql;
	}

	public String getPassSql() {
		return passSql;
	}

	public boolean isAnnonymousConnection() {
		return Util.isCadenaVacia(getUserSql());
	}

}

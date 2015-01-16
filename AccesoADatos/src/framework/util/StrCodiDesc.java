package framework.util;

import java.util.ArrayList;
import java.util.HashMap;

import framework.util.interfaces.IColumnData;
import framework.util.interfaces.IOrdenacion;
import framework.xml.StrAttrXML;

/**
 * 
 * @author David
 * 
 */

public final class StrCodiDesc implements IOrdenacion, IColumnData {

	public static final StrCodiDesc[] vacia = new StrCodiDesc[0];
	public static int ORDEN_CODI = 0;
	public static int ORDEN_DESC = 1;
	public static int ORDEN_CODI_INT = 2;
	public static int ORDEN_MANUAL = 3;
	public static int ORDEN_DESC_BASICO = 4;
	public static int ORDEN_CODI_IMPORTE = 5;
	private Object codigo;
	private Object descripcion;
	private String auxOrden = null;
	private HashMap<String, String> atributosExtendidos;
	private HashMap<String, Object> objetos;

	public StrCodiDesc() {
		super();
	}

	public StrCodiDesc(Object codigo, Object descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getStringCodigo() {
		return codigo.toString();
	}

	public String getStringDescripcion() {
		return descripcion.toString();
	}

	public void setCodigo(Object object) {
		codigo = object;
	}

	public void setDescripcion(Object object) {
		descripcion = object;
	}

	public String getKeyOrder(int orden) {
		if (orden == ORDEN_CODI) {
			return codigo.toString();
		} else if (orden == ORDEN_CODI_INT) {
			try {
				return Util.getZeroesNumber(8, Integer.parseInt(codigo.toString()));
			} catch (NumberFormatException e) {
				return codigo.toString();
			}
		} else if (orden == ORDEN_CODI_IMPORTE) {
			try {
				return Util.ajustarDerecha(13, Util.formatImportes(codigo.toString()), '0');
			} catch (NumberFormatException e) {
				return codigo.toString();
			}
		} else if (orden == ORDEN_MANUAL) {
			return auxOrden == null ? getStringCodigo() : auxOrden;
		}
		return descripcion.toString();
	}

	public void setOrden(String auxOrden) {
		this.auxOrden = auxOrden;
	}

	public String toString() {
		return descripcion.toString();
	}

	public static boolean incluyeCodigo(StrCodiDesc[] lista, String codigo) {
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].getStringCodigo().equals(codigo))
				return true;
		}
		return false;
	}

	public static StrCodiDesc[] excluirCodigo(StrCodiDesc[] lista, String codigo) {
		ArrayList<StrCodiDesc> aux = new ArrayList<StrCodiDesc>();
		for (int i = 0; i < lista.length; i++) {
			if (!lista[i].getStringCodigo().equals(codigo))
				aux.add(lista[i]);
		}
		return aux.toArray(new StrCodiDesc[aux.size()]);
	}

	public void addAtributoExtendido(String atributo, boolean value) {
		addAtributoExtendido(atributo, value ? "S" : "N");
	}

	public void addAtributoExtendido(String atributo, String valor) {
		if (this.atributosExtendidos == null) {
			this.atributosExtendidos = new HashMap<String, String>();
		}
		this.atributosExtendidos.put(atributo, valor);
	}

	public String getAtributoExtendido(String atributo) {
		if (this.atributosExtendidos == null)
			return "";
		String valor = this.atributosExtendidos.get(atributo);
		return valor == null ? "" : valor;
	}

	public StrAttrXML[] getAtributosExtendidos() {
		ArrayList<StrAttrXML> lista = new ArrayList<StrAttrXML>();
		if (this.atributosExtendidos != null) {
			String[] fields = this.atributosExtendidos.keySet().toArray(new String[this.atributosExtendidos.size()]);
			for (int i = 0; i < fields.length; i++) {
				String value = this.atributosExtendidos.get(fields[i]);
				StrAttrXML str = new StrAttrXML();
				str.setAttributeName(fields[i]);
				str.setAttributeValue(value);
				lista.add(str);
			}
		}
		return lista.toArray(new StrAttrXML[lista.size()]);
	}

	public void addObject(String key, Object obj) {
		if (this.objetos == null) {
			this.objetos = new HashMap<String, Object>();
		}
		this.objetos.put(key, obj);
	}

	public Object getObject(String key) {
		if (this.objetos == null)
			return null;
		Object obj = this.objetos.get(key);
		return obj;
	}

	public StrCodiDesc clonar() {
		return new StrCodiDesc(getStringCodigo(), getStringDescripcion());
	}

	public static StrCodiDesc[] excluir(StrCodiDesc[] items, String codigo) {
		ArrayList<StrCodiDesc> lista = new ArrayList<StrCodiDesc>();
		for (int i = 0; i < items.length; i++) {
			if (!items[i].getStringCodigo().equals(codigo)) {
				lista.add(items[i]);
			}
		}
		return lista.toArray(new StrCodiDesc[lista.size()]);
	}

	public static String getDescripcion(StrCodiDesc[] items, String codigo) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].getStringCodigo().equals(codigo))
				return items[i].getStringDescripcion();
		}
		return codigo;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null) {
			if (obj instanceof StrCodiDesc) {
				String codigo = ((StrCodiDesc) obj).getStringCodigo();
				return getStringCodigo().equals(codigo);
			}
		}
		return false;
	}

	public String getValor() {
		return getStringDescripcion();
	}

	public String getNombre() {
		return getStringCodigo();
	}
}

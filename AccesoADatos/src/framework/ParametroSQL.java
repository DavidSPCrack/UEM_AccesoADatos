package framework;

public class ParametroSQL {
	
	public static final class Types {
		public static final int STRING = 0;
		public static final int NUMBER = 1;
		public static final int DATE = 2;
		
	}
	
	private String key;
	private String value;
	private int type;
	

	protected ParametroSQL(String key, String value, int type) {
		this.key = key;
		this.value = value;
	}

	protected String getKey() {
		return key;
	}

	protected void setKey(String key) {
		this.key = key;
	}
	
	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}

	protected String getValue() {
		return value;
	}

	protected void setValue(String value) {
		this.value = value;
	}
	
	protected boolean isString() {
		return this.type == Types.STRING;
	}
	
	protected boolean isNumber() {
		return this.type == Types.NUMBER;
	}
	
	protected boolean isDate() {
		return this.type == Types.DATE;
	}
}

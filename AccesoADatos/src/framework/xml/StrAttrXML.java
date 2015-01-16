package framework.xml;

public final class StrAttrXML {

	private String attributeName = "";
	private String attributeValue = "";

	/** Creates a new instance of AttrXML */
	public StrAttrXML() {
	}

	public StrAttrXML(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	// Getters
	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}
	
	// Setters
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}

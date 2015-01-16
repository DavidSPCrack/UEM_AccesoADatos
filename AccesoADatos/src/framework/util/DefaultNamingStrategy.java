package framework.util;

import framework.util.interfaces.NamingStrategy;

/**
 * 
 * @author David
 * 
 */
public class DefaultNamingStrategy implements NamingStrategy {

	public String getName(String oldName) {
		return oldName;
	}

}

/**
 * 
 */
package models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Priscylla
 *
 */
public enum Level {
	
	FACIL("FÁCIL"),MEDIO("MÉDIO"),DIFICIL("DIFÍCIL");
	
	Level(String name) {
        
    }
	
	public static Level getLevel(String level) {
        if (level != null) {
                for (Level l : values()) {
                        if (level.equalsIgnoreCase(l.name()))
                                return l;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Level level: values()) {
            options.put(level.name(), level.name());
        }
        return options;
    }

}

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
public enum CorrectionType {
	
	MANUAL("Manual"),AUTOMATIC("Automática");
	
	CorrectionType(String name) {
        
    }
	
	public static CorrectionType getLevel(String correctionType) {
        if (correctionType != null) {
                for (CorrectionType type : values()) {
                        if (correctionType.equalsIgnoreCase(type.name()))
                                return type;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(CorrectionType type: values()) {
            options.put(type.name(), type.name());
        }
        return options;
    }

}

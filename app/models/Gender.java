/**
 * 
 */
package models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author priscylla
 *
 */
public enum Gender {
	 
    MASCULINO("Masculino"),FEMININO("Feminino"),UNANSWERED("Prefiro não responder");
	
	Gender(String name) {
        
    }
	
	public static Gender getGender(String gender) {
        if (gender != null) {
                for (Gender g : values()) {
                        if (gender.equalsIgnoreCase(g.name()))
                                return g;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Gender gender: values()) {
            options.put(gender.name(), gender.name());
        }
        return options;
    }

}

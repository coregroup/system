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
public enum HintsType {
	
	TEXT("Texto"),CODE("Código"),SOLUTION("Solução Análoga"),VIDEO("Video"),IMAGE("Imagem"),
	USER("Usuário");
	
	public String typeName;
	
	HintsType(String name){
		typeName = name;
	}
	
	public static HintsType getHintsType(String type) {
        if (type != null) {
                for (HintsType t : values()) {
                        if (type.equalsIgnoreCase(t.name()))
                                return t;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(HintsType type: values()) {
            options.put(type.name(), type.typeName);
        }
        return options;
    }

}
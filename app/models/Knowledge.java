package models;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Knowledge {

	PESSIMO("PÉSSIMO"),RUIM("RUIM"),NEUTRO("NEUTRO"),BOM("BOM"),OTIMO("ÓTIMO");
	
	Knowledge(String name) {
		
	}
	
	public static Knowledge getKnowledge(String knowledge) {
        if (knowledge != null) {
                for (Knowledge k : values()) {
                        if (knowledge.equalsIgnoreCase(k.name()))
                                return k;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Knowledge knowledge: values()) {
            options.put(knowledge.name(), knowledge.name());
        }
        return options;
    }

}

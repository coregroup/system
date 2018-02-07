/**
 * 
 */
package its.feedback.sim.c;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author priscylla
 *
 */
public enum Keywords {
	
	AUTO("auto"),BREAK("break"),CONTINUE("continue"),SWITCH("switch"),CASE("case"),
	DEAFULT("default"),CHAR("char"),CONST("const"),DO("do"),WHILE("while"),
	DOUBLE("double"),FLOAT("float"),IF("if"),ELSE("else"),ENUM("enum"),EXTERN("extern"),
	FOR("for"),GOTO("goto"),INT("int"),SHORT("short"),LONG("long"),SIGNED("signed"),
	UNSIGNED("unsigned"),RETURN("return"),SIZEOF("sizeof"),REGISTER("register"),
	STATIC("static"),STRUCT("struct"),TYPEDEF("typedef"),UNION("union"),VOID("void"),
	VOLATILE("volatile"),
	SCANF("scanf"), PRINTF("printf"), PORCENTO("%"), ECOMERCIAL("&");
	
	public String word;
	
	Keywords(String name){
		word = name;
	}
	
	public static Keywords getKeywords(String type) {
        if (type != null) {
                for (Keywords t : values()) {
                        if (type.equalsIgnoreCase(t.name()))
                                return t;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Keywords type: values()) {
            options.put(type.name(), type.word);
        }
        return options;
    }

}

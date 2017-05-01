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
public enum QuestionType {
	
	TEXT("Texto Curto"),PARAGRAPH_TEXT("Texto Longo"),MULTIPLE_CHOICE("Múltipla Escolha"),
	CHECKBOXES("Checkboxes"),SCALE("Escala"),UPLOAD("Upload de Arquivo"),DATE("Submeter Data"),
	TRUE_FALSE("Verdadeiro ou Falso"), CODE("Codificação");
	
	QuestionType(String name) {
        
    }
	
	public static QuestionType getQuestionType(String questionType) {
        if (questionType != null) {
                for (QuestionType type : values()) {
                        if (questionType.equalsIgnoreCase(type.name()))
                                return type;
                }
        }
        return null;
	}
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(QuestionType questionType: values()) {
            options.put(questionType.name(), questionType.name());
        }
        return options;
    }

}

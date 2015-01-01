/**
 * 
 */
package models;

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

}

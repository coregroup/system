/**
 * 
 */
package models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * @author priscylla
 *
 */
@Entity
@DiscriminatorValue("student")
public class Student extends User{
	
	private static final long serialVersionUID = 1L;
	
	//private List<Course> courses; ou Colletions?
	
	@Override
	public String getType() {
		return Student.class.getSimpleName();
	}
	
	
	

}

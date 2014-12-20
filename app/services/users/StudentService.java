/**
 * 
 */
package services.users;

import models.users.Student;

/**
 * @author priscylla
 *
 */
public interface StudentService {
	
	public void save(Student student);
	
	public void delete(Student student);
	
	public void update(Student student);

}

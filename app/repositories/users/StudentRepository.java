/**
 * 
 */
package repositories.users;

import models.users.Student;

/**
 * @author priscylla
 *
 */
public interface StudentRepository {
	
	public void save(Student student);
	
	public void delete(Student student);
	
	public void update(Student student);

}

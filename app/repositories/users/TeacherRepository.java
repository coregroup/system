/**
 * 
 */
package repositories.users;

import models.users.Teacher;

/**
 * @author priscylla
 *
 */
public interface TeacherRepository {
	
	public void save(Teacher teacher);
	
	public void delete(Teacher teacher);
	
	public void update(Teacher teacher);
	
	public Teacher exists(String email, String password);

}

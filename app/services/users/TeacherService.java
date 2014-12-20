/**
 * 
 */
package services.users;

import models.users.Teacher;

/**
 * @author priscylla
 *
 */
public interface TeacherService {
	
	public void save(Teacher teacher);
	
	public void delete(Teacher teacher);
	
	public void update(Teacher teacher);

}

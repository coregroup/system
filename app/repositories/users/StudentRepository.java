/**
 * 
 */
package repositories.users;

import com.avaje.ebean.PagingList;

import models.users.Student;

/**
 * @author priscylla
 *
 */
public interface StudentRepository {
	
	public void save(Student student);
	
	public void delete(Student student);
	
	public void update(Student student);
	
	public Student exists(String email, String password);
	
	public Student findByEmail(String email);
	
	public PagingList<Student> page(int page, int pageSize, String sortBy, String order, String filter);

}

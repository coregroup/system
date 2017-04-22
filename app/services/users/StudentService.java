/**
 * 
 */
package services.users;

import com.avaje.ebean.PagingList;

import models.users.Student;

/**
 * @author priscylla
 *
 */
public interface StudentService {
	
	public void save(Student student);
	
	public void delete(Student student);
	
	public void update(Student student);
	
	public Student findByEmail(String email);
	
	public PagingList<Student> page(int page, int pageSize, String sortBy, String order, String filter);

}

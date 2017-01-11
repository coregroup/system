/**
 * 
 */
package repositories.courses;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.course.Course;


/**
 * @author Priscylla
 *
 */
public interface CourseRepository {
	
	public void save(Course course);
	
	public void delete(Course course);
	
	public void update(Course course);
	
	public List<Course> findAll();
	
	public Course findById(Long id);
	
	public PagingList<Course> page(int page, int pageSize, String sortBy, String order, String filter);

}

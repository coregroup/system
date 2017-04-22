/**
 * 
 */
package services.course;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.course.Course;

/**
 * @author priscylla
 *
 */
public interface CourseService {
	
	public void save(Course course);
	
	public PagingList<Course> page(int page, int pageSize, String sortBy, String order, String filter);
	
	public Course findById(Long id);
	
	public List<Course> findAll();

}

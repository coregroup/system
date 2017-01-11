/**
 * 
 */
package services.course;

import com.avaje.ebean.PagingList;

import models.course.Course;

/**
 * @author priscylla
 *
 */
public interface CourseService {
	
	public void save(Course course);
	
	public PagingList<Course> page(int page, int pageSize, String sortBy, String order, String filter);

}

/**
 * 
 */
package services.course.impl;

import com.avaje.ebean.PagingList;

import models.course.Course;
import repositories.courses.CourseRepository;
import repositories.courses.impl.CourseRepositoryImpl;
import services.course.CourseService;

/**
 * @author priscylla
 *
 */
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository repository;
	
	public CourseServiceImpl() {
		repository = new CourseRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.course.CourseService#save(models.course.Course)
	 */
	@Override
	public void save(Course course) {
		repository.save(course);
	}

	/* (non-Javadoc)
	 * @see services.course.CourseService#page(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PagingList<Course> page(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.page(page, pageSize, sortBy, order, filter);
	}

}

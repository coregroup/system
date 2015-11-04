package repositories.courses.impl;

import java.util.List;

import models.course.Course;
import repositories.courses.CourseRepository;

import com.avaje.ebean.Ebean;

public class CourseRepositoryImpl implements CourseRepository {

	@Override
	public void save(Course course) {
		Ebean.save(course);
	}

	@Override
	public void delete(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Course course) {
		Ebean.update(course);
	}

	@Override
	public List<Course> findAll() {
		List<Course> courses =  Ebean.find(Course.class).findList();
		return courses;
	}

	@Override
	public Course findById(Long id) {
		Course course = Ebean.find(Course.class).where().eq("id", id.toString()).findUnique();
		return course;
	}

}

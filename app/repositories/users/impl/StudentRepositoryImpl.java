/**
 * 
 */
package repositories.users.impl;

import models.users.Student;
import repositories.users.StudentRepository;

/**
 * @author priscylla
 *
 */
public class StudentRepositoryImpl implements StudentRepository {

	
	@Override
	public void save(Student student) {
		student.save();
	}
	
	@Override
	public void delete(Student student) {
		student.delete();
	}

	@Override
	public void update(Student student) {
		student.update();
	}

	@Override
	public Student exists(String email, String password) {
		Student student = Student.find.where().eq("email", email).eq("password", password).findUnique();
		return student;
	}

}

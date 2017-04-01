/**
 * 
 */
package repositories.users.impl;

import com.avaje.ebean.Ebean;

import models.users.Student;
import repositories.users.StudentRepository;

/**
 * @author priscylla
 *
 */
public class StudentRepositoryImpl implements StudentRepository {

	
	@Override
	public void save(Student student) {
		Ebean.save(student);
	}
	
	@Override
	public void delete(Student student) {
		//TODO
	}

	@Override
	public void update(Student student) {
		Ebean.update(student);
	}


	@Override
	public Student exists(String email, String password) {
		Student student = Ebean.find(Student.class).where().eq("email", email).findUnique();
		if(student.getPassword().equals(password))
			return student;
		else
			return null;
	}

	@Override
	public Student findByEmail(String email) {
		Student student = Ebean.find(Student.class).where().eq("email", email).findUnique();
		return student;
	}

}

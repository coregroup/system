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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

	}

}

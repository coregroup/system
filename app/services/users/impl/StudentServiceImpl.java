/**
 * 
 */
package services.users.impl;

import models.users.Student;
import repositories.users.StudentRepository;
import services.users.StudentService;

/**
 * @author priscylla
 *
 */
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	
	@Override
	public void save(Student student) {
		student.setActive(true);
		this.studentRepository.save(student);
	}

	
	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Student student) {
		student.update();
	}

}

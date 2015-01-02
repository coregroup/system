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
		student.setActive(false);
		studentRepository.update(student);
	}

	@Override
	public void update(Student student) {
		studentRepository.update(student);
	}

}

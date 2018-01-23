/**
 * 
 */
package services.users.impl;

import com.avaje.ebean.PagingList;

import models.users.Student;
import repositories.users.StudentRepository;
import services.users.StudentService;

/**
 * @author priscylla
 *
 */
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository repository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.repository = studentRepository;
	}

	
	@Override
	public void save(Student student) {
		student.setActive(false);
		this.repository.save(student);
	}

	
	@Override
	public void delete(Student student) {
		student.setActive(false);
		repository.update(student);
	}

	@Override
	public void update(Student student) {
		repository.update(student);
	}


	@Override
	public Student findByEmail(String email) {
		return repository.findByEmail(email);
	}


	@Override
	public PagingList<Student> page(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.page(page, pageSize, sortBy, order, filter);
	}

}

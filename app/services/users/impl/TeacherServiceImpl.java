/**
 * 
 */
package services.users.impl;

import models.users.Teacher;
import repositories.users.TeacherRepository;
import services.users.TeacherService;

/**
 * @author priscylla
 *
 */
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public void save(Teacher teacher) {
		teacher.setActive(false);
		teacherRepository.save(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		teacher.setActive(false);
		teacherRepository.update(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		teacherRepository.update(teacher);
	}

}

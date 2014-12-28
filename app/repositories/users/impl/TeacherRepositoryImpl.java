/**
 * 
 */
package repositories.users.impl;

import models.users.Teacher;
import repositories.users.TeacherRepository;

/**
 * @author priscylla
 *
 */
public class TeacherRepositoryImpl implements TeacherRepository {

	@Override
	public void save(Teacher teacher) {
		teacher.save();
	}

	@Override
	public void delete(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Teacher exists(String email, String password) {
		Teacher teacher = Teacher.find.where().eq("email", email).eq("password", password).findUnique();
		return teacher;
	}

}

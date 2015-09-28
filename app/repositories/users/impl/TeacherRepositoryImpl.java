/**
 * 
 */
package repositories.users.impl;

import models.users.Teacher;
import repositories.users.TeacherRepository;

import com.avaje.ebean.Ebean;

/**
 * @author priscylla
 *
 */
public class TeacherRepositoryImpl implements TeacherRepository {

	@Override
	public void save(Teacher teacher) {
		Ebean.save(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
//		TODO
	}

	@Override
	public void update(Teacher teacher) {
		Ebean.update(teacher);
	}

	@Override
	public Teacher exists(String email, String password) {
		Teacher teacher = Ebean.find(Teacher.class).where().eq("email", email).findUnique();
		if(teacher!=null && teacher.getPassword().equals(password))
			return teacher;
		else
			return null;
	}

}

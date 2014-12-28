/**
 * 
 */
package services.users.impl;

import models.users.User;
import repositories.users.StudentRepository;
import repositories.users.TeacherRepository;
import repositories.users.impl.StudentRepositoryImpl;
import repositories.users.impl.TeacherRepositoryImpl;
import services.users.LoginService;

/**
 * @author priscylla
 *
 */
public class LoginServiceImpl implements LoginService {
	
	private StudentRepository studentRepository = new StudentRepositoryImpl();
	private TeacherRepository teacherRepository = new TeacherRepositoryImpl();
	
	@Override
	public User exists(String email, String password) {
		User user = (User) studentRepository.exists(email, password);
		if(user == null){
			user = (User) teacherRepository.exists(email, password);
		}
		return user;
	}

}

/**
 * 
 */
package services.users.impl;

import models.users.Student;
import models.users.User;
import repositories.users.StudentRepository;
import repositories.users.TeacherRepository;
import repositories.users.UserRepository;
import repositories.users.impl.StudentRepositoryImpl;
import repositories.users.impl.TeacherRepositoryImpl;
import repositories.users.impl.UserRepositoryImpl;
import services.users.LoginService;

/**
 * @author priscylla
 *
 */
public class LoginServiceImpl implements LoginService {
	
	private StudentRepository studentRepository = new StudentRepositoryImpl();
	private TeacherRepository teacherRepository = new TeacherRepositoryImpl();
	private UserRepository userRepository = new UserRepositoryImpl();
	
	@Override
	public User exists(String email, String password) {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null)
			return null;
		
		if(user.getType().equals(Student.class.getSimpleName()))
			user = (User) studentRepository.exists(email, password);
		else
			user = (User) teacherRepository.exists(email, password);
		
		return user;
	}

}

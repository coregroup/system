/**
 * 
 */
package services.users.impl;

import java.util.Calendar;

import models.users.User;
import repositories.users.UserRepository;
import repositories.users.impl.UserRepositoryImpl;
import services.users.UserService;

/**
 * @author priscylla
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void logged(User user) {
		user.setLastAccess(Calendar.getInstance());
		userRepository.update(user);
	}

}

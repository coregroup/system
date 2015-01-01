/**
 * 
 */
package repositories.users.impl;

import models.users.User;
import repositories.users.UserRepository;

/**
 * @author priscylla
 *
 */
public class UserRepositoryImpl implements UserRepository {

	@Override
	public User findByEmail(String email) {
		User user = null;
		user = User.find.where().eq("email", email).findUnique();
		return user;
	}

	@Override
	public void update(User user) {
		user.update();
	}

}

/**
 * 
 */
package repositories.users.impl;

import models.users.User;
import repositories.users.UserRepository;

import com.avaje.ebean.Ebean;

/**
 * @author priscylla
 *
 */
public class UserRepositoryImpl implements UserRepository {

	@Override
	public User findByEmail(String email) {
		User user = Ebean.find(User.class).where().eq("email", email).findUnique();
		return user;
	}

	@Override
	public void update(User user) {
		Ebean.update(user);
	}

}

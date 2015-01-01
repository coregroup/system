/**
 * 
 */
package repositories.users;

import models.users.User;

/**
 * @author priscylla
 *
 */
public interface UserRepository {
	
	public User findByEmail(String email);
	
	public void update(User user);

}

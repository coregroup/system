/**
 * 
 */
package services.users;

import models.users.User;

/**
 * @author priscylla
 *
 */
public interface UserService {
	
	public User findByEmail(String email);
	
	public void logged(User user);

}

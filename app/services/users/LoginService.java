/**
 * 
 */
package services.users;

import models.users.User;

/**
 * @author priscylla
 *
 */
public interface LoginService {
	
	public User exists(String email, String password);

}

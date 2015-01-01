/**
 * 
 */
package converters;

import models.users.Teacher;
import models.users.User;

/**
 * @author priscylla
 *
 */
public class ConvertUserToTeacher {
	
	public static Teacher convert(User user){
		if(user instanceof Teacher)
			return (Teacher) user;
		return null;
	}

}

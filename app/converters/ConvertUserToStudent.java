package converters;

import models.users.Student;
import models.users.User;

public class ConvertUserToStudent {
	
	public static Student convert(User user){
		if(user instanceof Student)
			return (Student) user;
		return null;
	}

}

/**
 * 
 */
package models.users;

import java.util.Date;

import javax.persistence.Id;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author priscylla
 *
 */
public abstract class User {
	
	@Id
	public Long id;
	
	@Required
	@MinLength(value = 6)
	private String fullname;
	
	@Required
	@Email
	private String email;
	
	@Required
	@MinLength(value = 6)
	private String password;
	
	@Required
	private String sex;
	
	@Required
	private Date dateOfBirth;
	
	@Required
	private String state;

}

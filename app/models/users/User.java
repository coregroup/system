/**
 * 
 */
package models.users;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import models.State;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.data.format.*;
import play.db.ebean.Model;


/**
 * @author priscylla
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Model{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long id;
	
	@Required
	@MinLength(value = 6)
	public String fullname;
	
	@Required
	@Email
	public String email;
	
	@Required
	@MinLength(value = 6)
	public String password;
	
	@Required
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	
	public static Finder<Long, User> find = new Finder(Long.class, User.class);


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}

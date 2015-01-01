/**
 * 
 */
package models.users;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Past;

import models.Gender;
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
	
	//@Formats.DateTime(pattern="yyyy-MM-dd")
	@Required
	@Past
	public Calendar dateOfBirth;
	
	public boolean active;
	
	public Calendar lastAccess;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	public static Finder<Long, User> find = new Finder(Long.class, User.class);

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the lastAccess
	 */
	public Calendar getLastAccess() {
		return lastAccess.getInstance();
	}

	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Calendar lastAccess) {
		this.lastAccess = lastAccess;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getType(){
		return User.class.getSimpleName();
	}

}

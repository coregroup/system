/**
 * 
 */
package models.users;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import models.Gender;
import models.State;
import models.curriculum.Solution;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author priscylla
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
public abstract class User {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Past
	public Calendar dateOfBirth;
	
	public boolean active;
	
	public Calendar lastAccess;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToMany
	public List<Solution> solutions;
	
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}



	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the dateOfBirth
	 */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}



	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
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
		return lastAccess;
	}



	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Calendar lastAccess) {
		this.lastAccess = lastAccess;
	}



	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}



	/**
	 * @param state the state to set
	 */
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


	/**
	 * @return the solutions
	 */
	public List<Solution> getSolutions() {
		return solutions;
	}


	/**
	 * @param solutions the solutions to set
	 */
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public String getType(){
		return User.class.getSimpleName();
	}


}

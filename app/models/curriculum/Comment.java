/**
 * 
 */
package models.curriculum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.users.User;
import play.data.validation.Constraints.MaxLength;

/**
 * @author priscylla
 *
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public Solution Solution;
	
	@MaxLength(value = 2048)
	public String text;
	
	@ManyToOne
	public User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Solution getSolution() {
		return Solution;
	}

	public void setSolution(Solution solution) {
		Solution = solution;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

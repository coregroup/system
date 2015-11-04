/**
 * 
 */
package models.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import models.course.Session;


/**
 * @author priscylla
 *
 */
@Entity
@DiscriminatorValue("student")
public class Student extends User{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="students")
	public List<Session> sessions;
	
	@Override
	public String getType() {
		return Student.class.getSimpleName();
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}

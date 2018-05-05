/**
 * 
 */
package models.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import models.course.Session;
import models.its.StudentModel;


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
	
	@OneToMany
	public StudentModel studentModel;
	
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

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	
}

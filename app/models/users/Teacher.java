package models.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.course.Session;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User{
	
	private static final long serialVersionUID = 1L;
	
	@Required
	@MinLength(value = 3)
	public String institution;
	
	@Required
	public String teachingArea;
	
	@OneToMany
	public List<Session> sessions;

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getTeachingArea() {
		return teachingArea;
	}

	public void setTeachingArea(String teachingArea) {
		this.teachingArea = teachingArea;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String getType() {
		return Teacher.class.getSimpleName();
	}

}

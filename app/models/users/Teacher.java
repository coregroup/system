package models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
	//private List<Course> courses; ou Colletions?
	
	public static Finder<Long, Teacher> find = new Finder(Long.class,
			Teacher.class);

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

}

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
	@MinLength(value = 6)
	public String teachingArea;
	//private List<Course> courses; ou Colletions?

}

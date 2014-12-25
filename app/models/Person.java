package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model {

	@Id
	public Long id;
	
	@Required
	public String name;
}
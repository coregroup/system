/**
 * 
 */
package models.curriculum;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Required
	@MinLength(value = 2)
	public String name;
	
	@ManyToMany(mappedBy="topics")
	public List<Question> questions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	

}

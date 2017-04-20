/**
 * 
 */
package models.curriculum;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import models.course.Module;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class Topic {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Required
	@MinLength(value = 2)
	public String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_id", insertable = true, updatable = true, nullable = true)
	public Topic fatherTopic;
	
	@ManyToMany(mappedBy="topics")
	public List<Question> questions;
	
	@ManyToMany(mappedBy="topics")
	public List<Module> modules;

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

	public Topic getFatherTopic() {
		return fatherTopic;
	}

	public void setFatherTopic(Topic fatherTopic) {
		this.fatherTopic = fatherTopic;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}

/**
 * 
 */
package models.course;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import models.curriculum.Topic;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public Course course;
	
	@Required
	public String description;
	
	@ManyToMany
    @JoinTable(name = "module_topic", joinColumns = @JoinColumn(name = "module_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	public List<Topic> topics;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

}

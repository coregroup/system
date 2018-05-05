/**
 * 
 */
package models.its;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.Knowledge;
import models.curriculum.Topic;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class StudentModelUnit {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Required
	@Enumerated(EnumType.STRING)
	public Knowledge knowledge;
	
	@ManyToOne
	public StudentModel studentModel;
	
	@ManyToOne
	public Topic topic;
	
	@Required
	public double mastered;
	
	@Required
	public double notmastered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	public double getMastered() {
		return mastered;
	}

	public void setMastered(double mastered) {
		this.mastered = mastered;
	}

	public double getNotmastered() {
		return notmastered;
	}

	public void setNotmastered(double notmastered) {
		this.notmastered = notmastered;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}

package models.its;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import models.Knowledge;
import models.curriculum.Topic;
import models.users.Student;
import play.data.validation.Constraints.Required;


@Entity
public class StudentModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public Student student;
	
	@Required
	@Enumerated(EnumType.STRING)
	public Knowledge knowledge;
	
	@Required
	public double mastered;
	
	@Required
	public double notmastered;
	
	@Required
	public Topic topic;
	
	@OneToMany
	public List<StudentModelUnit> studentModelUnits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
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

	public List<StudentModelUnit> getStudentModelUnits() {
		return studentModelUnits;
	}

	public void setStudentModelUnits(List<StudentModelUnit> studentModelUnits) {
		this.studentModelUnits = studentModelUnits;
	}
	
}

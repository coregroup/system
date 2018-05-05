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

import models.Knowledge;
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
	

}

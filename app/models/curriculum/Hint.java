/**
 * 
 */
package models.curriculum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.HintsType;
import models.users.User;
import play.data.validation.Constraints.Required;

/**
 * @author priscylla
 *
 */
@Entity
public class Hint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public User author;
	
	@Required
	@ManyToOne
	public Question question;
	
	public String solutionModel;
	
	public String partOfCode;
	
	public String content;
	
	@Required
	@Enumerated(EnumType.STRING)
	public HintsType type;
	
	public boolean available;

	/*
	 * Getters and Setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSolutionModel() {
		return solutionModel;
	}

	public void setSolutionModel(String solutionModel) {
		this.solutionModel = solutionModel;
	}

	public String getPartOfCode() {
		return partOfCode;
	}

	public void setPartOfCode(String partOfCode) {
		this.partOfCode = partOfCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HintsType getType() {
		return type;
	}

	public void setType(HintsType type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Hint [id=" + id + ", author=" + author + ", question=" + question + ", solutionModel=" + solutionModel
				+ ", partOfCode=" + partOfCode + ", content=" + content + ", type=" + type + "]";
	}
	
}

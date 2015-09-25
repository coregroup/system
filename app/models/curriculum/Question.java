/**
 * 
 */
package models.curriculum;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import models.CorrectionType;
import models.Level;
import models.QuestionType;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Enumerated(EnumType.STRING)
	public Level level;
	
	@ManyToMany
    @JoinTable(name = "question_topic", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	public List<Topic> topics;
	
	@Required
	@MinLength(value = 6)
	public String statement;
	
	@Required
	public String answer;
	
	@Required
	@Enumerated(EnumType.STRING)
	public QuestionType questionType;
	
	@Required
	@Enumerated(EnumType.STRING)
	public CorrectionType correctionType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public CorrectionType getCorrectionType() {
		return correctionType;
	}

	public void setCorrectionType(CorrectionType correctionType) {
		this.correctionType = correctionType;
	}
	

}

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
import javax.persistence.OneToMany;

import models.CorrectionType;
import models.Level;
import models.QuestionType;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

/**
 * @author Priscylla
 *
 */
@Entity
public class Question {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Required
	public String name;
	
	@Required
	@Enumerated(EnumType.STRING)
	public Level level;
	
	@ManyToMany
    @JoinTable(name = "question_topic", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	public List<Topic> topics;
	
	@Required
	@MinLength(value = 6)
	@MaxLength(value = 1023)
	public String statement;
	
	public String answer;
	
	@Enumerated(EnumType.STRING)
	public QuestionType questionType;
	
	@Enumerated(EnumType.STRING)
	public CorrectionType correctionType;
	
	public boolean available;
	
	@OneToMany
	public List<Solution> usersSolutions;
	
	@OneToMany
	public List<Hint> hints;

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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Solution> getUsersSolutions() {
		return usersSolutions;
	}

	public void setUsersSolutions(List<Solution> usersSolutions) {
		this.usersSolutions = usersSolutions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Hint> getHints() {
		return hints;
	}

	public void setHints(List<Hint> hints) {
		this.hints = hints;
	}
	
}

/**
 * 
 */
package models.its;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.course.Session;
import models.curriculum.Hint;
import models.curriculum.Question;
import models.curriculum.Solution;
import models.users.Student;
import models.users.User;

/**
 * @author priscylla
 *
 */
@Entity
public class HintHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public Hint hint;
	
	@ManyToOne
	public User user;
	
	@ManyToOne
	public Session session;
	
	@ManyToOne
	public Question question;
	
	@ManyToOne
	public Solution finalSolution;
	
	public String solutionInProgress;
	
	public String partOfSolution;
	
	public boolean finished;
	
	public Calendar time;

	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hint getHint() {
		return hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
	}

	public User getStudent() {
		return user;
	}

	public void setStudent(User user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Solution getFinalSolution() {
		return finalSolution;
	}

	public void setFinalSolution(Solution finalSolution) {
		this.finalSolution = finalSolution;
	}

	public String getSolutionInProgress() {
		return solutionInProgress;
	}

	public void setSolutionInProgress(String solutionInProgress) {
		this.solutionInProgress = solutionInProgress;
	}

	public String getPartOfSolution() {
		return partOfSolution;
	}

	public void setPartOfSolution(String partOfSolution) {
		this.partOfSolution = partOfSolution;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

}
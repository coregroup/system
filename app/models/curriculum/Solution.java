/**
 * 
 */
package models.curriculum;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.users.User;

/**
 * @author Priscylla
 *
 */
@Entity
public class Solution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public Question question;
	
	@ManyToOne
	public User user; 
	
	public Calendar startTime;
	
	public Calendar endTime;
	
	public String answer;
	
	public double evaluation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar date) {
		this.startTime = date;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar date) {
		this.endTime = date;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double isEvaluation() {
		return evaluation;
	}

	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}
	

}

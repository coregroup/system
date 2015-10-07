/**
 * 
 */
package its.pedagogical.impl;

import java.util.List;

import repositories.questions.QuestionRepository;
import models.curriculum.Question;
import its.pedagogical.Planning;

/**
 * @author Priscylla
 *
 */
public class PlanningImpl implements Planning {

	private QuestionRepository questionRepository;

	public PlanningImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	
	@Override
	public Question nextQuestion() {
		List<Question> question = questionRepository.findAll();		
		return question.get(0);
	}

	@Override
	public Question nextQuestion(Long id) {
		Long nextId = Long.sum(id, new Long("1"));
		Question question = questionRepository.findById(nextId);
		return question;
	}

}

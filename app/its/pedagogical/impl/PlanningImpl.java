/**
 * 
 */
package its.pedagogical.impl;

import java.util.List;

import repositories.questions.QuestionRepository;
import models.curriculum.Question;
import models.curriculum.Topic;
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
		List<Question> questions = questionRepository.findAll();		
		return questions.get(0);
	}

	@Override
	public Question nextQuestion(Long id) {
		Long nextId = Long.sum(id, new Long("1"));
		Question question = questionRepository.findById(nextId);
		return question;
	}


	@Override
	public Question nextQuestion(Topic topic) {
		List<Question> questions = topic.getQuestions();
		return questions.get(0);
	}


	@Override
	public Question nextQuestion(Topic topic, Long idQuestion) {
		List<Question> questions = topic.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			if(questions.get(i).getId().equals(idQuestion)){
				if((i+1)!=questions.size()){
					return questions.get(i+1);
				}
			}
		}
		return null;
	}

}

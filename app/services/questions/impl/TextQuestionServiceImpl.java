/**
 * 
 */
package services.questions.impl;

import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import services.questions.TextQuestionService;

/**
 * @author Priscylla
 *
 */
public class TextQuestionServiceImpl implements TextQuestionService {
	
	private QuestionRepository questionRepository;

	public TextQuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	/* (non-Javadoc)
	 * @see services.questions.TextQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		this.questionRepository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.TextQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see services.questions.TextQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		this.questionRepository.update(question);
	}

}

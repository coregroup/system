/**
 * 
 */
package services.questions.impl;

import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import services.questions.TextQuestionService;

/**
 * @author Priscylla
 *
 */
public class TextQuestionServiceImpl implements TextQuestionService {
	
	private QuestionRepository repository;

	public TextQuestionServiceImpl(QuestionRepository questionRepository) {
		this.repository = questionRepository;
	}

	/* (non-Javadoc)
	 * @see services.questions.TextQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		question.setQuestionType(QuestionType.TEXT);
		String newStatement = "<pre>" + question.getStatement() + "</pre>";
		question.setStatement(newStatement);
		this.repository.save(question);
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
		this.repository.update(question);
	}

}

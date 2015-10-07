/**
 * 
 */
package services.questions.impl;

import java.util.List;

import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.QuestionService;

/**
 * @author Priscylla
 *
 */
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepository questionRepository;

	public QuestionServiceImpl() {
		this.questionRepository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.QuestionService#findAll()
	 */
	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see services.questions.QuestionService#findById(java.lang.Long)
	 */
	@Override
	public Question findById(Long id) {
		return questionRepository.findById(id);
	}

}

/**
 * 
 */
package services.questions.impl;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.QuestionService;

/**
 * @author Priscylla
 *
 */
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepository repository;

	public QuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.QuestionService#findAll()
	 */
	@Override
	public List<Question> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see services.questions.QuestionService#findById(java.lang.Long)
	 */
	@Override
	public Question findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public PagingList<Question> page(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.page(page, pageSize, sortBy, order, filter);
	}

	@Override
	public void update(Question question) {
		repository.update(question);
	}

}

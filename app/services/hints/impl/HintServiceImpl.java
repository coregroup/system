/**
 * 
 */
package services.hints.impl;

import java.util.List;

import models.curriculum.Hint;
import repositories.hints.HintRepository;
import repositories.hints.impl.HintRepositoryImpl;
import services.hints.HintService;

/**
 * @author priscylla
 *
 */
public class HintServiceImpl implements HintService {
	
	private HintRepository repository;
	
	

	public HintServiceImpl() {
		this.repository = new HintRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.hints.HintService#save(models.curriculum.Hint)
	 */
	@Override
	public void save(Hint hint) {
		repository.save(hint);
	}

	/* (non-Javadoc)
	 * @see services.hints.HintService#delete(models.curriculum.Hint)
	 */
	@Override
	public void delete(Hint hint) {
		repository.delete(hint);
	}

	/* (non-Javadoc)
	 * @see services.hints.HintService#update(models.curriculum.Hint)
	 */
	@Override
	public void update(Hint hint) {
		repository.update(hint);
	}

	/* (non-Javadoc)
	 * @see services.hints.HintService#findAll()
	 */
	@Override
	public List<Hint> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see services.hints.HintService#findById(java.lang.Long)
	 */
	@Override
	public Hint findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Hint> findByQuestion(Long questionId) {
		return repository.findByQuestion(questionId);
	}

}

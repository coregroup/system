/**
 * 
 */
package services.its.feedback.impl;

import java.util.List;

import models.its.HintHistory;
import models.users.User;
import repositories.its.feedback.HintHistoryRepository;
import repositories.its.feedback.impl.HintHistoryRepositoryImpl;
import services.its.feedback.HintHistoryService;

/**
 * @author priscylla
 *
 */
public class HintHistoryServiceImpl implements HintHistoryService {
	
	private HintHistoryRepository repository;
	
	

	public HintHistoryServiceImpl() {
		super();
		this.repository = new HintHistoryRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#save(models.its.HintHistory)
	 */
	@Override
	public void save(HintHistory hintHistory) {
		repository.save(hintHistory);
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#delete(models.its.HintHistory)
	 */
	@Override
	public void delete(HintHistory hintHistory) {
		repository.delete(hintHistory);
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#update(models.its.HintHistory)
	 */
	@Override
	public void update(HintHistory hintHistory) {
		repository.update(hintHistory);
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#findAll()
	 */
	@Override
	public List<HintHistory> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#findById(java.lang.Long)
	 */
	@Override
	public HintHistory findById(Long id) {
		return repository.findById(id);
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#findByQuestion(java.lang.Long)
	 */
	@Override
	public List<HintHistory> findByQuestion(Long questionId) {
		return repository.findByQuestion(questionId);
	}

	/* (non-Javadoc)
	 * @see services.its.feedback.HintHistoryService#findByHint(java.lang.Long)
	 */
	@Override
	public List<HintHistory> findByHint(Long hintId) {
		return repository.findByHint(hintId);
	}

	@Override
	public List<HintHistory> findAllByUser(User user) {
		return repository.findAllByUser(user);
	}

}

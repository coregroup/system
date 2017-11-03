/**
 * 
 */
package services.solutions.impl;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Solution;
import repositories.solution.SolutionRepository;
import repositories.solution.impl.SolutionRepositoryImpl;
import services.solutions.SolutionService;

/**
 * @author Priscylla
 *
 */
public class SolutionServiceImpl implements SolutionService {
	
	private SolutionRepository repository;

	public SolutionServiceImpl() {
		this.repository = new SolutionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.solutions.SolutionService#save(models.curriculum.Solution)
	 */
	@Override
	public void save(Solution solution) {
		repository.save(solution);
	}

	/* (non-Javadoc)
	 * @see services.solutions.SolutionService#update(models.curriculum.Solution)
	 */
	@Override
	public void update(Solution solution) {
		repository.update(solution);
	}

	@Override
	public List<Solution> findAll() {
		return repository.findAll();
	}

	@Override
	public Solution findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.page(page, pageSize, sortBy, order, filter);
	}

	@Override
	public PagingList<Solution> pageUncorrected(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.pageUncorrected(page, pageSize, sortBy, order, filter);
	}

}

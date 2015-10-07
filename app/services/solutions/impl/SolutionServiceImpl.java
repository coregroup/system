/**
 * 
 */
package services.solutions.impl;

import models.curriculum.Solution;
import repositories.solution.SolutionRepository;
import repositories.solution.impl.SolutionRepositoryImpl;
import services.solutions.SolutionService;

/**
 * @author Priscylla
 *
 */
public class SolutionServiceImpl implements SolutionService {
	
	private SolutionRepository solutionRepository;

	public SolutionServiceImpl() {
		this.solutionRepository = new SolutionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.solutions.SolutionService#save(models.curriculum.Solution)
	 */
	@Override
	public void save(Solution solution) {
		solutionRepository.save(solution);
	}

	/* (non-Javadoc)
	 * @see services.solutions.SolutionService#update(models.curriculum.Solution)
	 */
	@Override
	public void update(Solution solution) {
		solutionRepository.update(solution);
	}

}

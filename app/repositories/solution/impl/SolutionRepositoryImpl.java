/**
 * 
 */
package repositories.solution.impl;

import com.avaje.ebean.Ebean;

import models.curriculum.Solution;
import repositories.solution.SolutionRepository;

/**
 * @author Priscylla
 *
 */
public class SolutionRepositoryImpl implements SolutionRepository {

	/* (non-Javadoc)
	 * @see repositories.solution.SolutionRepository#save(models.curriculum.Solution)
	 */
	@Override
	public void save(Solution solution) {
		Ebean.save(solution);
	}

	/* (non-Javadoc)
	 * @see repositories.solution.SolutionRepository#update(models.curriculum.Solution)
	 */
	@Override
	public void update(Solution solution) {
		Ebean.update(solution);
	}

}

/**
 * 
 */
package repositories.solution;

import models.curriculum.Solution;

/**
 * @author Priscylla
 *
 */
public interface SolutionRepository {
	
	public void save(Solution solution);
	
	public void update(Solution solution);

}

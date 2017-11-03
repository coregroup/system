/**
 * 
 */
package repositories.solution;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Solution;

/**
 * @author Priscylla
 *
 */
public interface SolutionRepository {
	
	public void save(Solution solution);
	
	public void update(Solution solution);
	
	public List<Solution> findAll();
	
	public Solution findById(Long id);
	
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter);
	
	public PagingList<Solution> pageUncorrected(int page, int pageSize, String sortBy, String order, String filter);

}

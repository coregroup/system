/**
 * 
 */
package services.solutions;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Solution;

/**
 * @author Priscylla
 *
 */
public interface SolutionService {
	
	public void save(Solution solution);
	
	public void update(Solution solution);
	
	public List<Solution> findAll();
	
	public List<Solution> findByUser(Long userId);
	
	public Solution findById(Long id);
	
	public PagingList<Solution> pageUncorrected(int page, int pageSize, String sortBy, String order, String filter);
	
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter);
	
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter, Long userId);
	
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter, Long userId, double evalutaion);

}

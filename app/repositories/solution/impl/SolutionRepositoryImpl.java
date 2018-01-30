/**
 * 
 */
package repositories.solution.impl;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagingList;

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

	@Override
	public List<Solution> findAll() {
		List<Solution> solutions =  Ebean.find(Solution.class).findList();
		return solutions;
	}

	@Override
	public Solution findById(Long id) {
		Solution solution = Ebean.find(Solution.class).where().eq("id", id.toString()).findUnique();
		return solution;
	}

	@Override
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter) {
		return Ebean.find(Solution.class).where()
				.ilike("id", "%" + filter + "%")
		        .orderBy(sortBy + " " + order)
		        .findPagingList(pageSize);
	}

	@Override
	public PagingList<Solution> pageUncorrected(int page, int pageSize, String sortBy, String order, String filter) {
		return Ebean.find(Solution.class).where().eq("evaluation", "-1")
				.ilike("id", "%" + filter + "%")
		        .orderBy(sortBy + " " + order)
		        .findPagingList(pageSize);
	}

	@Override
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter, Long userId) {
		return Ebean.find(Solution.class).where().eq("user_id", userId.toString())
				.ilike("id", "%" + filter + "%")
		        .orderBy(sortBy + " " + order)
		        .findPagingList(pageSize);
	}

	@Override
	public PagingList<Solution> page(int page, int pageSize, String sortBy, String order, String filter, Long userId,
			double evalutaion) {
		return Ebean.find(Solution.class).where().eq("user_id", userId.toString()).eq("evaluation", Double.toString(evalutaion))
				.ilike("id", "%" + filter + "%")
		        .orderBy(sortBy + " " + order)
		        .findPagingList(pageSize);
	}

	@Override
	public List<Solution> findByUser(Long userId) {
		List<Solution> solutions = Ebean.find(Solution.class).where().eq("user_id", userId.toString()).findList();
		return solutions;
	}

}

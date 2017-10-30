/**
 * 
 */
package repositories.hints.impl;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.curriculum.Hint;
import repositories.hints.HintRepository;

/**
 * @author priscylla
 *
 */
public class HintRepositoryImpl implements HintRepository {

	/* (non-Javadoc)
	 * @see repositories.hints.HintRepository#save(models.curriculum.Hint)
	 */
	@Override
	public void save(Hint hint) {
		Ebean.save(hint);
	}

	/* (non-Javadoc)
	 * @see repositories.hints.HintRepository#delete(models.curriculum.Hint)
	 */
	@Override
	public void delete(Hint hint) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repositories.hints.HintRepository#update(models.curriculum.Hint)
	 */
	@Override
	public void update(Hint hint) {
		Ebean.update(hint);
	}

	/* (non-Javadoc)
	 * @see repositories.hints.HintRepository#findAll()
	 */
	@Override
	public List<Hint> findAll() {
		List<Hint> hints = Ebean.find(Hint.class).findList();
		return hints;
	}

	/* (non-Javadoc)
	 * @see repositories.hints.HintRepository#findById(java.lang.Long)
	 */
	@Override
	public Hint findById(Long id) {
		Hint hint = Ebean.find(Hint.class).where().eq("id", id.toString()).findUnique();
		return hint;
	}

}

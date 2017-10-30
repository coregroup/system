/**
 * 
 */
package repositories.hints;

import java.util.List;

import models.curriculum.Hint;

/**
 * @author priscylla
 *
 */
public interface HintRepository {
	
	public void save(Hint hint);
	
	public void delete(Hint hint);
	
	public void update(Hint hint);
	
	public List<Hint> findAll();
	
	public Hint findById(Long id);

}

/**
 * 
 */
package services.hints;

import java.util.List;

import models.curriculum.Hint;

/**
 * @author priscylla
 *
 */
public interface HintService {

	public void save(Hint hint);

	public void delete(Hint hint);
	
	public void update(Hint hint);
	
	public List<Hint> findAll();
	
	public Hint findById(Long id);
	
	public List<Hint> findByQuestion(Long questionId);
	
	public List<Hint> findByQuestionAndType(Long questionId, String type);
}

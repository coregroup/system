/**
 * 
 */
package repositories.questions;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface QuestionRepository {
	
	public void save(Question question);
	
	public void delete(Question question);
	
	public void update(Question question);

}

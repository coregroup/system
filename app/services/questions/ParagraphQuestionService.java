/**
 * 
 */
package services.questions;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface ParagraphQuestionService {
	
	public void save(Question question);
	
	public void delete(Question question);
	
	public void update(Question question);

}

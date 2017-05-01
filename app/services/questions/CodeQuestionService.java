/**
 * 
 */
package services.questions;

import models.curriculum.Question;

/**
 * @author priscylla
 *
 */
public interface CodeQuestionService {
	
	public void save(Question question, String exinput, String exoutput);
	
	public void delete(Question question);
	
	public void update(Question question);


}

/**
 * 
 */
package services.questions;

import java.util.List;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface MultipleChoiceQuestionService {
	
	public void save(Question question, List<String> options);
	
	public void delete(Question question);
	
	public void update(Question question);


}

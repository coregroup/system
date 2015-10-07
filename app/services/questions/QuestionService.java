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
public interface QuestionService {
	
	public List<Question> findAll();
	
	public Question findById(Long id);

}

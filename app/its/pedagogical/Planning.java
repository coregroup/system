/**
 * 
 */
package its.pedagogical;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface Planning {
	
	public Question nextQuestion();
	
	public Question nextQuestion(Long id);

}

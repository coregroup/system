/**
 * 
 */
package its.pedagogical;

import models.curriculum.Question;
import models.curriculum.Topic;

/**
 * @author Priscylla
 *
 */
public interface Planning {
	
	public Question nextQuestion();
	
	public Question nextQuestion(Long id);
	
	public Question nextQuestion(Topic topic);
	
	public Question nextQuestion(Topic topic, Long idQuestion);

}

/**
 * 
 */
package services.topics;

import java.util.List;

import models.curriculum.Question;
import models.curriculum.Topic;

/**
 * @author Priscylla
 *
 */
public interface TopicService {
	
	public void save(Topic topic);
	
	public void delete(Topic topic);
	
	public void update(Topic topic);
	
	public List<Topic> findAll();
	
	public Topic findById(Long id);
	
//	public List<Question> getQuestions(Topic topic);

}

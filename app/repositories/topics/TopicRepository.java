/**
 * 
 */
package repositories.topics;

import models.curriculum.Topic;

/**
 * @author Priscylla
 *
 */
public interface TopicRepository {
	
	public void save(Topic topic);
	
	public void delete(Topic topic);
	
	public void update(Topic topic);

}

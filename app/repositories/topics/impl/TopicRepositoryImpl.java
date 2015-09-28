/**
 * 
 */
package repositories.topics.impl;

//import play.db.jpa.JPA;
import com.avaje.ebean.Ebean;

import models.curriculum.Topic;
import repositories.topics.TopicRepository;

/**
 * @author Priscylla
 *
 */
public class TopicRepositoryImpl implements TopicRepository {

	/* (non-Javadoc)
	 * @see repositories.topics.TopicRepository#save(models.curriculum.Topic)
	 */
	@Override
	public void save(Topic topic) {
		Ebean.save(topic);
	}

	/* (non-Javadoc)
	 * @see repositories.topics.TopicRepository#delete(models.curriculum.Topic)
	 */
	@Override
	public void delete(Topic topic) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see repositories.topics.TopicRepository#update(models.curriculum.Topic)
	 */
	@Override
	public void update(Topic topic) {
		Ebean.update(topic);
	}

}

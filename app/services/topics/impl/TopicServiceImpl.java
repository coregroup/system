/**
 * 
 */
package services.topics.impl;

import java.util.List;

import models.curriculum.Topic;
import repositories.topics.TopicRepository;
import repositories.topics.impl.TopicRepositoryImpl;
import services.topics.TopicService;

/**
 * @author Priscylla
 *
 */
public class TopicServiceImpl implements TopicService {
	
	private TopicRepository topicRepository;

	public TopicServiceImpl() {
		this.topicRepository = new TopicRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.topics.TopicService#save(models.curriculum.Topic)
	 */
	@Override
	public void save(Topic topic) {
		topicRepository.save(topic);
	}

	/* (non-Javadoc)
	 * @see services.topics.TopicService#delete(models.curriculum.Topic)
	 */
	@Override
	public void delete(Topic topic) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.topics.TopicService#update(models.curriculum.Topic)
	 */
	@Override
	public void update(Topic topic) {
		topicRepository.update(topic);
	}

	/* (non-Javadoc)
	 * @see services.topics.TopicService#findAll()
	 */
	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	@Override
	public Topic findById(Long id) {
		return topicRepository.findById(id);
	}
}

/**
 * 
 */
package repositories.its.feedback;

import java.util.List;

import models.its.HintHistory;
import models.users.User;

/**
 * @author priscylla
 *
 */
public interface HintHistoryRepository {
	
	public void save(HintHistory hintHistory);
	
	public void delete(HintHistory hintHistory);
	
	public void update(HintHistory hintHistory);
	
	public List<HintHistory> findAll();
	
	public HintHistory findById(Long id);
	
	public List<HintHistory> findByQuestion(Long questionId);
	
	public List<HintHistory> findByHint(Long hintId);
	
	public List<HintHistory> findAllByUser(User user);
	
	public List<HintHistory> findAllByUserAndQuestion(User user, Long questionId);

}

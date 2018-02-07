/**
 * 
 */
package repositories.its.feedback.impl;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.its.HintHistory;
import models.users.User;
import repositories.its.feedback.HintHistoryRepository;

/**
 * @author priscylla
 *
 */
public class HintHistoryRepositoryImpl implements HintHistoryRepository {

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#save(models.its.HintHistory)
	 */
	@Override
	public void save(HintHistory hintHistory) {
		Ebean.save(hintHistory);
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#delete(models.its.HintHistory)
	 */
	@Override
	public void delete(HintHistory hintHistory) {
		Ebean.delete(hintHistory);
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#update(models.its.HintHistory)
	 */
	@Override
	public void update(HintHistory hintHistory) {
		Ebean.update(hintHistory);
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#findAll()
	 */
	@Override
	public List<HintHistory> findAll() {
		List<HintHistory> hintHistories = Ebean.find(HintHistory.class).findList();
		return hintHistories;
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#findById(java.lang.Long)
	 */
	@Override
	public HintHistory findById(Long id) {
		HintHistory hintHistory = Ebean.find(HintHistory.class).where().eq("id", id.toString()).findUnique();
		return hintHistory;
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#findByQuestion(java.lang.Long)
	 */
	@Override
	public List<HintHistory> findByQuestion(Long questionId) {
		List<HintHistory> hintHistories = Ebean.find(HintHistory.class).where().eq("question_id", questionId.toString()).findList();
		return hintHistories;
	}

	/* (non-Javadoc)
	 * @see repositories.its.feedback.HintHistoryRepository#findByHint(java.lang.Long)
	 */
	@Override
	public List<HintHistory> findByHint(Long hintId) {
		List<HintHistory> hintHistories = Ebean.find(HintHistory.class).where().eq("hint_id", hintId.toString()).findList();
		return hintHistories;
	}

	@Override
	public List<HintHistory> findAllByUser(User user) {
		List<HintHistory> histories = Ebean.find(HintHistory.class).where().eq("user_id", user.getId().toString()).findList();
		return histories;
	}

}

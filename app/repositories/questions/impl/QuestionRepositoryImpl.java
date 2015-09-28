/**
 * 
 */
package repositories.questions.impl;

import com.avaje.ebean.Ebean;

import models.curriculum.Question;
import repositories.questions.QuestionRepository;

/**
 * @author Priscylla
 *
 */
public class QuestionRepositoryImpl implements QuestionRepository {

	/* (non-Javadoc)
	 * @see repositories.questions.QuestionRepository#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		Ebean.save(question);
	}

	/* (non-Javadoc)
	 * @see repositories.questions.QuestionRepository#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question questios) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see repositories.questions.QuestionRepository#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		Ebean.update(question);
	}

}

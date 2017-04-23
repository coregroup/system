/**
 * 
 */
package repositories.questions.impl;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagingList;

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
		//TODO
		Ebean.update(question);
	}

	@Override
	public List<Question> findAll() {
		List<Question> questions =  Ebean.find(Question.class).findList();
		return questions;
	}

	@Override
	public Question findById(Long id) {
		Question question = Ebean.find(Question.class).where().eq("id", id.toString()).findUnique();
		return question;
	}

	@Override
	public PagingList<Question> page(int page, int pageSize, String sortBy, String order, String filter) {
		return Ebean.find(Question.class).where()
				.ilike("name", "%" + filter + "%")
		        .orderBy(sortBy + " " + order)
		        .findPagingList(pageSize);
	}

}

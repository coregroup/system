/**
 * 
 */
package repositories.questions;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface QuestionRepository {
	
	public void save(Question question);
	
	public void delete(Question question);
	
	public void update(Question question);
	
	public List<Question> findAll();
	
	public Question findById(Long id);
	
	public PagingList<Question> page(int page, int pageSize, String sortBy, String order, String filter);

}

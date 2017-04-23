/**
 * 
 */
package services.questions;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.curriculum.Question;

/**
 * @author Priscylla
 *
 */
public interface QuestionService {
	
	public List<Question> findAll();
	
	public Question findById(Long id);
	
	public PagingList<Question> page(int page, int pageSize, String sortBy, String order, String filter);

}

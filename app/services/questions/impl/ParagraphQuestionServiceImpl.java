/**
 * 
 */
package services.questions.impl;

import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.ParagraphQuestionService;

/**
 * @author Priscylla
 *
 */
public class ParagraphQuestionServiceImpl implements ParagraphQuestionService {
	
	private QuestionRepository repository;

	public ParagraphQuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.ParagraphQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		question.setQuestionType(QuestionType.PARAGRAPH_TEXT);
		String newStatement = "<pre>" + question.getStatement() + "</pre>";
		question.setStatement(newStatement);
		this.repository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.ParagraphQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.questions.ParagraphQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		repository.update(question);
	}

}

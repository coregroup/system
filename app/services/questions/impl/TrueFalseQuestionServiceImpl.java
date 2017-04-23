/**
 * 
 */
package services.questions.impl;

import models.CorrectionType;
import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.TrueFalseQuestionService;

/**
 * @author Priscylla
 *
 */
public class TrueFalseQuestionServiceImpl implements TrueFalseQuestionService {
	
	private QuestionRepository repository;

	public TrueFalseQuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.TrueFalseQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		question.setQuestionType(QuestionType.TRUE_FALSE);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		this.repository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.TrueFalseQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.questions.TrueFalseQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package services.questions.impl;

import models.CorrectionType;
import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.UploadQuestionService;

/**
 * @author Priscylla
 *
 */
public class UploadQuestionServiceImpl implements UploadQuestionService {
	
	private QuestionRepository repository;

	public UploadQuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.UploadQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		question.setQuestionType(QuestionType.UPLOAD);
		question.setCorrectionType(CorrectionType.MANUAL);
		this.repository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.UploadQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.questions.UploadQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		repository.update(question);
	}

}

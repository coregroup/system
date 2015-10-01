/**
 * 
 */
package services.questions.impl;

import models.CorrectionType;
import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import services.questions.UploadQuestionService;

/**
 * @author Priscylla
 *
 */
public class UploadQuestionServiceImpl implements UploadQuestionService {
	
	private QuestionRepository questionRepository;

	public UploadQuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	/* (non-Javadoc)
	 * @see services.questions.UploadQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question) {
		question.setQuestionType(QuestionType.UPLOAD);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		this.questionRepository.save(question);
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
		// TODO Auto-generated method stub

	}

}

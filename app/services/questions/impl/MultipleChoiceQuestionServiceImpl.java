/**
 * 
 */
package services.questions.impl;

import java.util.List;

import models.CorrectionType;
import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import services.questions.MultipleChoiceQuestionService;

/**
 * @author Priscylla
 *
 */
public class MultipleChoiceQuestionServiceImpl implements
		MultipleChoiceQuestionService {
	
	private QuestionRepository questionRepository;

	public MultipleChoiceQuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	/* (non-Javadoc)
	 * @see services.questions.MultipleChoiceQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question, List<String> options) {
		question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		
		question.setStatement(question.getStatement() + "<br> A. " + options.get(0) + "<br> B. "
				+ options.get(1) + "<br>C. "
				+ options.get(2) + "<br>D. "
				+ options.get(3) + "<br>E. "
				+ options.get(4));
		
		
		this.questionRepository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.MultipleChoiceQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.questions.MultipleChoiceQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		// TODO Auto-generated method stub

	}

}

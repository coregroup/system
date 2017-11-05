/**
 * 
 */
package services.questions.impl;

import java.util.List;

import models.CorrectionType;
import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.MultipleChoiceQuestionService;

/**
 * @author Priscylla
 *
 */
public class MultipleChoiceQuestionServiceImpl implements
		MultipleChoiceQuestionService {
	
	private QuestionRepository repository;

	public MultipleChoiceQuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.MultipleChoiceQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question, List<String> options) {
		question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		
		question.setStatement(question.getStatement() + "<br> <span class=\"badge\">A</span></a> " 
				+ options.get(0) + "<br> <span class=\"badge\">B</span></a> "
				+ options.get(1) + "<br> <span class=\"badge\">C</span></a> "
				+ options.get(2) + "<br> <span class=\"badge\">D</span></a> "
				+ options.get(3) + "<br> <span class=\"badge\">E</span></a> "
				+ options.get(4));
		
		
		this.repository.save(question);
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
		repository.update(question);
	}

}

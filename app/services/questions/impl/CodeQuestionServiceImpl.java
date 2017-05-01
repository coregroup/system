/**
 * 
 */
package services.questions.impl;

import models.QuestionType;
import models.curriculum.Question;
import repositories.questions.QuestionRepository;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.CodeQuestionService;

/**
 * @author priscylla
 *
 */
public class CodeQuestionServiceImpl implements CodeQuestionService {
	
	private QuestionRepository repository;
	
	

	public CodeQuestionServiceImpl() {
		this.repository = new QuestionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.questions.CodeQuestionService#save(models.curriculum.Question)
	 */
	@Override
	public void save(Question question, String exinput, String exoutput){
		question.setStatement(question.getStatement() +
				"<br>Exemplo de Entrada<br>" + exinput +
				"<br>Exemplo de Saída<br>" + exoutput);
		question.setQuestionType(QuestionType.CODE);
		repository.save(question);
	}

	/* (non-Javadoc)
	 * @see services.questions.CodeQuestionService#delete(models.curriculum.Question)
	 */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.questions.CodeQuestionService#update(models.curriculum.Question)
	 */
	@Override
	public void update(Question question) {
		repository.update(question);
	}

}

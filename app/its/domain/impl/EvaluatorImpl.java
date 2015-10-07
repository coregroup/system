/**
 * 
 */
package its.domain.impl;

import models.CorrectionType;
import models.curriculum.Question;
import models.curriculum.Solution;
import its.domain.Evaluator;

/**
 * @author Priscylla
 *
 */
public class EvaluatorImpl implements Evaluator {

	/* (non-Javadoc)
	 * @see its.domain.Evaluator#evaluate(models.curriculum.Solution)
	 */
	@Override
	public boolean evaluate(Solution solution) {
		Question question = solution.getQuestion();
		String studentAnswer = solution.getAnswer();
		
		if(question.getCorrectionType().equals(CorrectionType.AUTOMATIC)){
			String correctAnswer = question.getAnswer();
			if(correctAnswer.equals(studentAnswer))
				return true;
			else
				return false;
		}
		
		return false;
	}

}

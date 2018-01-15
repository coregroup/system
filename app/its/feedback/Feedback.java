/**
 * 
 */
package its.feedback;

import java.util.ArrayList;
import java.util.List;

import its.feedback.sim.CodeSimilarity;
import its.feedback.sim.c.CosineImpl;
import models.HintsType;
import models.curriculum.Hint;
import services.hints.HintService;
import services.hints.impl.HintServiceImpl;

/**
 * @author priscylla
 *
 */
public class Feedback {
	
	private CodeSimilarity similarity;
	private HintService service;
	
	public Feedback() {
		super();
		this.similarity = new CosineImpl();
		this.service = new HintServiceImpl();
	}
	
	public void getTextFeedback(Long questionId, String code){
		List<Hint> hints = service.findByQuestionAndType(questionId, HintsType.TEXT.name());
		if(code.isEmpty() || code.equals("")){
			List<Hint> selectHints = new ArrayList<>();
			for (Hint hint : hints) {
				if(hint.partOfCode.isEmpty() || hint.partOfCode.equals("")){
					selectHints.add(hint);
				}
			}
			selectHints.get(0);
		} else {
			//usar similaridade para de cosine para determinar qual dica mostrar
		}
	}
	
	public void getVideoFeedback(String code){
		
	}
	
	public void getImageFeedback(String code){
		
	}

}

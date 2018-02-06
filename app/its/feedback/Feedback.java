/**
 * 
 */
package its.feedback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import its.feedback.sim.CodeSimilarity;
import its.feedback.sim.c.CosineImpl;
import models.HintsType;
import models.curriculum.Hint;
import models.curriculum.Question;
import models.its.HintHistory;
import models.users.Student;
import models.users.User;
import services.hints.HintService;
import services.hints.impl.HintServiceImpl;
import services.its.feedback.HintHistoryService;
import services.its.feedback.impl.HintHistoryServiceImpl;

/**
 * @author priscylla
 *
 */
public class Feedback {
	
	private CodeSimilarity similarity;
	private HintService service;
	private HintHistoryService hintHistoryService;
	
	public Feedback() {
		super();
		this.similarity = new CosineImpl();
		this.service = new HintServiceImpl();
		this.hintHistoryService = new HintHistoryServiceImpl();
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
			for (Hint hint : selectHints) {
				
			}
			//pegar as dicas na ordem, se nenhuma dica foi dada, mostrar a primeira, depois a segunda...
			// Pegar get(0) e buscar no HintHistory do usuário, senão estiver lá retornar ela
			// Se estiver lá e o status da última solução for finalizada, mostrar ela
			// Se estive lá e o status da última solução for em andamento, pegar a próxima get(1) e fazer verificação
		} else {
			//usar similaridade para de cosine para determinar qual dica mostrar
		}
	}
	
	public void getVideoFeedback(String code){
		
	}
	
	public Hint getImageFeedback(Question question, String code, Student user){
		List<Hint> hints = service.findByQuestionAndType(question.getId(), HintsType.IMAGE.toString());
		
		HintHistory hintHistory = new HintHistory();
		hintHistory.setHint(hints.get(0));
		hintHistory.setStudent(user);
		hintHistory.setSession(null);
		hintHistory.setQuestion(question);
		hintHistory.setFinalSolution(null);
		hintHistory.setSolutionInProgress(code);
		hintHistory.setPartOfSolution(null);
		hintHistory.setFinished(false);
		hintHistory.setTime(Calendar.getInstance());
		hintHistoryService.save(hintHistory);
		
		return hints.get(0);
	}
	
	public void startLog(Question question, Student user){
		
		HintHistory hintHistory = new HintHistory();
		hintHistory.setHint(null);
		hintHistory.setStudent(user);
		hintHistory.setSession(null);
		hintHistory.setQuestion(question);
		hintHistory.setFinalSolution(null);
		hintHistory.setSolutionInProgress(null);
		hintHistory.setPartOfSolution(null);
		hintHistory.setFinished(false);
		hintHistory.setTime(Calendar.getInstance());
		hintHistoryService.save(hintHistory);
	}

}

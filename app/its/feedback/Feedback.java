/**
 * 
 */
package its.feedback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import its.feedback.sim.CodeSimilarity;
import its.feedback.sim.c.CosineImpl;
import its.feedback.sim.c.KeywordSimImpl;
import models.HintsType;
import models.curriculum.Hint;
import models.curriculum.Question;
import models.its.HintHistory;
import models.users.Student;
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
		this.similarity = new KeywordSimImpl();
		this.service = new HintServiceImpl();
		this.hintHistoryService = new HintHistoryServiceImpl();
	}
	
	public Hint getTextFeedback(Question question, String code, Student user, int firstLine, int lastLine){
		List<Hint> hints = service.findByQuestionAndType(question.getId(), HintsType.TEXT.name());
		List<HintHistory> log = new ArrayList<>();
		
		log = hintHistoryService.findAllByUser(user);
		
		Hint hint;
		
		if(code.isEmpty() || code.equals("")){
			hint = getWhenCodeEmpty(code, hints, log);
			save(question, code, null, user, hint);
			return hint;
		} else {
			String partOfcode;
			
			if(firstLine!=0 && lastLine!=0){
				List<String> allLines = Arrays.asList(code.split("\n"));
				List<String> selectedLines = new ArrayList<>();
				for (int i = firstLine-1; i < lastLine; i++) {
					selectedLines.add(allLines.get(i));
				}
				partOfcode = String.join("\n", selectedLines);
			} else {
				partOfcode = code;
			}
			hint = getByPartOfCode(partOfcode, hints, log);
			save(question, code, partOfcode, user, hint);
			return hint;
		}
	}
	
	private Hint getByPartOfCode(String code, List<Hint> questionHints, List<HintHistory> log){
		List<Hint> selectHints = new ArrayList<>();
		double maxSim = 0;
		double sim;
		Hint selectHint = null;
		
		for (Hint hint : questionHints) {
			if(!(hint.partOfCode.isEmpty() || hint.partOfCode.equals(""))){
				selectHints.add(hint);
			}
		}

		for (HintHistory hintHistory : log) {
			selectHints.remove(hintHistory.getHint());
		}
		
		System.out.println("\n Code: " + code);
		for (Hint hint : selectHints) {
			sim = similarity.similarity(code, extrairTrecho(hint.getSolutionModel(), hint.getPartOfCode()));
			
			System.out.println("\n Id da Dica: " + hint.getId());
			System.out.println("\n Sim: " + sim);
			System.out.println("----------------------");
			
			if(sim >= maxSim){
				selectHint = hint;
				maxSim = sim;
			}
		}
		
		return selectHint;
	}
	
	private String extrairTrecho(String content, String trecho){
		String partOfcode;
		List<String> allLines = Arrays.asList(content.split("\n"));
		List<String> numeros = Arrays.asList(trecho.split("-"));
		List<String> selectedLines = new ArrayList<>();
		int firstLine;
		int lastLine;
		if(numeros.size()==1){
			firstLine = Integer.parseInt(numeros.get(0));
			selectedLines.add(allLines.get(firstLine-1));
		} else {
			firstLine = Integer.parseInt(numeros.get(0));
			lastLine = Integer.parseInt(numeros.get(1));
			for (int i = firstLine-1; i < lastLine; i++) {
				selectedLines.add(allLines.get(i));
			}
		}
		
		partOfcode = String.join("\n", selectedLines);
		System.out.println("\n Trecho: " + partOfcode);
		return partOfcode;
	}
	
	private Hint getWhenCodeEmpty(String code, List<Hint> questionHints, List<HintHistory> log){
		List<Hint> selectHints = new ArrayList<>();
			for (Hint hint : questionHints) {
				if(hint.partOfCode.isEmpty() || hint.partOfCode.equals("")){
					selectHints.add(hint);
				}
			}
			
			
			for (HintHistory hintHistory : log) {
				selectHints.remove(hintHistory.getHint());
			}
			
			if(selectHints.isEmpty()){
				return null;
			} else {
				return selectHints.get(0);
			}
	}
	
	private void save(Question question, String code, String partofSolution, Student user, Hint hint){
		HintHistory hintHistory = new HintHistory();
		hintHistory.setHint(hint);
		hintHistory.setStudent(user);
		hintHistory.setSession(null);
		hintHistory.setQuestion(question);
		hintHistory.setFinalSolution(null);
		hintHistory.setSolutionInProgress(code);
		hintHistory.setPartOfSolution(partofSolution);
		hintHistory.setFinished(false);
		hintHistory.setTime(Calendar.getInstance());
		hintHistoryService.save(hintHistory);
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

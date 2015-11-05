/**
 * 
 */
package its.domain.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import models.CorrectionType;
import models.QuestionType;
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
		
		if(question.getQuestionType().equals(QuestionType.UPLOAD)){
			return evaluateUploadQuestion(solution);
		}
		
		if(question.getCorrectionType().equals(CorrectionType.AUTOMATIC)){
			String correctAnswer = question.getAnswer();
			if(correctAnswer.equals(studentAnswer))
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	private boolean evaluateUploadQuestion(Solution solution){
		/**
		 * Repurar arquivo com casos de testes e extrair INPUT e OUTPUT
		 */
		Question question = solution.getQuestion();
		File arquivo = new File("public/output-codes/" + question.getAnswer());
		try( InputStream in = new FileInputStream(arquivo) ){
		  Scanner scan = new Scanner(in);
		  String output = "";
		  String input = "";
		  boolean startOutput = false;
		  
		  while( scan.hasNext() ){
			  String line = scan.nextLine();
			  if(line.equals("OUTPUT")){
				  startOutput = true;
			  }
			  if(startOutput)
				  output =  output + line + "\n";
			  else
				  input = input + line + "\n"; 
		  }
		  input = input.replaceAll("INPUT", "");
		  output = output.replaceAll("OUTPUT", "");
//		  System.out.println("Entrada: " + input);
//		  System.out.println("Saida: " + output);
		}catch(IOException ex){
		  ex.printStackTrace();
		}
		return false;
	}

}

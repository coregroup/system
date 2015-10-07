/**
 * 
 */
package controllers.resolution;

import its.domain.Evaluator;
import its.domain.impl.EvaluatorImpl;
import its.pedagogical.Planning;
import its.pedagogical.impl.PlanningImpl;

import java.util.Calendar;

import models.CorrectionType;
import models.curriculum.Question;
import models.curriculum.Solution;
import models.users.Student;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.QuestionService;
import services.questions.impl.QuestionServiceImpl;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

/**
 * @author Priscylla
 *
 */
public class ResolutionController extends Controller {
	
	private static DynamicForm form = Form.form();
	private static UserService userService = new UserServiceImpl();
	
	public static Result index(){
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		Question question = planning.nextQuestion();
		return ok(views.html.resolution.index.render(question, form));
	}
	
	public static Result submit(Long id){
		
		Form<Dynamic> requestForm = form.bindFromRequest();
    	String answer = requestForm.data().get("answer");
    	QuestionService questionService =  new QuestionServiceImpl();
    	Evaluator evaluator = new EvaluatorImpl();
    	SolutionService solutionService = new SolutionServiceImpl();
    	
    	
    	
    	if(answer == null || answer.equals("")){
    		DynamicForm formDeErro = form.fill(requestForm.data());
			formDeErro.reject("Você deve escrever uma resposta antes de submeter a solução.");
			flash("warning", "Você deve escrever uma resposta antes de submeter a solução.");
			return forbidden(views.html.resolution.index.render(questionService.findById(id), formDeErro));
    	}
    	
    	String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
    	Solution solution = new Solution();
    	solution.setAnswer(answer);
    	solution.setDate(Calendar.getInstance());
    	solution.setQuestion(questionService.findById(id));
    	solution.setUser(student);
    	boolean isCorrect = evaluator.evaluate(solution);
    	solution.setEvaluation(isCorrect);
    	solutionService.save(solution);
    	
    	if(solution.getQuestion().getCorrectionType().equals(CorrectionType.MANUAL)){
    		flash("success", "Sua resposta será corrigida MANUALMENTE pelo Professor."
    				+ "Tente agora essa nova questão!");
    		return redirect(controllers.resolution.routes.ResolutionController.next(id));
    	}
    	
    	if(isCorrect){
    		flash("success", "Você acertou a questão anterior! Tente agora essa nova questão!");
    		return redirect(controllers.resolution.routes.ResolutionController.next(id));
    		
    	} else {
    		DynamicForm formDeErro = form.fill(requestForm.data());
			formDeErro.reject("Resposta Incorreta.");
			flash("error", "Resposta Incorreta: " + answer + ". Tente Novamente.");
			return forbidden(views.html.resolution.index.render(questionService.findById(id), formDeErro));
    	}
	}
	
	public static Result next(Long id){
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		Question question = planning.nextQuestion(id);
		if(question == null){
			// Acabaram os exercícios
			return TODO;
		}
		return ok(views.html.resolution.index.render(question, form));
	}

}

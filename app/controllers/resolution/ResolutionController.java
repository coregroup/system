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
import play.mvc.Http.Request;
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
	
	public static Result listAll(){
		return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
	}
	
	public static Result listAllQuestions(int page, String sortBy, String order, String filter){
		QuestionService service = new QuestionServiceImpl();
		return ok(views.html.resolution.listQuestions.render(
				service.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result index(Long id){
		QuestionService service = new QuestionServiceImpl();
		Question question = service.findById(id);
		return ok(views.html.resolution.index.render(question, form));
	}
	
	public static Result submitCode(Long id){
		
		QuestionService questionService =  new QuestionServiceImpl();
		SolutionService solutionService = new SolutionServiceImpl();
		
		Form<Dynamic> requestForm = form.bindFromRequest();
		
		Request request = request();		
		String[] vetor = request.body().asFormUrlEncoded().get("code");
		String code = vetor[0];
		
		if(code == null || code.equals("")){
    		DynamicForm formDeErro = form.fill(requestForm.data());
			formDeErro.reject("Você deve escrever uma resposta antes de submeter a solução.");
			flash("warning", "Você deve escrever uma resposta antes de submeter a solução.");
			return redirect(controllers.resolution.routes.ResolutionController.index(id));
    	}
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
    	Solution solution = new Solution();
    	solution.setAnswer(code);
    	solution.setEndTime(Calendar.getInstance()); //TODO
    	solution.setQuestion(questionService.findById(id));
    	solution.setUser(student);
    	solution.setEvaluation(-1);//TODO NEGATIVO PARA SABER QUAIS QUESTÕES AINDA NÃO FORAM AVALIADAS MANUALMENTE (MUDAR APÓS JUIZ ONLINE)
    	solutionService.save(solution);
		
    	flash("info", "Sua questão será avaliada manualmente pelo professor");
		return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
	}
	
	/**
	 * 
	 * Antiga resolução que usava uma simulação de planejador pedagógico (apenas)
	 * escolhia as questões na sequencia em que eram retornadas do BD
	 */
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
    	solution.setEndTime(Calendar.getInstance()); //TODO
    	solution.setQuestion(questionService.findById(id));
    	solution.setUser(student);
    	boolean isCorrect = evaluator.evaluate(solution);
    	double evaluation = (isCorrect) ? 1 : 0; //TODO
    	solution.setEvaluation(evaluation); // TODO
    	if(solution.getQuestion().getCorrectionType().equals(CorrectionType.MANUAL)){
    		solution.setEvaluation(-1);
    	}
    	solutionService.save(solution);
    	
    	///////////////////////////////////////////////////////////////////////////////////////
    	if(solution.getQuestion().getCorrectionType().equals(CorrectionType.MANUAL)){
    		flash("success", "Sua resposta será corrigida MANUALMENTE pelo Professor."
    				+ "Tente agora essa nova questão!");
    		return redirect(controllers.resolution.routes.ResolutionController.next(id));
    	}
    	
    	if(isCorrect){
    		flash("success", "Você acertou a solução da questão! Agora escolha outra e continue aprendendo!");
    		return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
    		
    	} else {
			flash("error", "Resposta Incorreta! Tente Novamente.");
			return redirect(controllers.resolution.routes.ResolutionController.index(id));
    	}
	}
	
	public static Result next(Long id){
		QuestionService questionService = new QuestionServiceImpl();
		Question question = questionService.findById(id);
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		Question nextQuestion = planning.nextQuestion(question.getTopics().get(0), id);
		if(nextQuestion == null){
			flash("success", "Você finalizou todas as questões do módulo!");
			return redirect(controllers.pedagogical.routes.PlanningController.index());
		}
		return ok(views.html.resolution.index.render(nextQuestion, form));
//		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
//		Question question = planning.nextQuestion(id);
//		if(question == null){
//			// Acabaram os exercícios
//			return TODO;
//		}
//		return ok(views.html.resolution.index.render(question, form));
	}

}

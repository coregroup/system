/**
 * 
 */
package controllers.resolution;

import its.domain.Evaluator;
import its.domain.impl.EvaluatorImpl;
import its.feedback.Feedback;
import its.pedagogical.Planning;
import its.pedagogical.impl.PlanningImpl;

import java.util.Calendar;

import models.CorrectionType;
import models.curriculum.Hint;
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
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
		Feedback feedback = new Feedback();
		feedback.startLog(question, student);
		
		return ok(views.html.resolution.index.render(question, form, null));
	}
	
	public static Result submitCode(Long id){
		
		QuestionService questionService =  new QuestionServiceImpl();
		SolutionService solutionService = new SolutionServiceImpl();
		
		Form<Dynamic> requestForm = form.bindFromRequest();
		
		Request request = request();		
		String[] vetor = request.body().asFormUrlEncoded().get("code");
		String code = vetor[0];
		
		Question question = questionService.findById(id);
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
    	String[] firstLine = request.body().asFormUrlEncoded().get("firstLine");
    	String[] lastLine = request.body().asFormUrlEncoded().get("lastLine");
    	int line1 = 0;
    	int line2 = 0;
    	
    	if(!firstLine[0].equals("") && !lastLine[0].equals("")){
    		line1 = Integer.parseInt(firstLine[0]);
    		line2 = Integer.parseInt(lastLine[0]);
    	}
		
		///////////////////////////////////////////////
		String[] postAction = request().body().asFormUrlEncoded().get("action");
		  if (postAction == null || postAction.length == 0) {
			  return badRequest("You must provide a valid action");
		  } else {
		    String action = postAction[0];
		    if ("text".equals(action)) {
		    	//inicio do text
		    	System.out.println("\n" + "texto" + "\n");
		    	Feedback feedback = new Feedback();
		    	Hint hint = feedback.getTextFeedback(question, code, student, line1, line2);
		    	return forbidden(views.html.resolution.index.render(question, form, hint));
		    	//fim do text
		    } else if ("video".equals(action)) {
		    	System.out.println("\n" + "video" + "\n");
		    } else if ("flow".equals(action)){
		    	//inicio do flow
		    	Feedback feedback = new Feedback();
		    	Hint hint = feedback.getImageFeedback(question, code, student);
		    	return forbidden(views.html.resolution.index.render(question, form, hint));
		    	//fim do flow
		    	
		    } else if ("code".equals(action)){
		    	//inicio do code
		    	if(code == null || code.equals("")){
		    		DynamicForm formDeErro = form.fill(requestForm.data());
					formDeErro.reject("Você deve escrever uma resposta antes de submeter a solução.");
					flash("warning", "Você deve escrever uma resposta antes de submeter a solução.");
					return redirect(controllers.resolution.routes.ResolutionController.index(id));
		    	}
		    	
		    	Solution solution = new Solution();
		    	solution.setAnswer(code);
		    	solution.setEndTime(Calendar.getInstance()); //TODO
		    	solution.setQuestion(questionService.findById(id));
		    	solution.setUser(student);
		    	solution.setEvaluation(-1);//TODO NEGATIVO PARA SABER QUAIS QUESTÕES AINDA NÃO FORAM AVALIADAS MANUALMENTE (MUDAR APÓS JUIZ ONLINE)
		    	solutionService.save(solution);
		    	
		    	Feedback feedback = new Feedback();
		    	feedback.finishLog(solution.getQuestion(), student, solution);
				
		    	flash("info", "Sua questão será avaliada manualmente pelo professor");
				return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
				// Fim do code
		    } else {
		    	return badRequest("This action is not allowed");
		    }
		  }
		  return TODO;
		  ///////////////////////////////////////////////////////////
	}
	
	/*
	public static Result submitCodeFeedback(Long id, Long feedback){
		return TODO;
	}*/
	
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
			return forbidden(views.html.resolution.index.render(questionService.findById(id), formDeErro, null));
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
    	
    	
    	if(solution.getQuestion().getCorrectionType().equals(CorrectionType.MANUAL)){
    		flash("info", "Sua questão será avaliada manualmente pelo professor");
    		return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
    	}
    	
    	///////////////////////////////////////////////////////////////////////////////////////
    	if(isCorrect){
    		flash("success", "Você acertou a solução da questão! Agora escolha outra e continue aprendendo!");
    		return redirect(controllers.resolution.routes.ResolutionController.listAllQuestions(0, "name", "asc", ""));
    		
    	} else {
			flash("error", "Resposta Incorreta! Tente Novamente.");
			return redirect(controllers.resolution.routes.ResolutionController.index(id));
    	}
	}
	
	public static Result next(Long id){
		/*QuestionService questionService = new QuestionServiceImpl();
		Question question = questionService.findById(id);
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		Question nextQuestion = planning.nextQuestion(question.getTopics().get(0), id);
		if(nextQuestion == null){
			flash("success", "Você finalizou todas as questões do módulo!");
			return redirect(controllers.pedagogical.routes.PlanningController.index());
		}
		return ok(views.html.resolution.index.render(nextQuestion, form));*/
//		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
//		Question question = planning.nextQuestion(id);
//		if(question == null){
//			// Acabaram os exercícios
//			return TODO;
//		}
//		return ok(views.html.resolution.index.render(question, form));
		return TODO;
	}

}

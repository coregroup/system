/**
 * 
 */
package controllers.questions;

import static play.data.Form.form;

import java.util.List;

import controllers.routes;
import controllers.authentication.UserAuthenticatedSecured;
import models.curriculum.Hint;
import models.curriculum.Question;
import models.users.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.hints.HintService;
import services.hints.impl.HintServiceImpl;
import services.questions.QuestionService;
import services.questions.impl.QuestionServiceImpl;

/**
 * @author Priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class QuestionController extends Controller{
	
	public static Result index(){
		return redirect(controllers.questions.routes.QuestionController.list(0, "name", "asc", ""));
	}
	
	public static Result list(int page, String sortBy, String order, String filter){
		QuestionService service = new QuestionServiceImpl();
		return ok(views.html.question.list.render(
				service.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result details(Long id){
		QuestionService service = new QuestionServiceImpl();
		Question question = service.findById(id);
		HintService hintService = new HintServiceImpl();
		List<Hint> hints = hintService.findByQuestion(id);
		
		return ok(views.html.question.details.render(question, hints));
	}
	
	public static Result selectType(){
		return ok(views.html.question.selectType.render());
	}
	
	public static Result edit(Long id){
		QuestionService service = new QuestionServiceImpl();
		Question question = service.findById(id);
		Form<Question> questionForm = form(Question.class).fill(question);		
		return ok(views.html.question.edit.render(questionForm, question));
	}
	
	public static Result update(Long id){
		Form<Question> questionForm = form(Question.class).bindFromRequest();
		QuestionService service = new QuestionServiceImpl();
		Question question = service.findById(id);
		question.setName(questionForm.field("name").value());
		question.setStatement(questionForm.field("statement").value());
		service.update(question);
		
		return redirect(controllers.questions.routes.QuestionController.details(id));
	}

}

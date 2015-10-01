/**
 * 
 */
package controllers.resolution;

import models.curriculum.Question;
import pedagogical.Planning;
import pedagogical.impl.PlanningImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.questions.impl.QuestionRepositoryImpl;

/**
 * @author Priscylla
 *
 */
public class ResolutionController extends Controller {
	
	private static DynamicForm form = Form.form();
	
	public static Result index(){
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		Question question = planning.nextQuestion();
		return ok(views.html.resolution.index.render(question, form));
	}
	
	public static Result submit(){
		return TODO;
	}

}

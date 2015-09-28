/**
 * 
 */
package controllers.questions;

import models.curriculum.Question;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Priscylla
 *
 */
public class TextQuestionController extends Controller{
	
	private static Form<Question> eventoForm = Form.form(Question.class);
	
	public static Result create(){
		Form<Question> questionForm = eventoForm.bindFromRequest();
		return ok(views.html.question.text.newQuestion.render(questionForm));
	}
	
	public static Result save(){
		//Question question = formFromRequest.get();
		return TODO;
	}

}

/**
 * 
 */
package controllers.hints;

import static play.data.Form.form;

import models.curriculum.Hint;
import models.curriculum.Question;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.questions.QuestionService;
import services.questions.impl.QuestionServiceImpl;

/**
 * @author priscylla
 *
 */
public class HintsController extends Controller{
	
	public static Result create(Long id){
		QuestionService questionService = new QuestionServiceImpl();
		Question question = questionService.findById(id);
		Hint hint = new Hint();
		hint.setQuestion(question);
		
		Form<Hint> hintForm = form(Hint.class).fill(hint);
		
		return ok(views.html.hint.create.render(hintForm, question));
	}
	
	public static Result save(){
		return TODO;
	}

}

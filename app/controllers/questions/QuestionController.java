/**
 * 
 */
package controllers.questions;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Priscylla
 *
 */
public class QuestionController extends Controller{
	
	public static Result index(){
		return ok(views.html.question.selectType.render());
	}

}

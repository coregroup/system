/**
 * 
 */
package controllers.questions;

import controllers.authentication.UserAuthenticatedSecured;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * @author Priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class QuestionController extends Controller{
	
	public static Result index(){
		return ok(views.html.question.selectType.render());
	}

}

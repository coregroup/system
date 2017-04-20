/**
 * 
 */
package controllers.course;

import controllers.authentication.UserAuthenticatedSecured;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class ModuleController extends Controller {
	
	public static Result index(){
		return TODO;
	}
	
	public static Result create(){
		return TODO;
	}
	
	public static Result save(){
		return TODO;
	}

}

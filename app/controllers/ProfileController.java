/**
 * 
 */
package controllers;

import controllers.authentication.UserAuthenticatedSecured;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class ProfileController extends Controller{
	
	public static Result view(){
		return ok(views.html.profile.viewProfile.render());
	}

}

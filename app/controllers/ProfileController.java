/**
 * 
 */
package controllers;

import models.users.User;
import controllers.authentication.UserAuthenticatedSecured;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class ProfileController extends Controller{
	
	private static UserService userService = new UserServiceImpl();
	
	public static Result view(){
		String email = session().get("email");
		User user = userService.findByEmail(email);
		return ok(views.html.profile.viewProfile.render(user));
	}
	
//	public static Result edit(){
//		String email = session().get("email");
//		User user = userService.findByEmail(email);
//		return ok(views.html.profile.edit.render(user));
//	}

}

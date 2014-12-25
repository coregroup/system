/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

/**
 * @author priscylla
 *
 */
public class LoginController extends Controller{
    
    public static Result login() {
    	return ok(login.render());
    }

}

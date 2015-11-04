/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import controllers.authentication.UserAuthenticatedSecured;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class DashboardController extends Controller {
	
    public static Result studentDashboard() {
		
    	return ok(views.html.dashboard.studentDashboard.render());
    }

}

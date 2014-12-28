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
public class DashboardController extends Controller {
	
    public static Result studentDashboard() {
    	return ok(views.html.dashboard.studentDashboard.render());
    }

}

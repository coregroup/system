/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.users.UserService;
import services.users.impl.UserServiceImpl;
import controllers.authentication.UserAuthenticatedSecured;
import models.users.Student;
import models.users.User;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class DashboardController extends Controller {
	
	private static UserService userService = new UserServiceImpl();
	
	/**
	 * Este método verifica se o usuário é professor ou aluno e encaminha para
	 * o dashboard correto
	 * @return
	 */
	public static Result dashboard(){
		String email = session().get("email");
		User user = userService.findByEmail(email);
		
		if (user instanceof Student){
			return redirect(controllers.routes.DashboardController.studentDashboard());
		}
		return TODO; //DASHBOARD do professor ainda não implementada
	}
	
    public static Result studentDashboard() {
    	return ok(views.html.dashboard.student.index.render());
    	//return ok(views.html.dashboard.studentDashboard.render());
    }
    
    public static Result teacherDashboard(){
    	return TODO;
    }

}

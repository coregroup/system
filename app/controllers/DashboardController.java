/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.users.impl.StudentRepositoryImpl;
import services.module.ModuleService;
import services.module.impl.ModuleServiceImpl;
import services.users.StudentService;
import services.users.UserService;
import services.users.impl.StudentServiceImpl;
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
	private static StudentService studentService;
	
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
		} else {
			return redirect(controllers.routes.DashboardController.teacherDashboard());
		}
		
	}
	
    public static Result studentDashboard() {
    	String email = session().get("email");
    	studentService = new StudentServiceImpl(new StudentRepositoryImpl());
    	
    	ModuleService moduleService = new ModuleServiceImpl(); 
    	
		Student student = studentService.findByEmail(email);
		
    	return ok(views.html.dashboard.student.index.render(student, moduleService.findAll()));
    	
    }
    
    public static Result teacherDashboard(){
    	return ok(views.html.dashboard.teacher.index.render());
    	//return TODO;
    }

}

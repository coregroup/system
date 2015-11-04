package controllers.pedagogical;

import models.users.Student;
import models.users.User;
import play.mvc.Controller;
import play.mvc.Result;
import services.users.UserService;
import services.users.impl.UserServiceImpl;
import views.html.profile.viewProfile;

public class PlanningController extends Controller{
	
	private static UserService userService = new UserServiceImpl();
	
	public static Result index(){
//		String email = session().get("email");
//		User user = userService.findByEmail(email);
//		Student student = (Student) user;
//		System.out.println("\n*******************************\n");
//		System.out.println(student.getFullname());
//		System.out.println(student.getSessions().get(0));
//		System.out.println("\n*******************************\n");
		return ok(views.html.pedagogical.index.render());
	}

}

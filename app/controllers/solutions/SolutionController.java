package controllers.solutions;

import models.curriculum.Solution;
import models.users.Student;
import play.mvc.Controller;
import play.mvc.Result;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

public class SolutionController extends Controller{
	
	public static Result listAllSolutions(){
		return redirect(controllers.solutions.routes.SolutionController.listSolutions(0, "id", "asc", ""));
		//return TODO;
	}
	
	public static Result listSolutions(int page, String sortBy, String order, String filter){
		SolutionService service = new SolutionServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
		return ok(views.html.solution.list.render(
				service.page(page, 10, sortBy, order, filter, student.getId()), sortBy, order, filter));
	}
	
	public static Result listAllUncorrectSolutions(){
		return redirect(controllers.solutions.routes.SolutionController.listUncorrectSolutions(0, "id", "asc", ""));
	}
	
	public static Result listUncorrectSolutions(int page, String sortBy, String order, String filter){
		SolutionService service = new SolutionServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
		return ok(views.html.solution.list.render(
				service.page(page, 10, sortBy, order, filter, student.getId(), -1), sortBy, order, filter));
	}
	
	public static Result details(Long id){
		SolutionService service = new SolutionServiceImpl();
		Solution solution = service.findById(id);
		return ok(views.html.solution.details.render(solution));
		//return TODO;
	}

}

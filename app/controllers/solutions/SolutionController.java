package controllers.solutions;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.curriculum.Question;
import models.curriculum.Solution;
import models.users.Student;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

@Security.Authenticated(UserAuthenticatedSecured.class)
public class SolutionController extends Controller{
	
	public static Result listAllSolutions(){
		SolutionService service = new SolutionServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
    	List<Solution> solutions = service.findByUser(student.getId());
    	
    	List<Question> questions = new ArrayList<>();
    	for (Solution solution : solutions) {
			if(!questions.contains(solution.question)){
				questions.add(solution.question);
			}
		}
    	
    	List<Solution> lastSolutions = new ArrayList<>();
    	boolean add = true;
    	
    	for (int i = solutions.size()-1; i >= 0 ; i--) {
			for (Solution solution : lastSolutions) {
				if(solution.getQuestion().getId().equals(solutions.get(i).getQuestion().getId())){
					add = false;
				}
			}
			if(add){
				lastSolutions.add(solutions.get(i));
			}
			add=true;
		}
    	
    	return ok(views.html.solution.listAll.render(lastSolutions, questions));
	}
	
	public static Result detailsAll(Long id){
		SolutionService service = new SolutionServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
    	List<Solution> allSolutions = service.findByUser(student.getId());
    	List<Solution> selectedSolutions = new ArrayList<>();
    	
    	for (Solution solution : allSolutions) {
			if(solution.getQuestion().getId().equals(id)){
				selectedSolutions.add(solution);
			}
		}
    	
    	return ok(views.html.solution.detailsAll.render(selectedSolutions));
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
	}

}

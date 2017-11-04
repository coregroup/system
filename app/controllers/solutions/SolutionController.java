package controllers.solutions;

import models.curriculum.Solution;
import play.mvc.Controller;
import play.mvc.Result;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;

public class SolutionController extends Controller{
	
	public static Result listAllSolutions(){
		//return redirect(controllers.resolution.routes.SolutionController.listSolutions(0, "id", "asc", ""));
		return TODO;
	}
	
	public static Result listSolutions(int page, String sortBy, String order, String filter){
		/*SolutionService service = new SolutionServiceImpl();
		return ok(views.html.correction.list.render(
				service.page(page, 10, sortBy, order, filter), sortBy, order, filter));*/
		return TODO;
	}
	
	public static Result listAllUncorrectSolutions(){
		//return redirect(controllers.resolution.routes.TestCorrectionController.listUncorrectSolutions(0, "id", "asc", ""));
		return TODO;
	}
	
	public static Result listUncorrectSolutions(int page, String sortBy, String order, String filter){
		/*SolutionService service = new SolutionServiceImpl();
		return ok(views.html.correction.list.render(
				service.pageUncorrected(page, 10, sortBy, order, filter), sortBy, order, filter));*/
		return TODO;
	}
	
	public static Result details(Long id){
		/*SolutionService service = new SolutionServiceImpl();
		Solution solution = service.findById(id);
		return ok(views.html.correction.details.render(solution));*/
		return TODO;
	}

}

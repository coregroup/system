/**
 * 
 */
package controllers.resolution;

import controllers.authentication.UserAuthenticatedSecured;
import models.curriculum.Solution;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class TestCorrectionController extends Controller{
	
	public static Result listAllSolutions(){
		return redirect(controllers.resolution.routes.TestCorrectionController.listSolutions(0, "id", "asc", ""));
	}
	
	public static Result listSolutions(int page, String sortBy, String order, String filter){
		SolutionService service = new SolutionServiceImpl();
		return ok(views.html.correction.list.render(
				service.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result listAllUncorrectSolutions(){
		return redirect(controllers.resolution.routes.TestCorrectionController.listUncorrectSolutions(0, "id", "asc", ""));
	}
	
	public static Result listUncorrectSolutions(int page, String sortBy, String order, String filter){
		SolutionService service = new SolutionServiceImpl();
		return ok(views.html.correction.list.render(
				service.pageUncorrected(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result details(Long id){
		SolutionService service = new SolutionServiceImpl();
		Solution solution = service.findById(id);
		return ok(views.html.correction.details.render(solution));
	}
	
	public static Result submitCorrect(Long id){
		SolutionService service = new SolutionServiceImpl();
		Solution solution = service.findById(id);
		solution.setEvaluation(1);
		service.update(solution);
		flash("info", "Avaliação da solução realizada!");
		return redirect(controllers.resolution.routes.TestCorrectionController.listUncorrectSolutions(0, "id", "asc", ""));
	}
	
	public static Result submitIncorrect(Long id){
		SolutionService service = new SolutionServiceImpl();
		Solution solution = service.findById(id);
		solution.setEvaluation(0);
		service.update(solution);
		flash("info", "Avaliação da solução realizada!");
		return redirect(controllers.resolution.routes.TestCorrectionController.listUncorrectSolutions(0, "id", "asc", ""));
	}

}

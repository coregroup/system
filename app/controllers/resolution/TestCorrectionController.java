/**
 * 
 */
package controllers.resolution;

import play.mvc.Controller;
import play.mvc.Result;
import services.solutions.SolutionService;
import services.solutions.impl.SolutionServiceImpl;

/**
 * @author priscylla
 *
 */
public class TestCorrectionController extends Controller{
	
	
	public static Result listAllUncorrectSolutions(){
		return redirect(controllers.resolution.routes.TestCorrectionController.listUncorrectSolutions(0, "id", "asc", ""));
	}
	
	public static Result listUncorrectSolutions(int page, String sortBy, String order, String filter){
		SolutionService service = new SolutionServiceImpl();
		return ok(views.html.correction.uncorrected.list.render(
				service.pageUncorrected(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result details(Long id){
		return TODO;
	}

}

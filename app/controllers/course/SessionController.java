/**
 * 
 */
package controllers.course;

import play.mvc.Controller;
import play.mvc.Result;
import services.sessions.SessionService;
import services.sessions.impl.SessionServiceImpl;

/**
 * @author priscylla
 *
 */
public class SessionController extends Controller {
	
	public static Result index(){
		return redirect(controllers.course.routes.SessionController.list(0, "name", "asc", ""));
	}
	
	public static Result list(int page, String sortBy, String order, String filter){
		SessionService service = new SessionServiceImpl();
		return ok(views.html.session.list.render(
				service.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
			
	}

}

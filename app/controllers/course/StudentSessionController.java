/**
 * 
 */
package controllers.course;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author priscylla
 *
 */
public class StudentSessionController extends Controller {
	
	public static Result index(Long id){
		return ok(views.html.session.student.index.render(id));
	}

}

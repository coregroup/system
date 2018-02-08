/**
 * 
 */
package controllers.hints;

import java.util.List;

import models.its.HintHistory;
import models.users.Student;
import play.mvc.Controller;
import play.mvc.Result;
import services.its.feedback.HintHistoryService;
import services.its.feedback.impl.HintHistoryServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

/**
 * @author priscylla
 *
 */
public class FeedbackController extends Controller{
	
	private static UserService userService = new UserServiceImpl();
	
	public static Result getRecords(Long id){
		
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
    	
		HintHistoryService hintHistoryService = new HintHistoryServiceImpl();
		List<HintHistory> records = hintHistoryService.findAllByUserAndQuestion(student, id);
		
		return ok(views.html.resolution.hint.records.render(records));
	}
	
}

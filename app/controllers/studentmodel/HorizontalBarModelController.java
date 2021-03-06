package controllers.studentmodel;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.its.StudentModel;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.its.studentmodel.StudentModelService;
import services.its.studentmodel.impl.StudentModelServiceImpl;

@Security.Authenticated(UserAuthenticatedSecured.class)
public class HorizontalBarModelController extends Controller {

	public static Result index() {
		List<Double> value = new ArrayList<>();
		StudentModelService service = new StudentModelServiceImpl();
		StudentModel studentModel =  service.findById(new Long(1));
		
		for(int i = 0; i < studentModel.studentModelUnits.size(); i++) {
			value.add(studentModel.studentModelUnits.get(i).getMastered() * 100);
		}
		
		return ok(views.html.studentmodel.horizontalBar.render(studentModel, value));

		
	}
		
}

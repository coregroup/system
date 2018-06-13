package controllers.studentmodel;

import controllers.authentication.UserAuthenticatedSecured;
import models.its.StudentModel;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.its.studentmodel.StudentModelService;
import services.its.studentmodel.impl.StudentModelServiceImpl;

@Security.Authenticated(UserAuthenticatedSecured.class)
public class GaugeModelController extends Controller{

	public static Result index() {
		StudentModelService service = new StudentModelServiceImpl();
		StudentModel studentModel = service.findById(new Long(1));
		return ok(views.html.studentmodel.gauge.render(studentModel));
	}
	
	
}

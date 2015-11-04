package controllers.course;

import models.course.Course;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class CourseController extends Controller{
	
	private static Form<Course> courseForm = Form.form(Course.class);
	
	public static Result create(){
		Form<Course> form = courseForm.bindFromRequest();
		return ok(views.html.course.create.render(form));
	}
	
	public static Result save(){
		return TODO;
	}

}

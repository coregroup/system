package controllers.course;

import static play.data.Form.form;

import models.course.Course;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.course.CourseService;
import services.course.impl.CourseServiceImpl;

public class CourseController extends Controller{
	
	private static Form<Course> courseForm = Form.form(Course.class);	
	
	public static Result index(){
		return redirect(controllers.course.routes.CourseController.list(0, "name", "asc", ""));
	}
	
	public static Result list(int page, String sortBy, String order, String filter){
		CourseService service = new CourseServiceImpl();		
		return ok(views.html.course.list.render(
				service.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result create(){
		Form<Course> form = courseForm.bindFromRequest();
		return ok(views.html.course.create.render(form));
	}
	
	public static Result save(){
		Form<Course> courseForm = form(Course.class).bindFromRequest();
		if(courseForm.hasErrors()) {
            return badRequest(views.html.course.create.render(courseForm));
        }
		CourseService service = new CourseServiceImpl();
		service.save(courseForm.get());
        
        flash("success", "Curso cadastrado com sucesso");

		return redirect(controllers.course.routes.CourseController.index());
	}

}

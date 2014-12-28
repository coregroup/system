/**
 * 
 */
package controllers;

import static play.data.Form.form;
import converters.ConvertPasswordToSHA;
import models.users.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.users.impl.TeacherRepositoryImpl;
import services.users.TeacherService;
import services.users.impl.TeacherServiceImpl;
import views.html.signupTeacher;

/**
 * @author priscylla
 *
 */
public class TeacherController extends Controller {
	
	private static TeacherService teacherService = new TeacherServiceImpl(new TeacherRepositoryImpl());
	
	/**
     * Display the 'new teacher form'.
     */
	public static Result signupTeacher() {
		Form<Teacher> teacherForm = form(Teacher.class);
    	return ok(signupTeacher.render(teacherForm));
    }
	
    /**
     * Handle the 'new teacher form' submission 
     */
    public static Result save() {
        Form<Teacher> teacherForm = form(Teacher.class).bindFromRequest();
        
        if (!teacherForm.field("password").valueOr("").isEmpty()) {
			if (!teacherForm.field("password").valueOr("")
					.equals(teacherForm.field("password_confirm").value())) {
				teacherForm.reject("password", "A senha não confere.");
			}
		}
        
        if(teacherForm.hasErrors()) {
        	
            return badRequest(signupTeacher.render(teacherForm));
        }
        
        teacherForm.get().setPassword(ConvertPasswordToSHA.convert(teacherForm.get().getPassword()));
        teacherService.save(teacherForm.get());
        flash("success", "Professor(a) " + teacherForm.get().fullname + " cadastrado(a) com sucesso!");
        return redirect(routes.LoginController.login());
    }

}

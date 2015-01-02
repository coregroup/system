/**
 * 
 */
package controllers;

import static play.data.Form.form;

import java.text.ParseException;

import models.Gender;
import models.State;
import models.users.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.users.impl.TeacherRepositoryImpl;
import services.users.TeacherService;
import services.users.UserService;
import services.users.impl.TeacherServiceImpl;
import services.users.impl.UserServiceImpl;
import views.html.signupTeacher;
import converters.ConvertPasswordToSHA;
import converters.Html5CalendarFormatter;

/**
 * @author priscylla
 *
 */
public class TeacherController extends Controller {
	
	private static TeacherService teacherService = new TeacherServiceImpl(new TeacherRepositoryImpl());
	private static UserService userService = new UserServiceImpl();
	
	/**
     * Display the 'new teacher form'.
     */
	public static Result signupTeacher() {
		Form<Teacher> teacherForm = form(Teacher.class);
    	return ok(signupTeacher.render(teacherForm));
    }
	
	public static Result delete(){
		String email = session().get("email");
		Teacher teacher = (Teacher) userService.findByEmail(email);
		
		teacherService.delete(teacher);
		session().clear();
		flash("success", "Perfil excluido com sucesso!");
		return redirect(routes.LoginController.login());
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
    
    public static Result edit(){
    	String email = session().get("email");
		Teacher teacher = (Teacher) userService.findByEmail(email);
		
    	Form<Teacher> teacherForm = form(Teacher.class).fill(teacher);
    	return ok(views.html.profile.editProfileTeacher.render(teacherForm));
    }
    
    public static Result update(){
    	Form<Teacher> teacherForm = form(Teacher.class).bindFromRequest();
    	
    	if (teacherForm.field("fullname").valueOr("").isEmpty()) {
    		teacherForm.reject("fullname", "Este campo é obrigatório");
    		return badRequest(views.html.profile.editProfileTeacher.render(teacherForm));
		}
    	if (teacherForm.field("email").valueOr("").isEmpty()) {
    		teacherForm.reject("email", "Este campo é obrigatório");
    		return badRequest(views.html.profile.editProfileTeacher.render(teacherForm));
		}
    	
    	String email = session().get("email");
		Teacher teacher = (Teacher) userService.findByEmail(email);
		Html5CalendarFormatter calendarConvert = new Html5CalendarFormatter();
    	
		teacher.setFullname(teacherForm.field("fullname").value());
		teacher.setEmail(teacherForm.field("email").value());
		try {
			teacher.setDateOfBirth(calendarConvert.parse(teacherForm.field("dateOfBirth").value(), null));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teacher.setState(State.getState(teacherForm.field("state").value()));
		teacher.setGender(Gender.getGender(teacherForm.field("gender").value()));
		
		teacherService.update(teacher);
		
        flash("success", "Perfil atualizado com sucesso!");
        return redirect(routes.ProfileController.view());
    }

}

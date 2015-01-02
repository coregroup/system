/**
 * 
 */
package controllers;

import static play.data.Form.form;

import java.text.ParseException;

import converters.ConvertPasswordToSHA;
import converters.Html5CalendarFormatter;
import models.Gender;
import models.State;
import models.users.Student;
import models.users.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.users.impl.StudentRepositoryImpl;
import services.users.StudentService;
import services.users.UserService;
import services.users.impl.StudentServiceImpl;
import services.users.impl.UserServiceImpl;
import views.html.signupStudent;

/**
 * @author priscylla
 *
 */
public class StudentController extends Controller {
	
	private static StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
	private static UserService userService = new UserServiceImpl();
	
	/**
     * Display the 'new student form'.
     */
	public static Result signupStudent() {
		Form<Student> studentForm = form(Student.class);
    	return ok(signupStudent.render(studentForm));
    }
	
    /**
     * Handle the 'new student form' submission 
     */
    public static Result save() {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        
        if (!studentForm.field("password").valueOr("").isEmpty()) {
			if (!studentForm.field("password").valueOr("")
					.equals(studentForm.field("password_confirm").value())) {
				studentForm.reject("password", "A senha não confere.");
			}
		}
        
        if(studentForm.hasErrors()) {
            return badRequest(signupStudent.render(studentForm));
        }
        
        studentForm.get().setPassword(ConvertPasswordToSHA.convert(studentForm.get().getPassword()));
        studentService.save(studentForm.get());
        flash("success", "Estudante " + studentForm.get().fullname + " cadastrado(a) com sucesso!");
        return redirect(routes.LoginController.login());
    }
    
	
    public static Result edit(){
    	String email = session().get("email");
		Student user = (Student) userService.findByEmail(email);
		
    	Form<Student> studentForm = form(Student.class).fill(user);
    	return ok(views.html.profile.editProfileStudent.render(studentForm));
    }
    
    public static Result update(){
    	Form<Student> studentForm = form(Student.class).bindFromRequest();
    	
    	if (studentForm.field("fullname").valueOr("").isEmpty()) {
    		studentForm.reject("fullname", "Este campo é obrigatório");
    		return badRequest(views.html.profile.editProfileStudent.render(studentForm));
		}
    	if (studentForm.field("email").valueOr("").isEmpty()) {
    		studentForm.reject("email", "Este campo é obrigatório");
    		return badRequest(views.html.profile.editProfileStudent.render(studentForm));
		}
    	
    	String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		Html5CalendarFormatter calendarConvert = new Html5CalendarFormatter();
    	
		student.setFullname(studentForm.field("fullname").value());
		student.setEmail(studentForm.field("email").value());
		try {
			student.setDateOfBirth(calendarConvert.parse(studentForm.field("dateOfBirth").value(), null));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.setState(State.getState(studentForm.field("state").value()));
		student.setGender(Gender.getGender(studentForm.field("gender").value()));
		
		studentService.update(student);
		
        flash("success", "Perfil atualizado com sucesso!");
        return redirect(routes.ProfileController.view());
    }
//    
//    
//    public static Result edit() {
//    	
//    	String email = session().get("email");
//		User user = userService.findByEmail(email);
//		
//        Form<Student> studentForm = form(Student.class).bindFromRequest();
//        	
//        if(studentForm.hasErrors()) {
//            return badRequest(editProfileStudent.render(studentForm));
//        }
//        
//        studentService.update(studentForm.get());
//        
//        flash("success", "Perfil atualizado com sucesso!");
//        return redirect(routes.ProfileController.view());
//    }
    
    

}

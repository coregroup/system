/**
 * 
 */
package controllers;

import static play.data.Form.form;
import models.users.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.users.impl.StudentRepositoryImpl;
import services.users.StudentService;
import services.users.impl.StudentServiceImpl;
import views.html.signupStudent;

/**
 * @author priscylla
 *
 */
public class StudentController extends Controller {
	
	private static StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
	
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
        
        studentService.save(studentForm.get());
        flash("success", "Estudante " + studentForm.get().fullname + " cadastrado(a) com sucesso!");
        return redirect(routes.LoginController.login());
    }
    
    /**
     * Handle computer deletion
     */
//    public static Result delete(Long id) {
//        Student.find.ref(id).delete();
//        flash("success", "Computer has been deleted");
//        return GO_HOME;
//    }

}

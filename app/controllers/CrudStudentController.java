/**
 * 
 */
package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.*;
import views.html.*;
import models.users.*;;

/**
 * @author priscylla
 *
 */
public class CrudStudentController extends Controller{
	
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
        
        if (studentForm.field("fullname").valueOr("").isEmpty()) {
        	studentForm.reject("fullname", "Este campo é obrigatório");
		}
        
        if (!studentForm.field("password").valueOr("").isEmpty()) {
			if (!studentForm.field("password").valueOr("")
					.equals(studentForm.field("password_confirm").value())) {
				studentForm.reject("password", "A senha não confere.");
			}
		}
        
        if(studentForm.hasErrors()) {
        	
            return badRequest(signupStudent.render(studentForm));
        }
        studentForm.get().save();
        flash("success", "Estudante " + studentForm.get().fullname + " cadastrado(a) com sucesso!");
        return redirect(routes.Application.login());
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

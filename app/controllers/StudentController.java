/**
 * 
 */
package controllers;

import static play.data.Form.form;

import java.text.ParseException;

import models.Gender;
import models.State;
import models.users.Student;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.users.impl.StudentRepositoryImpl;
import services.users.StudentService;
import services.users.UserService;
import services.users.impl.StudentServiceImpl;
import services.users.impl.UserServiceImpl;
import views.html.signupStudent;
import controllers.authentication.UserAuthenticatedSecured;
import converters.ConvertPasswordToSHA;
import converters.Html5CalendarFormatter;

/**
 * @author priscylla
 *
 */
public class StudentController extends Controller {
	
	private static StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
	private static UserService userService = new UserServiceImpl();
	private static DynamicForm form = Form.form();
	
	/**
     * Display the 'new student form'.
     */
	public static Result signupStudent() {
		Form<Student> studentForm = form(Student.class);
    	return ok(signupStudent.render(studentForm));
    }
	
	@Security.Authenticated(UserAuthenticatedSecured.class)
	@Transactional
	public static Result delete() {
		String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		
		studentService.delete(student);
		session().clear();
		flash("success", "Perfil excluido com sucesso!");
		return redirect(routes.LoginController.login());
	}
	
    /**
     * Handle the 'new student form' submission 
     */
	@Transactional
    public static Result save() {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        
        if(userService.findByEmail(studentForm.field("email").valueOr(""))!=null){
        	studentForm.reject("email", "Já existe um usuário cadastrado com esse email.");
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
        
        studentForm.get().setPassword(ConvertPasswordToSHA.convert(studentForm.get().getPassword()));
        studentForm.get().setModel("5,5,5,5,5");//TODO
        studentService.save(studentForm.get());
        flash("success", "Estudante " + studentForm.get().fullname + " cadastrado(a) com sucesso!");
        return redirect(routes.LoginController.login());
    }
    
    @Security.Authenticated(UserAuthenticatedSecured.class)
    @Transactional
    public static Result edit(){
    	String email = session().get("email");
		Student user = (Student) userService.findByEmail(email);
		
    	Form<Student> studentForm = form(Student.class).fill(user);
    	return ok(views.html.profile.editProfileStudent.render(studentForm));
    }
    
    @Security.Authenticated(UserAuthenticatedSecured.class)
    @Transactional
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
		student.setTurma(studentForm.field("turma").value());
		
		studentService.update(student);
		
        flash("success", "Perfil atualizado com sucesso!");
        return redirect(routes.ProfileController.view());
    }

    @Security.Authenticated(UserAuthenticatedSecured.class)
    public static Result editPassword(){
    	return ok(views.html.profile.changePasswordStudent.render(form));
    }
    
    @Security.Authenticated(UserAuthenticatedSecured.class)
    @Transactional
    public static Result updatePassword(){
    	Form<Dynamic> requestForm = form.bindFromRequest();
    	String currentPassword = requestForm.data().get("currentPassword");
    	String newPassword = requestForm.data().get("newPassword");
    	String confirmPassword = requestForm.data().get("confirmPassword");
    	
    	String email = session().get("email");
		Student user = (Student) userService.findByEmail(email);
    	
		if(newPassword.equals(confirmPassword)) {
			if(ConvertPasswordToSHA.convert(currentPassword).equals(user.getPassword())){
				user.setPassword(ConvertPasswordToSHA.convert(newPassword));
				studentService.update(user);
			}
		} else {
			DynamicForm formDeErro = form.fill(requestForm.data());
    		formDeErro.reject("As senhas não conferem");
    		return forbidden(views.html.login.render(formDeErro));
		}
    	
    	flash("success", "Perfil atualizado com sucesso!");
        return redirect(routes.ProfileController.view());
    }
}

/**
 * 
 */
package controllers.course;

import static play.data.Form.form;

import java.util.Calendar;

import ch.qos.logback.core.net.SyslogOutputStream;
import models.course.Session;
import models.users.Student;
import models.users.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.users.impl.StudentRepositoryImpl;
import services.course.CourseService;
import services.course.impl.CourseServiceImpl;
import services.sessions.SessionService;
import services.sessions.impl.SessionServiceImpl;
import services.users.StudentService;
import services.users.UserService;
import services.users.impl.StudentServiceImpl;
import services.users.impl.UserServiceImpl;

/**
 * @author priscylla
 *
 */
public class SessionController extends Controller {
	
	private static Form<Session> sessionForm = Form.form(Session.class);
	
	public static Result index(){
		return redirect(controllers.course.routes.SessionController.list(0, "name", "asc", ""));
	}
	
	public static Result list(int page, String sortBy, String order, String filter){
		SessionService service = new SessionServiceImpl();
		return ok(views.html.session.list.render(
				service.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
			
	}
	
	public static Result create(){
		Form<Session> form = sessionForm.bindFromRequest();
		
		CourseService service = new CourseServiceImpl();
		
		return ok(views.html.session.create.render(form, service.findAll()));
	}
	
	public static Result save(){

		Form<Session> sForm = form(Session.class).bindFromRequest();
		CourseService courseService = new CourseServiceImpl();
		
		if(sForm.field("name").valueOr("").isEmpty() || sForm.field("course").valueOr("").isEmpty()){
			sForm.reject("Todos os campos do formulário devem ser preenchidos");
			 return badRequest(views.html.session.create.render(sForm, courseService.findAll()));
		}
		
		
		Session newSession = new Session();
		newSession.setName(sForm.field("name").value().toString());
			
		Long idCourse = Long.parseLong(sForm.field("course").value().toString());
		newSession.setCourse(courseService.findById(idCourse));
		newSession.setStart(Calendar.getInstance());
		newSession.setEnd(Calendar.getInstance());
		UserService userService = new UserServiceImpl();
		String email = session().get("email");
		Teacher teacher = (Teacher) userService.findByEmail(email);
		newSession.setTeacher(teacher);
			
		SessionService sessionService = new SessionServiceImpl();
		sessionService.save(newSession);
			
		flash("success", "Sessão cadastrada com sucesso");

		return redirect(controllers.course.routes.SessionController.list(0, "name", "asc", ""));
	}

	public static Result studentList(Long id){
		return redirect(controllers.course.routes.SessionController.studentAdd(id, 0, "fullname", "asc", ""));
	}
	
	public static Result studentAdd(Long id, int page, String sortBy, String order, String filter){
		StudentService service = new StudentServiceImpl(new StudentRepositoryImpl());
		return ok(views.html.session.students.render(id, service.page(page, 10, sortBy, order, filter) , sortBy, order, filter));
	}
	
	public static Result studentSave(Long id, String email){
		
		StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
		SessionService sessionService = new SessionServiceImpl();
		Session s = sessionService.findById(id);
		
		Student student = studentService.findByEmail(email);		
		student.sessions.add(s);
		student.setActive(true);
		studentService.update(student);
		
		flash("success", "O aluno " + student.getFullname() + " foi adicionado a turma " + s.name + " com sucesso!");
		
		return redirect(controllers.course.routes.SessionController.studentAdd(id, 0, "fullname", "asc", ""));
	}
	
}

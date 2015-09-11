/**
 * 
 */
package controllers;

import java.util.Calendar;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import models.curriculum.question.Question;
import models.users.Student;
import play.data.DynamicForm;
import play.data.Form;
import play.data.DynamicForm.Dynamic;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.users.impl.StudentRepositoryImpl;
import services.users.StudentService;
import services.users.UserService;
import services.users.impl.StudentServiceImpl;
import services.users.impl.UserServiceImpl;
import controllers.authentication.UserAuthenticatedSecured;
import experimento.BinaryOp;
import experimento.ConvFromDec;
import experimento.ConvToDec;
import experimento.StudentModel;
import experimento.dicas.SelecaoDicas;
import experimento.dicas.ServiceLog;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class ExperimentoController extends Controller{
	
	private static DynamicForm form = Form.form();
	private static UserService userService = new UserServiceImpl();
	private static StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
	
	@Transactional
	public static Result index(){
		
		Question question = null;
		TypedQuery<Question> query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.id = :id", Question.class);
		try{
			question = query.setParameter("id", (long) 1).getSingleResult();
		} catch (NoResultException e){
			return TODO;
		}
		
		String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		StudentModel modelo = new StudentModel();
		ServiceLog log = new ServiceLog();
		log.save(question, student, null, false, null, Calendar.getInstance(), ""+modelo.getValue(student), false);
		
		return ok(views.html.experimento.question.render(question, form, new Integer(10)));
	}
	
	@Transactional
	public static Result submeter(Long id, Integer valor){
		Form<Dynamic> requestForm = form.bindFromRequest();
    	String solucaoEstudante = requestForm.data().get("solution");
    	String resp = "";
    	
		Question question = null;
		TypedQuery<Question> query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.id = :id", Question.class);
		try{
			question = query.setParameter("id", id).getSingleResult();
			resp = question.getSolution();

			////////// O que fazer se a solução estiver correta
			if(solucaoEstudante.equals(resp)){
				String email = session().get("email");
				Student student = (Student) userService.findByEmail(email);
				StudentModel modelo = new StudentModel();
				student.setModel(modelo.newModel(student, valor));
				studentService.update(student);
				
				ServiceLog log = new ServiceLog();
				log.save(question, student, solucaoEstudante, true, null, Calendar.getInstance(), ""+modelo.getValue(student), false);
				
				flash("success", "Você acertou a questão anterior! Tente agora essa nova questão!");
				Long nextId = (long)(id.intValue() + 1);
				return redirect(routes.ExperimentoController.next(nextId));
			}
			/////////
			
		} catch (NoResultException e){
			return TODO;
		}
		
		//O que fazer em caso de resposta errada
		String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		StudentModel modelo = new StudentModel();
		ServiceLog log = new ServiceLog();
		log.save(question, student, solucaoEstudante, false, null, Calendar.getInstance(), ""+modelo.getValue(student),false);
		
		
		DynamicForm formDeErro = form.fill(requestForm.data());
		formDeErro.reject("Resposta Incorreta. Tente Novamente.");
		flash("error", "Resposta Incorreta: " + solucaoEstudante + "\nTente Novamente.");
		return forbidden(views.html.experimento.question.render(question, form, new Integer(valor -1)));
	}
	
	@Transactional
	public static Result next(Long id){
		Question question = null;
		TypedQuery<Question> query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.id = :id", Question.class);
		try{
			question = query.setParameter("id", id).getSingleResult();
		} catch (NoResultException e){
			// Acabaram os exercícios
			return redirect(routes.ExperimentoController.finish());
		}
		return ok(views.html.experimento.question.render(question, form, new Integer(10)));
	}
	
	public static Result finish(){
		return ok(views.html.experimento.finish.render());
	}
	
	@Transactional
	public static Result dica(Long id, Integer valor){
		Question question = null;
		TypedQuery<Question> query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.id = :id", Question.class);
		String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		
		try{
			question = query.setParameter("id", id).getSingleResult();
			SelecaoDicas selecao = new SelecaoDicas();
			String dica = selecao.getDica(question, student);
			
			flash("info", "Dica: "+dica);
			return forbidden(views.html.experimento.question.render(question, form, valor));
			
		} catch (NoResultException e){
			return TODO;
		}
	}
	
	@Transactional
	public static Result desistir(Long id, Integer valor){
		Question question = null;
		TypedQuery<Question> query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.id = :id", Question.class);
		String email = session().get("email");
		Student student = (Student) userService.findByEmail(email);
		StudentModel modelo = new StudentModel();
		student.setModel(modelo.newModel(student, new Integer(3)));
		studentService.update(student);
		
		try{
			question = query.setParameter("id", id).getSingleResult();
			ServiceLog log = new ServiceLog();
			log.save(question, student, null, false, null, Calendar.getInstance(), ""+modelo.getValue(student),true);
			
		} catch (NoResultException e){
			return TODO;
		}
		
		Long nextId = (long)(id.intValue() + 1);
		flash("info", "Você desistiu da questão anterior. Tente resolver essa nova questão!");
		return redirect(routes.ExperimentoController.next(nextId));
	}

}

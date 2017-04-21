/**
 * 
 */
package controllers.course;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.course.Course;
import models.course.Module;
import models.curriculum.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Security;
import services.course.CourseService;
import services.course.impl.CourseServiceImpl;
import services.module.ModuleService;
import services.module.impl.ModuleServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class ModuleController extends Controller {
	
	private static Form<Module> moduleForm = Form.form(Module.class);
	
	public static Result index(){
		return TODO;
	}
	
	public static Result create(Long id){
		
		CourseService service = new CourseServiceImpl();
		Course course = service.findById(id);
		Module module = new Module();
		module.setCourse(course);
		
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		
		Form<Module> newModuleForm = moduleForm.bindFromRequest();
		newModuleForm = form(Module.class).fill(module);
		
		return ok(views.html.module.create.render(newModuleForm, topics));
	}
	
	public static Result save(Long id){
		
		Form<Module> moduleForm = form(Module.class).bindFromRequest();
		Request request = request();
		TopicService topicService = new TopicServiceImpl();
		
		CourseService service = new CourseServiceImpl();
		Course course = service.findById(id);
		Module module = new Module();
		module.setCourse(course);
		
	
		if (moduleForm.field("description").valueOr("").isEmpty()) {
			moduleForm.reject("description", "Este campo é obrigatório");
			moduleForm = form(Module.class).fill(module);
			return badRequest(views.html.module.create.render(moduleForm, topicService.findAll()));
		}
		
		if(request.body().asFormUrlEncoded().get("listTopics")==null){
			moduleForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			moduleForm = form(Module.class).fill(module);
			return badRequest(views.html.module.create.render(moduleForm, topicService.findAll()));
		}
		
		List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
		
		ModuleService moduleService = new ModuleServiceImpl();
		Module newModule = moduleForm.get();
		newModule.setCourse(course);
		newModule.setTopics(selectedTopics);
		moduleService.save(newModule);
		
		flash("success", "Módulo cadastrado com sucesso!");
		return redirect(controllers.course.routes.CourseController.details(course.id));
		
	}

}

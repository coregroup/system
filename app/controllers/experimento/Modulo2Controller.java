package controllers.experimento;

import its.pedagogical.Planning;
import its.pedagogical.impl.PlanningImpl;

import java.util.List;

import models.curriculum.Question;
import models.curriculum.Solution;
import models.curriculum.Topic;
import models.users.Student;

import com.avaje.ebean.Ebean;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

public class Modulo2Controller extends Controller{
	
	private static DynamicForm form = Form.form();
	private static UserService userService = new UserServiceImpl();
	
	public static Result index(){
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		TopicService topicService = new TopicServiceImpl();
		Topic topic = topicService.findById(new Long(6));
		Question question = planning.nextQuestion(topic);
		
		///////////////////////
		String email = session().get("email");
    	Student student = (Student) userService.findByEmail(email);
		Long idLastQuestion = null;
		
		List<Solution> listSolution = Ebean.find(Solution.class).where().eq("user_id", student.getId().toString()).findList();
		
		for (int i = 0; i < listSolution.size(); i++) {
			if(topic.id.equals(listSolution.get(i).getQuestion().getTopics().get(0).id)){
				idLastQuestion = listSolution.get(i).getQuestion().id;
			}
		}
		if(idLastQuestion==null){
			return ok(views.html.resolution.index.render(question, form));
		}
			question = planning.nextQuestion(topic, idLastQuestion);
		///////////////////
		
			if(question == null){
				flash("success", "Você finalizou todas as questões do módulo!");
				return redirect(controllers.pedagogical.routes.PlanningController.index());
			}
		
		return ok(views.html.resolution.index.render(question, form));
	}

}

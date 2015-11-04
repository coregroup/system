package controllers.experimento;

import its.pedagogical.Planning;
import its.pedagogical.impl.PlanningImpl;

import java.util.List;

import models.curriculum.Question;
import models.curriculum.Solution;
import models.curriculum.Topic;
import models.users.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

public class Modulo1Controller extends Controller{
	
	private static DynamicForm form = Form.form();
	private static UserService userService = new UserServiceImpl();
	
	public static Result index(){
		Planning planning = new PlanningImpl(new QuestionRepositoryImpl());
		TopicService topicService = new TopicServiceImpl();
		Topic topic = topicService.findById(new Long(5));
		Question question = planning.nextQuestion(topic);
		
		///////////////////////
//		String email = session().get("email");
//    	User student = userService.findByEmail(email);
//		List<Solution> solutions = student.getSolutions();
//		Long idLastQuestion = null;
//		for (int i = 0; i < solutions.size(); i++) {
//			if(topic.id.equals(solutions.get(i).getQuestion().getTopics().get(0).id)){
//				idLastQuestion = solutions.get(i).getQuestion().id;
//			}
//		}
//		
//		if(idLastQuestion==null){
//			System.out.println("\n********************************************\n");
//			System.out.println("já tinha algumas resolvidas");
//			System.out.println("\n********************************************\n");
//			return ok(views.html.resolution.index.render(question, form));
//		}
//			question = planning.nextQuestion(topic, idLastQuestion);
//		
		///////////////////
		
		
		return ok(views.html.resolution.index.render(question, form));
	}

}

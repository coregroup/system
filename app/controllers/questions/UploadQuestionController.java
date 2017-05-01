/**
 * 
 */
package controllers.questions;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.curriculum.Question;
import models.curriculum.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Security;
import services.questions.UploadQuestionService;
import services.questions.impl.UploadQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author Priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class UploadQuestionController extends Controller {
	
	private static Form<Question> uploadQuestionForm = Form.form(Question.class);
	
	public static Result create(){
		Form<Question> questionForm = uploadQuestionForm.bindFromRequest();
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		return ok(views.html.question.upload.create.render(questionForm, topics));
	}
	
	public static Result save(){
		
		Form<Question> questionForm = form(Question.class).bindFromRequest();
		Request request = request();
		TopicService topicService = new TopicServiceImpl();
		
		if(questionForm.hasErrors()){
			questionForm.reject("* ALGUM CAMPO OBRIGATÓRIO NÃO FOI PREENCHIDO");
			return badRequest(views.html.question.upload.create.render(questionForm, topicService.findAll()));
		}
		
		if(request.body().asMultipartFormData().asFormUrlEncoded().get("listTopics")==null){
			questionForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			return badRequest(views.html.question.upload.create.render(questionForm, topicService.findAll()));
		}
		
		List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asMultipartFormData().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
		
		UploadQuestionService questionService = new UploadQuestionServiceImpl();
		Question question = questionForm.get();
		question.setAvailable(true);
		question.setTopics(selectedTopics);
		questionService.save(question);
		
		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.list(0, "name", "asc", ""));
	}

}

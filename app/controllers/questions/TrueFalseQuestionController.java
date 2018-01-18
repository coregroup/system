/**
 * 
 */
package controllers.questions;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.CorrectionType;
import models.Level;
import models.curriculum.Question;
import models.curriculum.Topic;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Security;
import services.questions.TrueFalseQuestionService;
import services.questions.impl.TrueFalseQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author Priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class TrueFalseQuestionController extends Controller{
	
	private static DynamicForm form = Form.form();
	
	public static Result create(){
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		return ok(views.html.question.truefalse.create.render(form, topics));
	}
	
	public static Result save(){
		Form<Dynamic> requestForm = form.bindFromRequest();
		Request request = request();
    	String name = requestForm.data().get("name");
    	String level = requestForm.data().get("level");
    	
    	String answer = requestForm.data().get("answer");
    	
    	//statement
    	String[] vetorContent = request.body().asFormUrlEncoded().get("statement");
    	String statement = vetorContent[0];
    	
    	TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
    	
    	if(name.equals("") || level.equals("") || statement.equals("") || answer == null){
    		DynamicForm formDeErro = form.fill(requestForm.data());
			formDeErro.reject("Todos os campos devem ser preenchidos.");
			return forbidden(views.html.question.truefalse.create.render(formDeErro, topics));
    	}
    	if(request.body().asFormUrlEncoded().get("listTopics")==null){
    		requestForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			return badRequest(views.html.question.truefalse.create.render((DynamicForm) requestForm, topics));
		}
    	
    	List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
		
		TrueFalseQuestionService questionService = new TrueFalseQuestionServiceImpl();
		Question question = new Question();
		question.setName(name);
		question.setAnswer(answer);
		question.setAvailable(true);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		question.setLevel(Level.getLevel(level));
		question.setStatement(statement);
		question.setTopics(selectedTopics);
		
		questionService.save(question);

		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.list(0, "name", "asc", ""));
	}

}

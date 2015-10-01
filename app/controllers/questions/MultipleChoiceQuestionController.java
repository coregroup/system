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
import play.data.Form;
import play.data.DynamicForm.Dynamic;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Request;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.MultipleChoiceQuestionService;
import services.questions.impl.MultipleChoiceQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author Priscylla
 *
 */
@Security.Authenticated(UserAuthenticatedSecured.class)
public class MultipleChoiceQuestionController extends Controller{
	
	private static DynamicForm form = Form.form();
	
	public static Result create(){
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		
		return ok(views.html.question.multiplechoice.create.render(form, topics));
	}
	
	public static Result save(){
		
		Form<Dynamic> requestForm = form.bindFromRequest();
		Request request = request();
    	String name = requestForm.data().get("name");
    	String level = requestForm.data().get("level");
    	String statement = requestForm.data().get("statement");
    	String answer = requestForm.data().get("answer");
    	String a = requestForm.data().get("optionA");
    	String b = requestForm.data().get("optionB");
    	String c = requestForm.data().get("optionC");
    	String d = requestForm.data().get("optionD");
    	String e = requestForm.data().get("optionE");
    	
    	
    	TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
    	
    	if(name.equals("") || level.equals("") || statement.equals("") || answer == null ||
    			a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("")){
    		DynamicForm formDeErro = form.fill(requestForm.data());
			formDeErro.reject("Todos os campos devem ser preenchidos.");
			return forbidden(views.html.question.multiplechoice.create.render(formDeErro, topics));
    	}
    	if(request.body().asFormUrlEncoded().get("listTopics")==null){
    		requestForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			return badRequest(views.html.question.multiplechoice.create.render((DynamicForm) requestForm, topics));
		}
    	
    	List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
    	
		MultipleChoiceQuestionService questionService = new MultipleChoiceQuestionServiceImpl(new QuestionRepositoryImpl());
		
		Question question = new Question();
		question.setName(name);
		question.setAnswer(answer);
		question.setAvailable(true);
		question.setCorrectionType(CorrectionType.AUTOMATIC);
		question.setLevel(Level.getLevel(level));
		question.setStatement(statement);
		question.setTopics(selectedTopics);
		List<String> options = new ArrayList<String>();
		options.add(a);
		options.add(b);
		options.add(c);
		options.add(d);
		options.add(e);
		
		questionService.save(question, options);

		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.index());
	}

}

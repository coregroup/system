/**
 * 
 */
package controllers.questions;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import models.CorrectionType;
import models.curriculum.Question;
import models.curriculum.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Request;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.ParagraphQuestionService;
import services.questions.TextQuestionService;
import services.questions.impl.ParagraphQuestionServiceImpl;
import services.questions.impl.TextQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author Priscylla
 *
 */
public class ParagraphQuestionController extends Controller{
	
	private static Form<Question> paragraphQuestionForm = Form.form(Question.class);
	
	public static Result create(){
		Form<Question> questionForm = paragraphQuestionForm.bindFromRequest();
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		return ok(views.html.question.paragraph.create.render(questionForm, topics));
	}
	
	public static Result save(){
		
		Form<Question> questionForm = form(Question.class).bindFromRequest();
		Request request = request();
		TopicService topicService = new TopicServiceImpl();
		
		if(questionForm.hasErrors()){
			questionForm.reject("* ALGUM CAMPO OBRIGATÓRIO NÃO FOI PREENCHIDO");
			return badRequest(views.html.question.text.newQuestion.render(questionForm, topicService.findAll()));
		}
		if(request.body().asFormUrlEncoded().get("listTopics")==null){
			questionForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			return badRequest(views.html.question.text.newQuestion.render(questionForm, topicService.findAll()));
		}
		if(questionForm.field("correctionType").value().equals((CorrectionType.AUTOMATIC.name()))){
			questionForm.reject("* NESSE MOMENTO O SISTEMA NÃO SUPORTA A CORREÇÃO AUTOMÁTICA PARA ESSE"
					+ "TIPO DE QUESTÃO.");
			return badRequest(views.html.question.text.newQuestion.render(questionForm, topicService.findAll()));
		}
		
		List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
		
		ParagraphQuestionService questionService = new ParagraphQuestionServiceImpl(new QuestionRepositoryImpl());
		Question question = questionForm.get();
		question.setAvailable(true);
		question.setTopics(selectedTopics);
		questionService.save(question);
		
		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.index());
	}

}

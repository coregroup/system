/**
 * 
 */
package controllers.questions;

import static play.data.Form.form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import models.curriculum.Question;
import models.curriculum.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.Request;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import repositories.questions.impl.QuestionRepositoryImpl;
import services.questions.UploadQuestionService;
import services.questions.impl.UploadQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author Priscylla
 *
 */
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
		
		if(request.body().asMultipartFormData().getFile("resposta") == null){
			questionForm.reject("* O ARQUIVO DE RESPOSTA NÃO FOI ANEXADO");
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
		
		UploadQuestionService questionService = new UploadQuestionServiceImpl(new QuestionRepositoryImpl());
		Question question = questionForm.get();
		question.setAvailable(true);
		question.setTopics(selectedTopics);
		
		File destino = null;
		try {
			destino = gravaDestaque(question.getName());
			question.setAnswer(destino.getName());
			questionService.save(question);
		} catch (IOException e) {
			e.printStackTrace();
			questionForm.reject("OCORREU UM ERRO INESPERADO NO SISTEMA");
			return badRequest(views.html.question.upload.create.render(questionForm, topicService.findAll()));
		}
		
		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.index());
	}
	
	
	private static File gravaDestaque(String name) throws IOException {
		RequestBody requestBody = request().body();
		MultipartFormData body = requestBody.asMultipartFormData();
		FilePart filePart = body.getFile("resposta");
		File resposta = filePart.getFile();
		File destino = arquivoDeDestino(name);
		FileUtils.moveFile(resposta, destino);
		return destino;
	}
		
	private static File arquivoDeDestino(String name) {
		name = name.replaceAll(" ", "_");
		return new File("public/output-codes", System.currentTimeMillis()
		+ "_" + name);
	}

}

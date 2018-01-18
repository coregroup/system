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

import models.CorrectionType;
import models.curriculum.Question;
import models.curriculum.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import services.questions.CodeQuestionService;
import services.questions.impl.CodeQuestionServiceImpl;
import services.topics.TopicService;
import services.topics.impl.TopicServiceImpl;

/**
 * @author priscylla
 *
 */
public class CodeQuestionController extends Controller{
	
	private static Form<Question> codeQuestionForm = Form.form(Question.class);
	
	
	public static Result create(){
		Form<Question> qForm = codeQuestionForm.bindFromRequest();
		TopicService topicService = new TopicServiceImpl();
		List<Topic> topics = topicService.findAll();
		
		return ok(views.html.question.code.create.render(qForm, topics));
	}
	
	public static Result save(){
		
		Form<Question> questionForm = form(Question.class).bindFromRequest();
		Request request = request();
		TopicService topicService = new TopicServiceImpl();
		
		//statement
		String[] vetorContent = request.body().asFormUrlEncoded().get("statement");
		String statement = vetorContent[0];
		
		if(questionForm.hasErrors()){
			questionForm.reject("* ALGUM CAMPO OBRIGATÓRIO NÃO FOI PREENCHIDO");
			return badRequest(views.html.question.code.create.render(questionForm, topicService.findAll()));
		}
		
		if(request.body().asMultipartFormData().asFormUrlEncoded().get("listTopics")==null){
			questionForm.reject("* OS TÓPICOS DA QUESTÃO NÃO FORAM ESCOLHIDOS");
			return badRequest(views.html.question.code.create.render(questionForm, topicService.findAll()));
		}
		
		if(questionForm.field("correctionType").value().equals((CorrectionType.AUTOMATIC.name()))){
			if(request.body().asMultipartFormData().getFile("input") == null){
				questionForm.reject("* A CORREÇÃO AUTOMÁTICA EXIGE O PREENCHIMENTO DO CAMPO: Arquivo de Input ");
				return badRequest(views.html.question.code.create.render(questionForm, topicService.findAll()));
			}
			if(request.body().asMultipartFormData().getFile("output") == null){
				questionForm.reject("* A CORREÇÃO AUTOMÁTICA EXIGE O PREENCHIMENTO DO CAMPO: Arquivo de Output ");
				return badRequest(views.html.question.code.create.render(questionForm, topicService.findAll()));
			}
		}
		
		List<Topic> selectedTopics = new ArrayList<Topic>();
		String[] topicos = request.body().asMultipartFormData().asFormUrlEncoded().get("listTopics");
		
		for (String topico : topicos) {
			selectedTopics.add(topicService.findById(Long.valueOf(topico)));
		}
		
		CodeQuestionService questionService = new CodeQuestionServiceImpl();
		Question question = questionForm.get();
		question.setStatement(statement);
		question.setAvailable(true);
		question.setTopics(selectedTopics);
		String exinput = questionForm.data().get("exinput");
		String exoutput = questionForm.data().get("exoutput");
		
		
		if(question.getCorrectionType().equals((CorrectionType.MANUAL))){
			questionService.save(question, exinput, exoutput);
		}
		
		if(question.getCorrectionType().equals((CorrectionType.AUTOMATIC))){
			File inputFile = null;
			File outputFile = null;
			
			try {
				inputFile = gravaArquivo(question.getName(), "input");
				outputFile = gravaArquivo(question.getName(), "output");
				questionService.save(question, exinput, exoutput);
			} catch (IOException e) {
				e.printStackTrace();
				questionForm.reject("OCORREU UM ERRO INESPERADO NO SISTEMA");
				return badRequest(views.html.question.code.create.render(questionForm, topicService.findAll()));
			}
			
		}
		flash("success", "Questão cadastrada com sucesso!");
		return redirect(controllers.questions.routes.QuestionController.list(0, "name", "asc", ""));
	}
	
	private static File gravaArquivo(String name, String path) throws IOException {
		RequestBody requestBody = request().body();
		MultipartFormData body = requestBody.asMultipartFormData();
		FilePart filePart = body.getFile(path);
		File arquivo = filePart.getFile();
		File destino = arquivoDeDestino(name, path);
		FileUtils.moveFile(arquivo, destino);
		return destino;
	}
		
	private static File arquivoDeDestino(String name, String path) {
		name = name.replaceAll(" ", "_");
		return new File("public/codes/questions/"+path, name);
}

}

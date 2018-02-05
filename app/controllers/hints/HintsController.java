/**
 * 
 */
package controllers.hints;

import static play.data.Form.form;

import models.HintsType;
import models.curriculum.Hint;
import models.curriculum.Question;
import models.users.Teacher;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import services.hints.HintService;
import services.hints.impl.HintServiceImpl;
import services.questions.QuestionService;
import services.questions.impl.QuestionServiceImpl;
import services.users.UserService;
import services.users.impl.UserServiceImpl;

/**
 * @author priscylla
 *
 */
public class HintsController extends Controller{
	
	private static DynamicForm form = Form.form();
	private static UserService userService = new UserServiceImpl();
	
	public static Result create(Long id){
		QuestionService questionService = new QuestionServiceImpl();
		Question question = questionService.findById(id);
		Hint hint = new Hint();
		hint.setQuestion(question);
		
		Form<Hint> hintForm = form(Hint.class).fill(hint);
		
		return ok(views.html.hint.create.render(hintForm, question));
	}
	
	public static Result save(Long id){
		
		Form<Dynamic> requestForm = form.bindFromRequest();
		String partOfCode = requestForm.data().get("partOfCode");
		
		Request request = request();
		String[] vetor = request.body().asFormUrlEncoded().get("code");
		String solutionModel = vetor[0];
		String[] vetorContent = request.body().asFormUrlEncoded().get("content");
		String content = vetorContent[0];
		
		String email = session().get("email");
    	Teacher author = (Teacher) userService.findByEmail(email);
    	
    	if(solutionModel==null || solutionModel.equals("")){
    		if(!partOfCode.equals("")){
    			flash("danger", "Se você preencher o campo TRECHO DE CÓDIGO, também deve preencher o campo SOLUÇÃO MODELO.");
    			return redirect(controllers.hints.routes.HintsController.create(id));
    		}
    	}
    	if(requestForm.data().get("type").equals("")){
			flash("danger", "Você esqueceu de preencher o campo TIPO DE DICA!");
			return redirect(controllers.hints.routes.HintsController.create(id));
		}
    	if(content.equals("<p>&nbsp;</p>")){
			flash("danger", "Você esqueceu de preencher o CONTEÚDO da dica!");
			return redirect(controllers.hints.routes.HintsController.create(id));
		}
    	
    	if(requestForm.data().get("type").equals("IMAGE")){
    		content = content.substring(3, (content.length()-4));// retirado do <p> do inicio e do </p> do final
    		//content = "<img src=\""+ content + "\" />"; TODO ANTIGO HTML USADO
    		content = content.replace("&lt;", "<");
    		content = content.replace("&gt;", ">");
    	}
    	
    	if(requestForm.data().get("type").equals("VIDEO")){
    		content = content.substring(3, (content.length()-4));// retirado do <p> do inicio e do </p> do final
    		content = content.substring(32);
    		content = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" +
    					content + "?rel=0&amp;showinfo=0\" frameborder=\"0\" allowfullscreen></iframe>";
    	}
    	
		QuestionService questionService = new QuestionServiceImpl();
		Question question = questionService.findById(id);  
		HintService hintService = new HintServiceImpl();
		
		Hint hint = new Hint();
		
		hint.setAuthor(author);
		hint.setQuestion(question);
		hint.setSolutionModel(solutionModel);
		hint.setPartOfCode(partOfCode);
		hint.setType(HintsType.getHintsType(requestForm.data().get("type")));
		hint.setContent(content);
		
		
		hintService.save(hint);
		
		//System.out.println("\n\n Request: " + request.body().toString());
		return ok(views.html.question.details.render(question, hintService.findByQuestion(id)));
	}

}

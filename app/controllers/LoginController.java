/**
 * 
 */
package controllers;

import models.users.User;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.users.LoginService;
import services.users.impl.LoginServiceImpl;
import views.html.login;

/**
 * @author priscylla
 *
 */
public class LoginController extends Controller{
	
	private static DynamicForm form = Form.form();
	private static LoginService loginService = new LoginServiceImpl();
    
    public static Result login() {
    	return ok(login.render(form));
    }
    
    public static Result signin(){
    	Form<Dynamic> requestForm = form.bindFromRequest();
    	String email = requestForm.data().get("email");
    	String password = requestForm.data().get("password");
    	
    	User user = loginService.exists(email, password);
    	
    	if(user != null)
    		return redirect(controllers.routes.Application.studentDashboard());//TODO
    	else {
    		DynamicForm formDeErro = form.fill(requestForm.data());
    		formDeErro.reject("O email ou senha estão incorretos");
    		return forbidden(views.html.login.render(formDeErro));
    	}
    }

}

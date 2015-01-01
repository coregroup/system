/**
 * 
 */
package controllers;

import java.util.Calendar;

import models.users.User;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.users.LoginService;
import services.users.UserService;
import services.users.impl.LoginServiceImpl;
import services.users.impl.UserServiceImpl;
import views.html.login;
import converters.ConvertPasswordToSHA;

/**
 * @author priscylla
 *
 */
public class LoginController extends Controller{
	
	private static DynamicForm form = Form.form();
	private static LoginService loginService = new LoginServiceImpl();
	private static UserService userServive = new UserServiceImpl(); 
    
    public static Result login() {
    	return ok(login.render(form));
    }    
    
    public static Result signin(){
    	Form<Dynamic> requestForm = form.bindFromRequest();
    	String email = requestForm.data().get("email");
    	String password = requestForm.data().get("password");
    	
    	User user = loginService.exists(email, ConvertPasswordToSHA.convert(password));
    	
    	if(user != null){
    		session().put("email", user.getEmail());
    		userServive.logged(user);
    		return redirect(controllers.routes.DashboardController.studentDashboard());
    	}
    	else {
    		DynamicForm formDeErro = form.fill(requestForm.data());
    		formDeErro.reject("O email ou senha estão incorretos");
    		return forbidden(views.html.login.render(formDeErro));
    	}
    }
    
    public static Result logout() {
        session().clear();
        flash("success", "Você saiu do sistema com sucesso!");
        return redirect(
            routes.Application.index()
        );
    }

}

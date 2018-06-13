package controllers.studentmodel;

import java.util.ArrayList;
import java.util.List;

import controllers.authentication.UserAuthenticatedSecured;
import models.Knowledge;
import models.its.StudentModel;
import models.its.StudentModelUnit;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.its.studentmodel.StudentModelService;
import services.its.studentmodel.impl.StudentModelServiceImpl;

@Security.Authenticated(UserAuthenticatedSecured.class)
public class SmileModelController extends Controller{

	public static Result index() {
		StudentModelService service = new StudentModelServiceImpl();
		StudentModel studentModel = service.findById(new Long(1));
		
		List<Integer> smile = new ArrayList<Integer>();
		smile.add(new Integer(0));
		smile.add(new Integer(1));
		smile.add(new Integer(2));
		smile.add(new Integer(3));
		smile.add(new Integer(4));

		/*
		 int qntOtimos = 0;
		 int qntBom = 0;
		 int qntNeutro = 0;
		 int qntRuim = 0;
		 int qntPessimo = 0;
		*/
		 		 
		for (StudentModelUnit unit : studentModel.getStudentModelUnits()) {
			if(unit.getKnowledge().equals(Knowledge.OTIMO)) {
				int valorOtimo = smile.get(0).intValue() + 1;
				smile.set(0, new Integer(valorOtimo));
				 
			}
			if(unit.getKnowledge().equals(Knowledge.BOM)) {
				int valorBom = smile.get(1).intValue() + 1;
				smile.set(1, new Integer(valorBom));

			}
			if(unit.getKnowledge().equals(Knowledge.NEUTRO)) {
				int valorNeutro = smile.get(2).intValue() + 1;
				
				smile.set(2, new Integer(valorNeutro));

			}
			if(unit.getKnowledge().equals(Knowledge.RUIM)) {
				int valorRuim = smile.get(3).intValue() + 1;
				smile.set(3, new Integer(valorRuim));

			}
			if(unit.getKnowledge().equals(Knowledge.PESSIMO)) {
				int valorPessimo = smile.get(4).intValue() + 1;
				smile.set(4, new Integer(valorPessimo));

			}
			
		}
		
		
		return ok(views.html.studentmodel.smile.render(studentModel, smile));
	}
	
}

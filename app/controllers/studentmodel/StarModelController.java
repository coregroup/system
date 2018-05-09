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
public class StarModelController extends Controller{
	 
	public static Result index() {
		StudentModelService service = new StudentModelServiceImpl();
		StudentModel studentModel =  service.findById(new Long(1));
		
		
		List<Integer> qntEstrelas = new ArrayList<Integer>();
		qntEstrelas.add(new Integer(0));
		qntEstrelas.add(new Integer(1));
		qntEstrelas.add(new Integer(0));
		qntEstrelas.add(new Integer(0));
		qntEstrelas.add(new Integer(0));

		/*
		 int qntOtimos = 0;
		 int qntBom = 0;
		 int qntNeutro = 0;
		 int qntRuim = 0;
		 int qntPessimo = 0;
		*/
		 		 
		for (StudentModelUnit unit : studentModel.getStudentModelUnits()) {
			if(unit.getKnowledge().equals(Knowledge.OTIMO)) {
				int valorOtimo = qntEstrelas.get(0).intValue() + 1;
				qntEstrelas.set(0, new Integer(valorOtimo));
				 
			}
			if(unit.getKnowledge().equals(Knowledge.BOM)) {
				int valorBom = qntEstrelas.get(1).intValue() + 1;
				qntEstrelas.set(1, new Integer(valorBom));

			}
			if(unit.getKnowledge().equals(Knowledge.NEUTRO)) {
				int valorNeutro = qntEstrelas.get(2).intValue() + 1;
				
				qntEstrelas.set(2, new Integer(valorNeutro));

			}
			if(unit.getKnowledge().equals(Knowledge.RUIM)) {
				int valorRuim = qntEstrelas.get(3).intValue() + 1;
				qntEstrelas.set(3, new Integer(valorRuim));

			}
			if(unit.getKnowledge().equals(Knowledge.PESSIMO)) {
				int valorPessimo = qntEstrelas.get(4).intValue() + 1;
				qntEstrelas.set(4, new Integer(valorPessimo));

			}
			
		}
		
		return ok(views.html.studentmodel.star.render(studentModel, qntEstrelas));
	}
	

}

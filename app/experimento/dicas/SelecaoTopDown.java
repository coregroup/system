/**
 * 
 */
package experimento.dicas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import experimento.StudentModel;
import models.curriculum.question.Logexp;
import models.curriculum.question.Question;
import models.users.Student;
import play.db.jpa.JPA;

/**
 * @author priscylla
 *
 */
public class SelecaoTopDown implements TipoSelecao {

	@Override
	public String getDica(Question question, Student student) {
		ServiceLog log = new ServiceLog();
		StudentModel modelo = new StudentModel();
		
		TypedQuery<Logexp> query = JPA.em().createQuery("SELECT l FROM Logexp l WHERE l.idStudent = :idStudent", Logexp.class);
		List<Logexp> list = query.setParameter("idStudent", student.getId()).getResultList();
		
		if(list.isEmpty()){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		
		for (Logexp logexp : list) {
			
			if(logexp.getIdQuestion().intValue() != question.getId().intValue()){ // senão for a questão corrente
				list.remove(logexp);
			}
			if(logexp.getIdDica()==null && logexp.getIdDica()==""){
				list.remove(logexp);
			}
		}
		
		if(list.size()==1){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		if(list.size()==2){
			log.save(question, student, null, false, "2", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica2();
		}
		if(list.size()==3){
			log.save(question, student, null, false, "3", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica3();						
		}
		if(list.size()==4){
			log.save(question, student, null, false, "4", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica4();						
		}
		if(list.size()==5){
			log.save(question, student, null, false, "5", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();
		}
		if(list.size()>=6){
			log.save(question, student, null, false, "6", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();
		}
		
		log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
		return question.getDica1();
	}

}

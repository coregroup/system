/**
 * 
 */
package experimento.dicas;

import java.util.ArrayList;
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
		List<Logexp> novaLista = new ArrayList<Logexp>();
		
		TypedQuery<Logexp> query = JPA.em().createQuery("SELECT l FROM Logexp l WHERE l.idStudent = :idStudent", Logexp.class);
		List<Logexp> list = query.setParameter("idStudent", student.getId()).getResultList();
		
		if(list.isEmpty()){
			System.out.println("Lista vazia");
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		
		System.out.println("Tamanho da lista: "+ list.size());
		
		for (Logexp logexp : list) {
			
			if(logexp.getIdQuestion().intValue()==question.getId().intValue()){
				novaLista.add(logexp);
			}
		}
		
		if(novaLista.size()==1){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		if(novaLista.size()==2){
			log.save(question, student, null, false, "2", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica2();
		}
		if(novaLista.size()==3){
			log.save(question, student, null, false, "3", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica3();						
		}
		if(novaLista.size()==4){
			log.save(question, student, null, false, "4", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica4();						
		}
		if(novaLista.size()==5){
			log.save(question, student, null, false, "5", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();
		}
		if(novaLista.size()>=6){
			log.save(question, student, null, false, "6", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();
		}
		
		log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
		return question.getDica1();
	}

}

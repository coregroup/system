/**
 * 
 */
package experimento.dicas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import play.db.jpa.JPA;
import experimento.StudentModel;
import models.curriculum.question.Logexp;
import models.curriculum.question.Question;
import models.users.Student;

/**
 * @author priscylla
 *
 */
public class SelecaoSM implements TipoSelecao {

	@Override
	public String getDica(Question question, Student student) {
		StudentModel modelo = new StudentModel();
		SelecaoTopDown topdown = new SelecaoTopDown();
		
		int valueModelo = (int) modelo.getValue(student)/2;
		
		if(valueModelo>=4){
			return topdown.getDica(question, student);
		} else {
			if(valueModelo<=2){
				return getDicaBaixo(question, student);
			} else {
				return getDicaMedio(question, student);
			}
		}
	}
	
	public String getDicaBaixo(Question question, Student student) {
		ServiceLog log = new ServiceLog();
		StudentModel modelo = new StudentModel();
		
		List<Logexp> novaLista = new ArrayList<Logexp>();
		
		TypedQuery<Logexp> query = JPA.em().createQuery("SELECT l FROM Logexp l WHERE l.idStudent = :idStudent", Logexp.class);
		List<Logexp> list = query.setParameter("idStudent", student.getId()).getResultList();
		
		if(list.isEmpty()){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		
		for (Logexp logexp : list) {
			
			if(logexp.getIdQuestion().intValue()==question.getId().intValue()){
				novaLista.add(logexp);
			}
		}
		
		if(novaLista.size()==1){
			log.save(question, student, null, false, "3", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica3();
		}
		if(novaLista.size()==2){
			log.save(question, student, null, false, "4", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica4();
		}
		if(novaLista.size()==3){
			log.save(question, student, null, false, "5", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();						
		}
		if(novaLista.size()==4){
			log.save(question, student, null, false, "2", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica2();						
		}
		if(novaLista.size()==5){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		if(novaLista.size()>=6){
			log.save(question, student, null, false, "6", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica5();
		}
		
		log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
		return question.getDica1();
	}
	
	
	public String getDicaMedio(Question question, Student student) {
		ServiceLog log = new ServiceLog();
		StudentModel modelo = new StudentModel();
		
		List<Logexp> novaLista = new ArrayList<Logexp>();
		
		TypedQuery<Logexp> query = JPA.em().createQuery("SELECT l FROM Logexp l WHERE l.idStudent = :idStudent", Logexp.class);
		List<Logexp> list = query.setParameter("idStudent", student.getId()).getResultList();
		
		if(list.isEmpty()){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();
		}
		
		for (Logexp logexp : list) {
			
			if(logexp.getIdQuestion().intValue()==question.getId().intValue()){
				novaLista.add(logexp);
			}
		}
		
		if(novaLista.size()==1){
			log.save(question, student, null, false, "2", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica2();
		}
		if(novaLista.size()==2){
			log.save(question, student, null, false, "3", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica3();
		}
		if(novaLista.size()==3){
			log.save(question, student, null, false, "4", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica4();						
		}
		if(novaLista.size()==4){
			log.save(question, student, null, false, "1", Calendar.getInstance(), ""+modelo.getValue(student), false);
			return question.getDica1();						
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

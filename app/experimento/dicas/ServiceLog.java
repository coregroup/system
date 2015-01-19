/**
 * 
 */
package experimento.dicas;

import java.util.Calendar;

import play.db.jpa.JPA;
import models.curriculum.question.Logexp;
import models.curriculum.question.Question;
import models.users.Student;

/**
 * @author priscylla
 *
 */
public class ServiceLog {
	
	public void save(Question q, Student student, String solution, boolean correto, String idDica, Calendar horario, String model,
			boolean desistiu){
		Logexp log = new Logexp();
		log.setIdStudent(student.getId());
		log.setIdTurma(student.getTurma());
		log.setIdQuestion(q.getId());
		log.setTopic(q.getTopic());
		log.setSolution(solution);
		log.setIdDica(idDica);
		log.setDesistiu(desistiu);
		log.setHorario(horario);
		log.setCorreto(correto);
		log.setModel(model);
		
		JPA.em().persist(log);
	}

}

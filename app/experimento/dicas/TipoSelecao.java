/**
 * 
 */
package experimento.dicas;

import models.curriculum.question.Question;
import models.users.Student;

/**
 * @author priscylla
 *
 */
public interface TipoSelecao {
	
	public String getDica(Question question, Student student);

}

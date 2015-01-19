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
public class SelecaoDicas {
	
	public String getDica(Question q, Student student){
		TipoSelecao selecao = getTipo(q, student);
		String dica = selecao.getDica(q, student);
		return dica;
	}
	
	
	private TipoSelecao getTipo(Question q, Student student){
		TipoSelecao tipo = null;
		if(student.getTurma().equals("A")){
			if(q.getTopic().equals("analise")){
				tipo = new SelecaoTopDown();
			}
			if(q.getTopic().equals("sintese")){
				tipo = new SelecaoRandom();
			}
		}
		if(student.getTurma().equals("B")){
			if(q.getTopic().equals("analise")){
				tipo = new SelecaoRandom();				
			}
			if(q.getTopic().equals("sintese")){
				tipo = new SelecaoSM();
			}
		}
		if(student.getTurma().equals("C")){
			if(q.getTopic().equals("analise")){
				tipo = new SelecaoSM();				
			}
			if(q.getTopic().equals("sintese")){
				tipo = new SelecaoTopDown();
			}
		}
		
		return tipo;
	}

}

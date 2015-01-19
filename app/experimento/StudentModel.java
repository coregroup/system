/**
 * 
 */
package experimento;

import models.users.Student;

/**
 * @author priscylla
 *
 */
public class StudentModel {
	
	public double getValue(Student student){
		
		String texto = student.getModel();  
		String notas[] = texto.split(",");
		int n1 = Integer.valueOf(notas[0]);
		int n2 = Integer.valueOf(notas[1]);
		int n3 = Integer.valueOf(notas[2]);
		int n4 = Integer.valueOf(notas[3]);
		int n5 = Integer.valueOf(notas[4]);
		
		return (n1 + n2 + n3 + n4 + n5)/5;
	}
	
	public String newModel(Student student, Integer valor){
		String texto = student.getModel();  
		String notas[] = texto.split(",");
		
		int n2 = Integer.valueOf(notas[1]);
		int n3 = Integer.valueOf(notas[2]);
		int n4 = Integer.valueOf(notas[3]);
		int n5 = Integer.valueOf(notas[4]);
		
		return n2 + "," + n3 + "," + n4 + "," + n5 + "," + valor.intValue();
		
	}

}

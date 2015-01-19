/**
 * 
 */
package experimento;

import java.util.Random;

/**
 * @author priscylla
 *
 */
public class Aleatorio {
	
	public int getNumRandon(){
		 Random gerador = new Random();
		 int numero = gerador.nextInt(5) + 1;//gera de 1 à 5
		 return numero;
	}

}

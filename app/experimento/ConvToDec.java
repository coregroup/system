/**
 * 
 */
package experimento;

/**
 * @author priscylla
 *
 */
public class ConvToDec {
	
	public int convertTo10(String num, int base){
		int resul = 0;
		if(base == 2){
			resul = Integer.parseInt(num, 2);
		}
		if(base == 8){
			resul = Integer.parseInt(num, 8);
		}
		if(base == 16){
			resul = Integer.parseInt(num, 16);
		}
		return resul;
	}

}

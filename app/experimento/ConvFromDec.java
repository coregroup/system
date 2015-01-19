/**
 * 
 */
package experimento;

/**
 * @author priscylla
 *
 */
public class ConvFromDec {
	
	public String convertFrom10(int num, int base){
		String resul = "";
		
		while(num >= base){
			resul = addNumberInResult(num % base, resul);
			num = num / base;
		}
		resul = addNumberInResult(num, resul);
		return resul;		
	}
	
	private String addNumberInResult(int number, String resul){
    	switch (number) {
		case 10:
			resul = "A" + resul;
			break;
		case 11:
			resul = "B" + resul;
			break;
		case 12:
			resul = "C" + resul;
			break;
		case 13:
			resul = "D" + resul;
			break;
		case 14:
			resul = "E" + resul;
			break;
		case 15:
			resul = "F" + resul;
			break;
		default:
			resul = number + resul;
			break;
		}
    	
    	return resul;
    }

}

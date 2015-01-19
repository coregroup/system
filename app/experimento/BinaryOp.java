/**
 * 
 */
package experimento;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author priscylla
 *
 */
public class BinaryOp {
	
	public String soma(String number1, String number2) {

        //Lista encadeada que servirá como 'registrador' armazenador das somas
        LinkedList<Integer> register = new LinkedList<Integer>();
        //Pìlha para os 'vai um'
        Stack<Integer> goOne = new Stack<Integer>();

        //Ou então insira eles manualmente
        //number1 = "1100001";
        //number2 = "101";

        //Deixa ambas as string com o mesmo tamanho, colocando zeros explícitos na menor

        if (number1.length() != number2.length()) {
            if (number1.length() > number2.length()) {
                number2 = fillWithZero(number2, number1.length() - number2.length());
            } else {
                number1 = fillWithZero(number1, number2.length() - number1.length());
            }
        }

        int loopCount = number1.length();
        int sum = 0;

        while (loopCount-- > 0) {

            sum = Integer.parseInt(number1.substring(loopCount, loopCount + 1)) + Integer.parseInt(number2.substring(loopCount, loopCount + 1));
            if (!goOne.empty()) {
                sum += goOne.pop();
            }
            register.add(sum % 2);
            goOne.add(sum / 2); //Divisão de inteiros, retorna só a parte inteira

        }
        if (!goOne.empty()) {
            register.add(goOne.pop());
        }

        /*
         * Pega o Iterator em ordem reversa, caso contrário, o valor seria outra
         * coisa qualquer, menos o valor correto <img src="http://www.geekinside.com.br/wp-includes/images/smilies/icon_razz.gif" alt=":P" class="wp-smiley">
         */
        Iterator<Integer> i = register.descendingIterator();
        String output = "";
        while (i.hasNext()) {
            output = output.concat(Integer.toString(i.next()));
        }
        //Retira os zeros iniciais com expressão regular
        output = output.replaceFirst("^0*", "");
//        System.out.println("Operação: " + number1 + " + " + number2 );
//        System.out.println("Saída binária: " + output);
//        System.out.println("Saída decimal: " + Integer.parseInt(output, 2));
        return output;
    }

    private String fillWithZero(String original, int places) {
        StringBuilder zeros = new StringBuilder(places);
        while (places-- > 0) {
            zeros.append("0");
        }
        zeros.append(original);
        return zeros.toString();
    }

}

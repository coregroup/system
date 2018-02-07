/**
 * 
 */
package its.feedback.sim.c;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author priscylla
 *
 */
public class SearchKeys {
	
	// Contagem usando Expressao regular
		public int countExpression(String texto, String valor) {
			int count = 0;

			if (texto != null) {
				// Onde registramos a expressão regular
				Pattern pattern = Pattern.compile(valor);

				// Onde registramos o comparador
				Matcher matcher = pattern.matcher(texto);

				// Loop para verificar o que foi encontrado
				while (matcher.find()) {
					count++;
				}
			}
			
			return count;

		}
		
		// Contagem usando INDEX OF
		public int countIndexOf(String texto, String valor) {
			int pos = 0;
			int count = 0;

			if (texto != null) {
				// Procura a proxima ocorrencia da palavra
				pos = texto.indexOf(valor);

				// Enquanto encontrar outras ocorrencias, ele soma contador
				while (pos >= 0) {
					++count;

					// Procura a proxima ocorrencia a partir do ultimo ponto
					pos = texto.indexOf(valor, pos + 1);
				}

			}

			return count;
		}

}

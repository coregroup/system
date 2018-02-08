/**
 * 
 */
package its.feedback.sim.c;

import java.util.Map;

import its.feedback.sim.CodeSimilarity;

/**
 * @author priscylla
 *
 */
public class KeywordSimImpl implements CodeSimilarity {

	/* (non-Javadoc)
	 * @see its.feedback.sim.CodeSimilarity#similarity(java.lang.String, java.lang.String)
	 */
	@Override
	public double similarity(String modelo, String code) {
		int qntModelo = 0;
		int qntCode = 0;
		int totalModelo = 0;
		double comuns = 0;
		SearchKeys search = new SearchKeys();
		Map<String,String> keys = Keywords.options();
		
		for (Map.Entry<String, String> word : keys.entrySet()) {
			qntModelo = search.countExpression(modelo, word.getValue());
			qntCode = search.countExpression(code, word.getValue());
			//System.out.println("** " + word.getValue() + " | qntModelo = " + qntModelo + " | qntCode = " + qntCode);
			totalModelo += qntModelo;
			if(qntModelo <= qntCode)
				comuns += qntModelo;
			else
				comuns += qntCode;
		}
		return comuns;
	}

}

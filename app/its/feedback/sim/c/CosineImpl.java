package its.feedback.sim.c;

import info.debatty.java.stringsimilarity.Cosine;
import its.feedback.sim.CodeSimilarity;

public class CosineImpl implements CodeSimilarity {

	@Override
	public double similarity(String code1, String code2) {
		Cosine cos = new Cosine(3);
		if(code1.equals("") && code2.equals(""))
			return 0;
		return cos.similarity(code1, code2);
	}

}

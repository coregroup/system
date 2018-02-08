/**
 * 
 */
package its.feedback.sim.c;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.text.similarity.LevenshteinDistance;

import its.feedback.sim.CodeSimilarity;
import its.feedback.sim.c.ast.CLexer;
import its.feedback.sim.c.ast.CParser;

/**
 * @author priscylla
 *
 */
public class ASTSimImpl implements CodeSimilarity {
	
	private String tree = "";

	/* (non-Javadoc)
	 * @see its.feedback.sim.CodeSimilarity#similarity(java.lang.String, java.lang.String)
	 */
	@Override
	public double similarity(String code1, String code2) {
		LevenshteinDistance distanceCalc = new LevenshteinDistance();
		double ratio = 0;
		try {
			String tree1 = generate(code1);
			String tree2 = generate(code2);
			int count = distanceCalc.apply(tree1, tree2);
			ratio = 1.0 - ((double) count / Math.max(tree1.length(), tree2.length()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ratio;
	}
	
	private String generate(String inputString) throws IOException{
		tree = "";   
        ANTLRInputStream input = new ANTLRInputStream(inputString);
        
        CLexer lexer = new CLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        ParserRuleContext ctx = parser.compilationUnit();

        printAST(ctx, false, 0);
		return tree;
	}
	
	
	private void printAST(RuleContext ctx, boolean verbose, int indentation) {
		
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];
            for (int i = 0; i < indentation; i++) {
                tree += "  ";
            }
            tree += ruleName + " -> " + ctx.getText();
        }
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                printAST((RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));
            }
        }
    }

}

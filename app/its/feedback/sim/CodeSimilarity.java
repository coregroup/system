/**
 * 
 */
package its.feedback.sim;

/**
 * @author priscylla
 *
 */
public interface CodeSimilarity {
	
	/**
     * Compute and return a measure of similarity between 2 strings.
     * @param code1
     * @param code2
     * @return similarity (0 means both strings are completely different)
     */
	double similarity(String code1, String code2);

}

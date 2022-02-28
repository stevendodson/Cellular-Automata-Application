/**
 * Represents any rule that governs the evolution of a one-dimensional,
 * two-state Cellular Automaton at a single moment in time.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public abstract class Rule {
	/** Rule number of the Cellular Automaton. */
	private int ruleNum;
	/** Binary representation of the rule number. */
	protected String binaryRule;
	/** Size of the radius used for each evolution. */
	protected int radiusSize;

	/**
	 * Constructs a Rule object with the given rule number.
	 * 
	 * @param ruleNum
	 */
	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		binaryRule = String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
	}

	/**
	 * Returns the rule number.
	 * 
	 * @return the rule number.
	 */
	public int getRuleNum() {
		return ruleNum;
	}

	/**
	 * Returns an array containing the immediate neighbors of the given index. Uses
	 * circular logic for edge cases.
	 * 
	 * @param idx the index of the element to find the neighbors of
	 * @param gen the generation used to find the neighbors
	 * @return an array containing the immediate neighbors of the given index.
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);

	/**
	 * Returns the boolean result of a given neighborhood after being evolved using
	 * the rule number.
	 * 
	 * @param neighborhood the neighborhood to be evolved
	 * @return the boolean result of the given neighborhood after the rule is
	 *         applied.
	 */

	public abstract boolean evolve(boolean[] neighborhood);

	/**
	 * Returns a tabular representation of the rule with the corresponding results.
	 * 
	 * @param falseSymbol the character to be used to represent false
	 * @param trueSymbol  the character to be used to represent true
	 * @return a String representation of the rule
	 */
	public abstract String ruleTableString(char falseSymbol, char trueSymbol);

	/**
	 * Returns an array containing the neighbors of an element within the given
	 * radius.
	 * 
	 * @param idx    the index of the element to find the neighbors of
	 * @param radius the radius of the neighborhood
	 * @param gen    the generation used to find the neighborhood
	 * @return an array containing the neighbors of the element within the radius
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		boolean[] result = new boolean[radius * 2 + 1];
		int range = idx - radius;
		for (int i = 0; i < result.length; ++i, ++range) {
			result[i] = gen.getState(Math.floorMod(range, gen.size()));
		}
		return result;
	}

	/**
	 * Returns the next generation by using the rule on the given generation.
	 * 
	 * @param gen the Generation object to be evolved
	 * @return the evolved version of the given Generation object
	 */
	public Generation evolve(Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		for (int i = 0; i < gen.size(); ++i) {
			temp[i] = evolve(getNeighborhoodByRadius(i, radiusSize, gen));
		}
		Generation result = new Generation(temp);
		return result;
	}
}
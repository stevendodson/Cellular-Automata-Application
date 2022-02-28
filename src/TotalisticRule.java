import java.util.StringJoiner;

/**
 * Represents any one of the 64 rules that govern the evolution of
 * one-dimensional, two-state totalistic cellular automata with a neighborhood
 * radius of 2.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class TotalisticRule extends Rule {
	/** Default radius for totalistic cellular automata */
	private static final int DEFAULT_RADIUS = 2;

	/**
	 * Creates a Rule object within the bounds of a totalistic rule.
	 * 
	 * @param ruleNum the desired rule number
	 * @throws RuleNumException if ruleNum is less than 0 or greater than 63
	 */
	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if (ruleNum < 0 || ruleNum > 63) {
			throw new RuleNumException(0, 63);
		}
		radiusSize = 2;
	}

	public boolean evolve(boolean[] neighborhood) {
		boolean result;
		int numTrue = 0;
		for (boolean b : neighborhood) {
			if (b == true) {
				++numTrue;
			}
		}
		switch (numTrue) {
		case 5:
			result = (binaryRule.charAt(2) == '0') ? false : true;
			return result;
		case 4:
			result = (binaryRule.charAt(3) == '0') ? false : true;
			return result;
		case 3:
			result = (binaryRule.charAt(4) == '0') ? false : true;
			return result;
		case 2:
			result = (binaryRule.charAt(5) == '0') ? false : true;
			return result;
		case 1:
			result = (binaryRule.charAt(6) == '0') ? false : true;
			return result;
		case 0:
			result = (binaryRule.charAt(7) == '0') ? false : true;
			return result;
		}
		return false;
	}

	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx, DEFAULT_RADIUS, gen);
	}

	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String result;
		StringJoiner joiner = new StringJoiner(" ");
		for (int i = 2; i < binaryRule.length(); ++i) {
			joiner.add(Character.toString(binaryRule.charAt(i)));
		}
		result = joiner.toString().replace('0', falseSymbol);
		result = result.replace('1', trueSymbol);
		String table = "5 4 3 2 1 0" + System.lineSeparator() + result;
		return table;
	}
}

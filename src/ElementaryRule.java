import java.util.StringJoiner;

/**
 * Represents any one of the 256 rules that govern the evolution of elementary
 * cellular automata.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class ElementaryRule extends Rule {

	/**
	 * Creates a Rule object within the bounds of an elementary rule.
	 * 
	 * @param ruleNum the desired rule number
	 * @throws RuleNumException if ruleNum is less than 0 or greater than 255
	 */
	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if (ruleNum < 0 || ruleNum > 255) {
			throw new RuleNumException(0, 255);
		}
		radiusSize = 1;
	}

	public boolean evolve(boolean[] neighborhood) {
		if (neighborhood[0] == true) {
			if (neighborhood[1] == true) {
				if (neighborhood[2] == true) {
					if (binaryRule.charAt(0) == '0') {
						return false;
					}
					return true;
				}
				if (binaryRule.charAt(1) == '0') {
					return false;
				}
				return true;
			}
			if (neighborhood[2] == true) {
				if (binaryRule.charAt(2) == '0') {
					return false;
				}
				return true;
			}
			if (binaryRule.charAt(3) == '0') {
				return false;
			}
			return true;

		}

		if (neighborhood[1] == true) {
			if (neighborhood[2] == true) {
				if (binaryRule.charAt(4) == '0') {
					return false;
				}
				return true;
			}
			if (binaryRule.charAt(5) == '0') {
				return false;
			}
			return true;
		}
		if (neighborhood[2] == true) {
			if (binaryRule.charAt(6) == '0') {
				return false;
			}
			return true;
		}
		if (binaryRule.charAt(7) == '0') {
			return false;
		}
		return true;
	}

	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx, 1, gen);
	}

	public String ruleTableString(char falseSymbol, char trueSymbol) {
		StringJoiner joiner = new StringJoiner("   ", "", " ");
		for (int i = 0; i < binaryRule.length(); ++i) {
			joiner.add(Character.toString(binaryRule.charAt(i)));
		}
		String table = "111 110 101 100 011 010 001 000" + System.lineSeparator() + " " + joiner.toString();
		table = table.replace('0', falseSymbol);
		table = table.replace('1', trueSymbol);
		return table;
	}
}

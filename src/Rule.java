
public class Rule {
	private int rule;
	private String binaryRule;

	protected Rule(int ruleNum) {
		if (ruleNum < 0) {
			ruleNum = 0;
		}
		if (ruleNum > 255) {
			ruleNum = 255;
		}
		binaryRule = String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
		rule = ruleNum;
	}

	public int getRuleNum() {
		return rule;
	}

	public static boolean[] getNeighborhood(int index, Generation gen) {

		boolean[] result = new boolean[3];
		if (gen.size() == 1) {
			result[0] = result[1] = result[2] = gen.getState(0);
			return result;
		}
		result[1] = gen.getState(index);
		if (index - 1 < 0) {
			result[0] = gen.getState(gen.size() - 1);
			result[2] = gen.getState(index + 1);
			return result;
		}
		if (index + 1 > gen.size() - 1) {
			result[0] = gen.getState(index - 1);
			result[2] = gen.getState(0);
			return result;
		}
		result[0] = gen.getState(index - 1);
		result[2] = gen.getState(index + 1);
		return result;
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

	public Generation evolve(Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		for (int i = 0; i < gen.size(); ++i) {
			temp[i] = evolve(getNeighborhood(i, gen));
		}
		Generation result = new Generation(temp);
		return result;
	}
	
}
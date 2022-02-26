
public abstract class Rule {
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

	public abstract boolean[] getNeighborhood(int idx, Generation gen);

	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		boolean[] result = new boolean[radius * 2 + 1];
		int range = idx - radius;
		for (int i = 0; i < result.length; ++i, ++range) {
			result[i] = gen.getState(Math.floorMod(range, gen.size()));
		}
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
			temp[i] = evolve(getNeighborhoodByRadius(i, 1, gen));
		}
		Generation result = new Generation(temp);
		return result;
	}

	public String ruleTableString(char falseSymbol, char trueSymbol) {
		return null;
	}

}
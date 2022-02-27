
public abstract class Rule {
	private int ruleNum;
	protected String binaryRule;
	protected int radiusSize;

	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		binaryRule = String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
	}

	public int getRuleNum() {
		return ruleNum;
	}

	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	
	public abstract boolean evolve(boolean[] neighborhood);

	public abstract String ruleTableString(char falseSymbol, char trueSymbol);
	
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		boolean[] result = new boolean[radius * 2 + 1];
		int range = idx - radius;
		for (int i = 0; i < result.length; ++i, ++range) {
			result[i] = gen.getState(Math.floorMod(range, gen.size()));
		}
		return result;
	}

	public Generation evolve(Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		for (int i = 0; i < gen.size(); ++i) {
			temp[i] = evolve(getNeighborhoodByRadius(i, radiusSize, gen));
		}
		Generation result = new Generation(temp);
		return result;
	}
}
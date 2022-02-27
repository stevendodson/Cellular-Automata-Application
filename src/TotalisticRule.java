
public class TotalisticRule extends Rule {
	protected TotalisticRule(int ruleNum) {
		super(ruleNum);
		
	}
	public boolean evolve(boolean[] neighborhood) {
		return false;
	}
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return null;
	}
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		return null;
	}
}

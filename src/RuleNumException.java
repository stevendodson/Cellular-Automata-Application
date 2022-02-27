
public class RuleNumException extends Exception {
	private static long serialVersionUID;
	
	public RuleNumException(int min, int max) {
		super("ruleNum is outside the range [" + min + ", " + max + "].");
	}
}

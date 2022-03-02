
public class RuleNumException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public RuleNumException(int min, int max) {
		super("ruleNum is outside the range [" + min + ", " + max + "].");
	}
}

/**
 * This class is a throwable class that indicates that the rule number is
 * invalid.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class RuleNumException extends Exception {

	/** Default UID */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the Exception constructor, passing a custom message.
	 * 
	 * @param min the minimum valid rule number
	 * @param max the maximum valid rule number
	 */
	public RuleNumException(int min, int max) {
		super("ruleNum is outside the range [" + min + ", " + max + "].");
	}
}

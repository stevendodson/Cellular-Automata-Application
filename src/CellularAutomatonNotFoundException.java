
/**
 * This class is a throwable class that indicates that the Cellular Automaton
 * type is invalid.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class CellularAutomatonNotFoundException extends Exception {

	/** Default UID */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the Exception constructor, passing custom message.
	 * 
	 * @param s the invalid Automaton type
	 */
	public CellularAutomatonNotFoundException(String s) {
		super("Unknown cellular automaton type " + s);
	}
}

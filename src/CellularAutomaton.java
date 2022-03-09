/**
 * This documents Cellular Automata. Valid Cellular Automaton types are "ECA"
 * and "TCA"
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public enum CellularAutomaton {

	/** Represents an Elementary Cellular Automaton */
	ECA,
	/** Represents a Totalistic Cellular Automaton */
	TCA;

	/**
	 * Parses a String and returns a Cellular Automaton type.
	 * 
	 * @param s String representation of the Cellular Automaton type
	 * @return the Cellular Automaton value
	 * @throws CellularAutomatonNotFoundException if the given String is not a valid
	 *                                            Cellular Automaton type
	 */
	public static CellularAutomaton parse(String s) throws CellularAutomatonNotFoundException {
		if (s.equalsIgnoreCase("ECA")) {
			return ECA;
		} else if (s.equalsIgnoreCase("TCA")) {
			return TCA;
		} else {
			throw new CellularAutomatonNotFoundException(s);
		}
	}
}

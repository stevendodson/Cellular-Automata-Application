import java.util.Arrays;

/**
 * Represents the cells of a one-dimensional, two-state cellular automata at a
 * single moment in time.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class Generation {
	/** Holds the state of each cell in the Generation. */
	private boolean[] cellStates;

	/**
	 * Construct a Generation with the given states.
	 * 
	 * @param states list of states for the Generation
	 */
	public Generation(boolean... states) {
		if (states == null || states.length == 0) {
			cellStates = new boolean[1];
			cellStates[0] = false;
			return;
		}
		cellStates = Arrays.copyOf(states, states.length);
	}

	/**
	 * Construct a Generation using the given string and a character to represent
	 * true.
	 * 
	 * @param states string to be parsed and used to create a Generation
	 * @param trueSymbol character to represent true
	 */
	public Generation(String states, char trueSymbol) {
		if (states == null || states == "") {
			cellStates = new boolean[1];
			cellStates[0] = false;
			return;
		}
		cellStates = new boolean[states.length()];
		for (int i = 0; i < states.length(); ++i) {
			if (states.charAt(i) == trueSymbol) {
				cellStates[i] = true;
			} else {
				cellStates[i] = false;
			}
		}
	}

	/**
	 * Return the state of the given index in Generation.
	 * 
	 * @param index the index of the desired element
	 * @return the boolean value at the given index
	 */
	public boolean getState(int index) {
		return cellStates[index];
	}

	/**
	 * Returns an array containing the states of every cell in the Generation object.
	 * 
	 * @return a boolean array containing the states of each cell in the Generation
	 *         object
	 */
	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, size());
	}

	/**
	 * Returns a String representation of the Generation object with the given true symbol and false symbol.
	 * 
	 * @param falseSymbol character to be used to represent false
	 * @param trueSymbol character to be used to represent true
	 * @return a String representation of the Generation object with the given true symbol and false symbol
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < size(); ++i) {
			if (cellStates[i] == true) {
				str.append(trueSymbol);
			} else {
				str.append(falseSymbol);
			}
		}
		return str.toString();
	}

	/**
	 * Returns the number of cells in the Generation object.
	 * @return the number of cells in the Generation object
	 */
	public int size() {
		return cellStates.length;
	}
}

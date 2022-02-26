import java.util.Arrays;

public class Generation {
	private boolean[] cellStates;

	public Generation(boolean... states) {
		if (states == null || states.length == 0) {
			cellStates = new boolean[1];
			cellStates[0] = false;
			return;
		}
		cellStates = Arrays.copyOf(states, states.length);
	}

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

	public boolean getState(int index) {
		return cellStates[index];
	}

	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, size());
	}

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

	public int size() {
		return cellStates.length;
	}
}

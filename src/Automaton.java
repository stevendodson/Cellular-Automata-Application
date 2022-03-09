import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * This class represents any one-dimensional, two-state cellular automaton that
 * evolves according to a rule represented by the Rule class.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public abstract class Automaton {
	/** The rule used to evolve the Automaton */
	private Rule rule;
	/** An ArrayList containing generations at different stages of evolution */
	private ArrayList<Generation> generations;
	/** The character used to represent false */
	public char falseSymbol;
	/** The character used to represent true */
	public char trueSymbol;

	/**
	 * Constructs a new Automaton object with the given rule number and initial
	 * generation.
	 * 
	 * @param ruleNum the rule number used to evolve the Automaton
	 * @param initial a reference to the initial generation of the Automaton
	 * @throws RuleNumException if ruleNum is outside the bounds for the Automaton
	 *                          type
	 */
	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		rule = createRule(ruleNum);
		generations = new ArrayList<Generation>(1);
		generations.add(initial);

		falseSymbol = '0';
		trueSymbol = '1';
	}

	/**
	 * Constructs a new Automaton by parsing a specifically formatted text file.
	 * 
	 * @param filename the name of the file to be parsed
	 * @throws IOException      if the file is not able to be read
	 * @throws RuleNumException if the given rule number is outside the bounds for
	 *                          the Automaton type
	 */
	protected Automaton(String filename) throws IOException, RuleNumException {
		if (filename == null || filename == "") {
			return;
		}
		BufferedReader br = new BufferedReader(new FileReader(filename));
		rule = createRule(Integer.valueOf(br.readLine()));
		falseSymbol = (char) br.read();
		br.read();
		trueSymbol = (char) br.read();
		br.readLine();
		Generation initial = new Generation(br.readLine(), trueSymbol);

		generations = new ArrayList<Generation>(1);
		generations.add(initial);
		br.close();
	}

	/**
	 * Evolves the Automaton by the given number of steps.
	 * 
	 * @param numSteps the number of steps to evolve the Automaton
	 * @return the number of steps the Automaton was evolved by
	 */
	public int evolve(int numSteps) {
		if (numSteps <= 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < numSteps; ++i) {
			generations.add(rule.evolve(getCurrentGeneration()));
			++count;
		}
		return count;
	}

	/**
	 * Returns the Generation at the given step number
	 * 
	 * @param stepNum the zero-indexed step number to get the generation at
	 * @return the Generation at the given step number
	 */
	public Generation getGeneration(int stepNum) {
		if (stepNum > generations.size() - 1) {
			evolve(stepNum - (generations.size() - 1));
		}
		return generations.get(stepNum);
	}

	/**
	 * Returns the current Generation. The current generation is also the most
	 * evolved generation.
	 * 
	 * @return the current generation
	 */
	public Generation getCurrentGeneration() {
		return generations.get(generations.size() - 1);
	}

	/**
	 * Returns the rule number being used to evolve the generation.
	 * 
	 * @return the rule number of the Automaton
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}

	/**
	 * Returns the number of steps the Automaton has been evolved.
	 * 
	 * @return the number of steps the Automaton has been evolved
	 */
	public int getTotalSteps() {
		return generations.size() - 1;
	}

	/**
	 * Saves the evolution of the Automaton to a file with the given filename.
	 * 
	 * @param filename the name of the file to store the information
	 * @throws IOException if the file cannot be created or written to
	 */
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
		bw.write(toString());
		bw.close();
	}

	/**
	 * Returns a String representation of the Automaton.
	 * 
	 * @return a String representation of the Automaton.
	 */
	public String toString() {
		StringJoiner str = new StringJoiner(System.lineSeparator());
		for (int i = 0; i <= getTotalSteps(); ++i) {
			str.add(generations.get(i).getStates(falseSymbol, trueSymbol));
		}
		return str.toString();
	}

	/**
	 * Returns a String representation of the rule.
	 * 
	 * @return a String representation of the rule
	 */
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}

	/**
	 * Creates a rule object with the given rule number.
	 * 
	 * @param ruleNum the rule number to be used
	 * @return the rule object
	 * @throws RuleNumException if ruleNum is outside the bounds for the Rule type
	 */
	abstract protected Rule createRule(int ruleNum) throws RuleNumException;

	/**
	 * Creates an Automaton object given a CellularAutomaton value.
	 * 
	 * @param ca      the CellularAutomaton value
	 * @param ruleNum the rule number to be used
	 * @param initial a reference to the initial generation
	 * @return the reference to the Automaton instance, or null if ca is null
	 * @throws RuleNumException if ruleNum is out of bounds for the rule type
	 */
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial)
			throws RuleNumException {
		if (ca == CellularAutomaton.ECA) {
			return new ElementaryAutomaton(ruleNum, initial);
		}
		if (ca == CellularAutomaton.TCA) {
			return new TotalisticAutomaton(ruleNum, initial);
		}
		return null;
	}

}

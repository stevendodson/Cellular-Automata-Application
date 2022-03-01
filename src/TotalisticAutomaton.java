import java.io.IOException;

/**
 * This class represents any one-dimensional, two-state cellular automaton that
 * evolves according to the rules represented by TotalisticRule.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class TotalisticAutomaton extends Automaton {

	/**
	 * Constructs a new Totalistic Automaton object with the given rule number and
	 * initial generation.
	 * 
	 * @param ruleNum the rule number used to evolve the automaton
	 * @param initial a reference to the initial generation to be used
	 * @throws RuleNumException if ruleNum is less than 0 or greater than 63
	 */
	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}

	/**
	 * Constructs a new Totalistic Automaton by parsing a specifically formatted
	 * text file.
	 * 
	 * @param filename filename the name of the file to be parsed
	 * @throws NumberFormatException if the elements of the file with the given file
	 *                               name are not formatted correctly
	 * @throws IOException           if the file is not able to be read
	 * @throws RuleNumException      if the rule number is less than 0 or greater
	 *                               than 63
	 */
	protected TotalisticAutomaton(String filename) throws NumberFormatException, IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		return new TotalisticRule(ruleNum);
	}
}

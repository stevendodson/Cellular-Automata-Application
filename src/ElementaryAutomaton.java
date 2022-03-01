import java.io.IOException;

/**
 * This class represents any one-dimensional, two-state cellular automaton that
 * evolves according to the rules represented by ElementaryRule.
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class ElementaryAutomaton extends Automaton {

	/**
	 * Constructs a new Elementary Automaton object with the given rule number and
	 * initial generation.
	 * 
	 * @param ruleNum the rule number used to evolve the automaton
	 * @param initial a reference to the initial generation to be used
	 * @throws RuleNumException if ruleNum is less than 0 or greater than 255
	 */
	protected ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}

	/**
	 * Constructs a new Elementary Automaton by parsing a specifically formatted
	 * text file.
	 * 
	 * @param filename the name of the file to be parsed
	 * @throws IOException           if the file is not able to be read
	 * @throws NumberFormatException if the elements of the file with the given file
	 *                               name are not formatted correctly
	 * @throws RuleNumException      if the rule number is less than 0 or greater
	 *                               than 255
	 */
	protected ElementaryAutomaton(String filename) throws IOException, NumberFormatException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		return new ElementaryRule(ruleNum);
	}
}

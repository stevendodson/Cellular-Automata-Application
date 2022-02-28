import java.io.IOException;

public class TotalisticAutomaton extends Automaton {

	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	protected TotalisticAutomaton(String filename) throws NumberFormatException, IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		return new TotalisticRule(ruleNum);
	}
}

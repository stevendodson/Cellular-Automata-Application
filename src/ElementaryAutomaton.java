import java.io.IOException;

public class ElementaryAutomaton extends Automaton {
	
	protected ElementaryAutomaton(int ruleNum, Generation initial) {
		super(ruleNum, initial);
	}
	protected ElementaryAutomaton(String filename) throws IOException {
		super(filename);
	}
	
	@Override
	protected Rule createRule(int ruleNum) {
		// TODO Auto-generated method stub
		return null;
	}

}

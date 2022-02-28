import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class Automaton {
	private Rule rule;
	private ArrayList<Generation> generations;
	public char falseSymbol = '0';
	public char trueSymbol = '1';

	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		if (initial == null) {
			return;
		}
		rule = createRule(ruleNum);
		generations = new ArrayList<Generation>(1);
		generations.add(initial);

		falseSymbol = '0';
		trueSymbol = '1';
	}

	protected Automaton(String filename) throws NumberFormatException, IOException, RuleNumException {
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

	public Generation getGeneration(int stepNum) {
		if (stepNum > generations.size() - 1) {
			evolve(stepNum - (generations.size() - 1));
		}
		return generations.get(stepNum);
	}

	public Generation getCurrentGeneration() {
		return generations.get(generations.size() - 1);
	}

	public int getRuleNum() {
		return rule.getRuleNum();
	}

	public int getTotalSteps() {
		return generations.size() - 1;
	}

	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
		bw.write(toString());
		bw.close();
	}

	public String toString() {
		StringJoiner str = new StringJoiner(System.lineSeparator());
		for (int i = 0; i <= getTotalSteps(); ++i) {
			str.add(generations.get(i).getStates(falseSymbol, trueSymbol));
		}
		return str.toString();
	}
	
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}
	
	abstract protected Rule createRule(int ruleNum) throws RuleNumException;
	
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial) throws RuleNumException {
		if (ca == CellularAutomaton.ECA) {
			return new ElementaryAutomaton(ruleNum, initial);
		}
		if (ca == CellularAutomaton.TCA) {
			return new TotalisticAutomaton(ruleNum, initial);
		}
		return null;
	}

}

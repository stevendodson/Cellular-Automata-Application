
/**
 * An application to create and simulate a cellular automaton
 * 
 * @author Steven Dodson
 * @version 1.0
 */
public class Application {
	/** The number of expected command-line arguments */
	private static final int NUM_EXPECTED_ARGS = 6;

	/** Expected index of the Cellular Automaton type */
	private static final int IDX_CA = 0;
	/** Expected index of the rule number */
	private static final int IDX_RULE_NUM = 1;
	/** Expected index of the false symbol to be used */
	private static final int IDX_FALSE_SYMBOL = 2;
	/** Expected index of the true symbol to be used */
	private static final int IDX_TRUE_SYMBOL = 3;
	/** Expected index of the initial Generation */
	private static final int IDX_INITIAL_GENERATION = 4;
	/** Expected index of the number of times the Generation is to be evolved */
	private static final int IDX_NUM_EVOLVE = 5;

	/** The expected arguments in order */
	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";

	// Source and class file names must match, so Application can be hard-coded
	/** String containing the expected usage at the command-line */
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	/** String containing the expected usage if running as jar */
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	/** Array of strings to hold command-line arguments */
	private String[] appArgs;

	/**
	 * Constructs an Application and validates the number of arguments
	 * 
	 * @param args the arguments to be used to create the Cellular Automaton
	 */
	public Application(String[] args) {
		validateNumArgs(args);
		appArgs = args;
	}

	/**
	 * Validates the number of arguments given, calling
	 * throwRuntimeExceptionWithUsageMessage() if the number of arguments given
	 * differs from the number of expected arguments.
	 * 
	 * @param args the array of arguments to be validated
	 */
	private void validateNumArgs(String[] args) {
		if (args.length != NUM_EXPECTED_ARGS) {
			throwRuntimeExceptionWithUsageMessage();
		}
	}

	/**
	 * Throws the necessary runtime exception with the corresponding usage message.
	 */
	private void throwRuntimeExceptionWithUsageMessage() {
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	/**
	 * Returns true if the Application is running as jar, returns false otherwise.
	 * 
	 * @return true if the Application is running as jar, returns false otherwise
	 */
	private boolean runningAsJar() {
		return Application.class.getResource("Application.class").toString().startsWith("jar");
	}

	/**
	 * Parses the given arguments, constructs the appropriate Automaton, and prints
	 * the evolution to System.out
	 * 
	 * @param args the arguments to be parsed and used to create the Cellular
	 *             Automaton
	 * @throws RuntimeException if an exception is thrown at any time
	 */
	private void parseArgs(String[] args) throws RuntimeException {
		// Parse each of the six arguments, construct the appropriate
		// Automaton, and print out the full evolution to System.out.
		// Refer to the README for details on exception handling.
		try {
			CellularAutomaton ca = CellularAutomaton.parse(args[IDX_CA]);
			int ruleNum = Integer.parseInt(args[IDX_RULE_NUM]);
			Generation gen = new Generation(args[IDX_INITIAL_GENERATION], args[IDX_TRUE_SYMBOL].charAt(0));
			int numEvolutions = Integer.parseInt(args[IDX_NUM_EVOLVE]);
			Automaton a = Automaton.createAutomaton(ca, ruleNum, gen);
			a.falseSymbol = args[IDX_FALSE_SYMBOL].charAt(0);
			a.trueSymbol = args[IDX_TRUE_SYMBOL].charAt(0);
			a.evolve(numEvolutions);
			System.out.println(a.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void run() throws RuntimeException {
		parseArgs(appArgs);
	}

	/**
	 * Constructs and runs an Application using the given command-line arguments.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		// Construct and run an Application using the supplied main method arguments.
		try {
			Application app = new Application(args);
			app.run();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
	}
}

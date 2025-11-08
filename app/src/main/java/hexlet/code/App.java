/**
 * This package contains the main application entry point.
 */
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    description = "Compares two configuration files and shows a difference."
)
public final class App implements Callable<Integer> {
    /**
     * Default stylish format.
     */
    public static final String FORMAT_STYLISH = "stylish";
    /**
     * Plain format.
     */
    public static final String FORMAT_PLAIN = "plain";

    /**
     * Path to first file.
     */
    @CommandLine.Parameters(description = "Path to first file")
    private String filepath1;
    /**
     * Path to second file.
     */
    @CommandLine.Parameters(description = "Path to second file")
    private String filepath2;
    /**
     * Output format.
     */
    @CommandLine.Option(
        names = { "-f", "--format" },
        defaultValue = "stylish",
        description = "Output format [default: ${DEFAULT-VALUE}]"
    )
    private String format = FORMAT_STYLISH;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(
                Differ.generate(this.filepath1, this.filepath2, this.format)
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return 1;
        }

        return 0;
    }

    /**
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(description = "path to second file")
    private String filepath2;
    @CommandLine.Option(names = { "-f", "--format" }, defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(this.filepath1, this.filepath2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(this.format);

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

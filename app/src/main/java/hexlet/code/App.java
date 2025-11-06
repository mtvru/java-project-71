package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "gendiff", mixinStandardHelpOptions = true, description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @CommandLine.Parameters(description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(description = "path to second file")
    private String filepath2;
    @CommandLine.Option(names = { "-f", "--format" }, defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public void run() {
        try {
            System.out.println(Differ.generate(this.filepath1, this.filepath2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(this.format);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "app", mixinStandardHelpOptions = true, subcommands = { GenDiffCommand.class })
public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

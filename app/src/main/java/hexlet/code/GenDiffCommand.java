package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class GenDiffCommand implements Runnable {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = { "-f", "--format" }, defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public void run() {
        System.out.println(Differ.generate(this.filepath1, this.filepath2));
    }
}

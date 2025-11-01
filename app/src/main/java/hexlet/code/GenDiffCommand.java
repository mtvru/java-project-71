package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", description = "Generate diff between two files")
public class GenDiffCommand implements Runnable {
    @Parameters(index = "0", description = "First file")
    private String file1;
    @Parameters(index = "1", description = "Second file")
    private String file2;

    @Override
    public void run() {
        System.out.println(Differ.generate(this.file1, this.file2));
    }
}

package ru.nsu.romanov.filter.command;

import lombok.Getter;
import picocli.CommandLine;
import ru.nsu.romanov.filter.service.Service;
import java.io.File;
import java.io.IOException;

@Getter
@CommandLine.Command(
    description = "filter input file into three files: integers.txt, floats.txt, strings.txt"
)
public class CommandLineRunner implements Runnable{
    @CommandLine.Option(names = "-s", description = "is brief info")
    private boolean isBrief = false;
    @CommandLine.Option(names = "-f", description = "is full info")
    private boolean isFull = false;
    @CommandLine.Option(names = "-o", description = "path to file")
    private String path;
    @CommandLine.Option(names = "-p", description = "prefix to output file")
    private String prefix;
    @CommandLine.Option(names = "-a", description = "is rewrite file")
    private boolean isRewrite;
    @CommandLine.Parameters(paramLabel = "FILE", description = "one or more files to filter")
    File[] files;

    @Override
    public void run() {
        try {
            new Service(this).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

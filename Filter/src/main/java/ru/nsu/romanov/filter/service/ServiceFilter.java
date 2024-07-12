package ru.nsu.romanov.filter.service;

import ru.nsu.romanov.filter.command.CommandLineRunner;
import ru.nsu.romanov.filter.service.parser.Parser;
import ru.nsu.romanov.filter.service.writer.Writer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ServiceFilter {
    public ServiceFilter(CommandLineRunner commandLineRunner) {
        this.commandLineRunner = commandLineRunner;
        validateCommandLineParameters();
    }

    public void run() throws IOException {
        var parser = new Parser(List.of(commandLineRunner.getFiles()));
        parser.parseFiles();

        writeStatistics(parser);

        writeRes(parser);
    }

    private void writeRes(Parser parser) throws IOException {
        new Writer<>(
                parser.getStatisticsInteger().getElements(),
                "integers.txt",
                commandLineRunner.getPath(),
                commandLineRunner.getPrefix(),
                commandLineRunner.isRewrite()
        ).write();

        new Writer<>(
                parser.getStatisticsFloat().getElements(),
                "floats.txt",
                commandLineRunner.getPath(),
                commandLineRunner.getPrefix(),
                commandLineRunner.isRewrite()
        ).write();

        new Writer<>(
                parser.getStatisticsString().getElements(),
                "strings.txt",
                commandLineRunner.getPath(),
                commandLineRunner.getPrefix(),
                commandLineRunner.isRewrite()
        ).write();
    }

    private void writeStatistics(Parser parser) {
        if (!commandLineRunner.isBrief() && !commandLineRunner.isFull()) {
            return;
        }

        if (commandLineRunner.isFull()) {
            System.out.println(parser.getStatisticsInteger().full()
                + parser.getStatisticsFloat().full()
                + parser.getStatisticsString().full()
            );
            return;
        }

        System.out.println(parser.getStatisticsInteger().brief()
                + parser.getStatisticsFloat().brief()
                + parser.getStatisticsString().brief()
        );
    }

    private void validateCommandLineParameters() {
        if (commandLineRunner.getFiles().length == 0) {
            throw new IllegalArgumentException("Should be at least one file to read");
        }

        var list = Arrays.stream(commandLineRunner.getFiles()).filter(file -> !file.exists()).toList();
        if (!list.isEmpty()) {
            throw new IllegalArgumentException("Invalid files: " + list);
        }
    }
    private final CommandLineRunner commandLineRunner;
}

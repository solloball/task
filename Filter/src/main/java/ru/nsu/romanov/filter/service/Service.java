package ru.nsu.romanov.filter.service;

import ru.nsu.romanov.filter.service.config.Config;
import ru.nsu.romanov.filter.service.parser.Parser;
import ru.nsu.romanov.filter.service.writer.WriterFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Main service of program.
 * Validate input file, run parser, write statistics to stdout,
 * write list of object to files.
 */
public class Service {
    public Service(Config config, File[] files) {
        this.config = config;
        this.files = List.of(files);
        validateCommandLineParameters();
    }

    public void start() throws IOException {
        var parser = new Parser(files);
        parser.parseFiles();

        writeStatistics(parser);

        writeResults(parser);
    }

    private void writeResults(Parser parser) throws IOException {
        new WriterFile<>(
                parser.getStatisticsInteger().getElements(),
                "integers.txt",
                config.path(),
                config.prefix(),
                config.shouldNotRewrite()
        ).write();

        new WriterFile<>(
                parser.getStatisticsFloat().getElements(),
                "floats.txt",
                config.path(),
                config.prefix(),
                config.shouldNotRewrite()
        ).write();

        new WriterFile<>(
                parser.getStatisticsString().getElements(),
                "strings.txt",
                config.path(),
                config.prefix(),
                config.shouldNotRewrite()
        ).write();
    }

    private void writeStatistics(Parser parser) {
        if (!config.isBrief() && !config.isFull()) {
            return;
        }

        if (config.isFull()) {
            System.out.println(parser.getStatisticsInteger().fullInfo()
                + parser.getStatisticsFloat().fullInfo()
                + parser.getStatisticsString().fullInfo()
            );
            return;
        }

        System.out.println(parser.getStatisticsInteger().briefInfo()
                + parser.getStatisticsFloat().briefInfo()
                + parser.getStatisticsString().briefInfo()
        );
    }

    private void validateCommandLineParameters() {
        if (files.isEmpty()) {
            throw new IllegalArgumentException("Should be at least one file to read");
        }

        var list = files.stream().filter(file -> !file.exists()).toList();
        if (!list.isEmpty()) {
            throw new IllegalArgumentException("Invalid files: " + list);
        }
    }
    private final Config config;
    private final List<File> files;
}

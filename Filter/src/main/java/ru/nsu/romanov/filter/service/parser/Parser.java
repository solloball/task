package ru.nsu.romanov.filter.service.parser;

import lombok.Getter;
import ru.nsu.romanov.filter.service.statistics.StatisticsFloat;
import ru.nsu.romanov.filter.service.statistics.StatisticsInteger;
import ru.nsu.romanov.filter.service.statistics.StatisticsString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Parse files and make statistics;
 */
@Getter
public class Parser {
    public Parser(List<File> files) {
        this.files = files;
    }

    public void parseFiles() {
        files.forEach(this::parseFile);
    }

    private void parseFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    var num = Long.parseLong(line);
                    statisticsInteger.add(num);     // try to parse to integer.
                    continue;
                }  catch (NumberFormatException ignored) {
                }

                try {
                    var num = Float.parseFloat(line);  // try to parse to float.
                    statisticsFloat.add(num);
                    continue;
                }  catch (NumberFormatException ignored) {
                }

                statisticsString.add(line); // it is a string.
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final StatisticsString statisticsString = new StatisticsString();
    private final StatisticsFloat statisticsFloat = new StatisticsFloat();
    private final StatisticsInteger statisticsInteger = new StatisticsInteger();
    private final List<File> files;
}

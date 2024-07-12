package ru.nsu.romanov.filter;

import picocli.CommandLine;
import ru.nsu.romanov.filter.command.CommandLineRunner;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CommandLineRunner()).execute(args);
        exit(exitCode);
    }
}
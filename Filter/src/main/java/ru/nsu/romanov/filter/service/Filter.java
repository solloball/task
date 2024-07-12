package ru.nsu.romanov.filter.service;

import ru.nsu.romanov.filter.command.CommandLineRunner;

import java.io.File;
import java.util.Arrays;

public class Filter {
    public Filter(CommandLineRunner commandLineRunner) {
        this.commandLineRunner = commandLineRunner;
        validateCommandLineParameters();
    }

    public void run() {
        System.out.println("hi!!");
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

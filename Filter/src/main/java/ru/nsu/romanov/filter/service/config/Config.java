package ru.nsu.romanov.filter.service.config;

/**
 * Config of program.
 *
 * @param isBrief is brief statistics.
 * @param isFull is full statistics
 * @param path path to output file.
 * @param prefix prefix of output file.
 * @param shouldNotRewrite true if should now rewrite file.
 */
public record Config(
        boolean isBrief,
        boolean isFull,
        String path,
        String prefix,
        boolean shouldNotRewrite
) { }

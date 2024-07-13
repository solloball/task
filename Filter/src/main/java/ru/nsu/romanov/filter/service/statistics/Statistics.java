package ru.nsu.romanov.filter.service.statistics;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

/**
 * Keep statistics and list of object;
 *
 * @param <T> type of element.
 */
@Getter
public abstract class Statistics<T> {
    public void add(T elem) {
        elements.add(elem);
        internalLogic(elem);
    }

    public String briefInfo() {
        return "\nStatistics:\nCount: " + elements.size();
    }

    abstract void internalLogic(T elem);

    private final List<T> elements = new ArrayList<>();
}

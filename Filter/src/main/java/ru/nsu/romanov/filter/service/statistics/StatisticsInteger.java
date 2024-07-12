package ru.nsu.romanov.filter.service.statistics;

import lombok.Getter;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
public class StatisticsInteger extends Statistics<Long> {

    @Override
    void internalLogic(Long elem) {
        if (max == null) {
            max = elem;
        }
        if (min == null) {
            min = elem;
        }
        max = max(max, elem);
        min = min(min, elem);
        sum += elem;
    }

    public String full() {
        return brief()
            + "\nmax: "
            + max
            + "\nmin: "
            + min
            + "\nsum: "
            + sum
            + "\naverage: "
            + getAverage();
    }

    public int getAverage() {
        if (super.getElements().isEmpty()) {
            return 0;
        }
        return sum / super.getElements().size();
    }

    private Long max;
    private Long min;
    private int sum = 0;
}

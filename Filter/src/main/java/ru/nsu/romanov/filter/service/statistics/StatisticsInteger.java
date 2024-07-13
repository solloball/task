package ru.nsu.romanov.filter.service.statistics;

import lombok.Getter;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Statistics for integer value.
 */
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

    public String fullInfo() {
        return briefInfo()
            + "\ntype: Integer"
            + "\nmax: "
            + max
            + "\nmin: "
            + min
            + "\nsum: "
            + sum
            + "\naverage: "
            + getAverage();
    }

    public long getAverage() {
        if (super.getElements().isEmpty()) {
            return 0;
        }
        return  sum / super.getElements().size();
    }

    private Long max;
    private Long min;
    private long sum = 0;
}

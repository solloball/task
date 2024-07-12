package ru.nsu.romanov.filter.service.statistics;

import lombok.Getter;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
public class StatisticsFloat extends Statistics<Float> {
    @Override
    void internalLogic(Float elem) {
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

    public float getAverage() {
        if (super.getElements().isEmpty()) {
            return 0;
        }
        return sum / super.getElements().size();
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

    private Float max = null;
    private Float min = null;
    private float sum = 0;
}

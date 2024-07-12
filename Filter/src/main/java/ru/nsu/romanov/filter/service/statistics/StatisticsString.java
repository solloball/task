package ru.nsu.romanov.filter.service.statistics;

import lombok.Getter;

@Getter
public class StatisticsString extends Statistics<java.lang.String> {
    @Override
    void internalLogic(java.lang.String elem) {
        var len = elem.length();

        if (len > stringMaxLen.length()) {
            stringMaxLen = elem;
        }

        if (len < stringMinLen.length() || stringMinLen.isEmpty()) {
            stringMinLen = elem;
        }
    }

    public String full() {
        return brief()
            + "\nstring with min len: "
            + stringMinLen
            + "\nstring with max len: "
            + stringMaxLen;
    }

    private String stringMaxLen = "";
    private String stringMinLen = "";
}

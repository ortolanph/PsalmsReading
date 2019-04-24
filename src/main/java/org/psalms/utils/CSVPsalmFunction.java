package org.psalms.utils;

import org.psalms.entities.Psalm;

import java.util.function.Function;

public class CSVPsalmFunction implements Function<String[], Psalm> {
    @Override
    public Psalm apply(String[] csvDataArray) {
        Psalm psalm = new Psalm();

        psalm.psalmNumber = Integer.valueOf(csvDataArray[0]);
        psalm.readingOrder = Integer.valueOf(csvDataArray[1]);
        psalm.read = Boolean.valueOf(csvDataArray[2]);

        return psalm;
    }
}

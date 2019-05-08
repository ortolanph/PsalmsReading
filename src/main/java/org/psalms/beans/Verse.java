package org.psalms.beans;

public class Verse {

    public int verse;
    public String text;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(verse % 2 == 0? "\t": "")
                .append(verse)
                .append(". ")
                .append(text)
                .append('\n')
                .toString();
    }
}

package org.psalms.beans;

import java.util.List;

public class FullPsalm {

    public String reference;
    public List<Verse> verses;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Psalm ").append(reference).append("\n\n");

        verses.forEach(v -> builder.append(v.toString()));

        builder.append("\n\n");

        return builder.toString();
    }
}

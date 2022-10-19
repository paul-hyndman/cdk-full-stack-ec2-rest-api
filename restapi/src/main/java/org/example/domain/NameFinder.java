package org.example.domain;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * A class which finds most/least popular name from a given String array of names
 */
public class NameFinder {
    private String[] names;

    public NameFinder(String[] names) {
        validateNameList(names);
        this.names = names;
    }

    /**
     * Implementation here is that name and rank or occurrence count is encapsulated in an inner class NameRanking.
     * NameRanking object is stored in a Map using name as key, and ranking is incremented if name is already
     * present in the Map.
     *
     * After populating Map with all unique names and their rank, or occurrence count, equality and lambda
     * expressions are used to find the most popular name.
     *
     * @return Most popular name
     */
    public String getMostPopular() {
        Map<String, NameRanking> rankedName = new HashMap();
        for (String name : names) {
            if (!rankedName.containsKey(name)) {
                rankedName.put(name, new NameRanking(name));
            }
            rankedName.get(name).rank ++;
        }
        // an Optional could also be used as below, with .stream().min(Comparator....) used instead of .max
        NameRanking rank = rankedName.values().stream().sorted().findFirst().get();
        return rank.name;
    }

    public String getLeastPopular() {
        Map<String, NameRanking> rankedName = new HashMap();
        for (String name : names) {
            if (!rankedName.containsKey(name)) {
                rankedName.put(name, new NameRanking(name));
            }
            rankedName.get(name).rank ++;
        }
        Optional<NameRanking> rank = rankedName.values().stream().max(Comparator.naturalOrder());
        return rank.get().name;
    }

    private void validateNameList(String[] names) {
        if (names == null || names.length == 0) {
            throw new InvalidParameterException("Please provide list of names");
        }
    }

    /**
     * A class encapsulating name and rank, or occurrence count.
     * Implementing a typed Comparable helps make the override clear.
     */
    class NameRanking implements Comparable<NameRanking> {
        String name;
        Integer rank;

        public NameRanking(String name) {
            this.name = name;
            rank = 1;
        }

        @Override
        public int compareTo(NameRanking o) {
            return o.rank.compareTo(rank);
        }

    }
}

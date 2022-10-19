package org.example.domain;

import org.example.domain.NameFinder;
import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class NameFinderTest {

    @Test
    public void testGetMostPopular() {
        List<String> names = new ArrayList();
        names.add("jane");
        names.add("jane");
        names.add("joe");
        names.add("joe");
        names.add("joe");
        names.add("homer");
        names.add("marge");
        names.add("marge");
        String mostPopular = new NameFinder(names.toArray(new String[0])).getMostPopular();
        Assert.assertTrue(mostPopular.equals("joe"));
    }

    @Test
    public void testGetLeastPopular() {
        List<String> names = new ArrayList();
        names.add("jane");
        names.add("jane");
        names.add("homer");
        names.add("paul");
        names.add("paul");
        names.add("joe");
        names.add("joe");
        names.add("joe");
        String leastPopular = new NameFinder(names.toArray(new String[0])).getLeastPopular();
        Assert.assertTrue(leastPopular.equals("homer"));
    }

    @Test
    public void testNullNameList() {
        try {
            new NameFinder(null).getLeastPopular();
            Assert.fail("Expected exception not thrown");
        } catch (InvalidParameterException ex) {
        }
        try {
            new NameFinder(null).getMostPopular();
            Assert.fail("Expected exception now thrown");
        } catch (InvalidParameterException ex) {
        }
    }

    @Test
    public void testEmptyNameList() {
        try {
            new NameFinder(new String[0]).getLeastPopular();
            Assert.fail("Expected exception not thrown");
        } catch (InvalidParameterException ex) {
        }
        try {
            new NameFinder(new String[0]).getMostPopular();
            Assert.fail("Expected exception now thrown");
        } catch (InvalidParameterException ex) {
        }
    }
}

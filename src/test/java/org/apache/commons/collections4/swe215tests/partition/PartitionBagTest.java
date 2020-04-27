package org.apache.commons.collections4.swe215tests.partition;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartitionBagTest {
    @Test
    public void testEmptyTreeBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        assertEquals(bag1.getCount(emptyString), 0);

        assertFalse(bag1.uniqueSet().contains(emptyString));
    }

    @Test
    public void testEmptyStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        assertEquals(bag1.getCount(nonEmptyString), 0);

        assertFalse(bag1.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testAddZeroStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 0);

        assertEquals(bag1.getCount(nonEmptyString), 0);

        assertFalse(bag1.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        assertTrue(bag1.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddOneStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 1);

        assertEquals(bag1.getCount(nonEmptyString), 1);

        assertTrue(bag1.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddNegativeStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, -1);

        assertEquals(bag1.getCount(nonEmptyString), 0);

        assertFalse(bag1.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddRemoveExactMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 10);

        assertEquals(bag1.getCount(nonEmptyString), 10);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 0);

        assertFalse(bag1.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testAddRemoveZeroMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 10);

        assertEquals(bag1.getCount(nonEmptyString), 10);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        bag1.remove(nonEmptyString, 0);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        assertTrue(bag1.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testAddRemoveOneMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 10);

        assertEquals(bag1.getCount(nonEmptyString), 10);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        bag1.remove(nonEmptyString, 1);

        assertEquals(bag1.getCount(nonEmptyString), 4);

        assertTrue(bag1.uniqueSet().contains(nonEmptyString));

    }
    @Test
    public void testAddRemoveNegativeMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 10);

        assertEquals(bag1.getCount(nonEmptyString), 10);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        bag1.remove(nonEmptyString, -1);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        assertTrue(bag1.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testAddRemoveExtraMultipleStringBag() {
        Bag<String> bag1 = new TreeBag<>();
        String emptyString = "";

        bag1.add(emptyString);

        assertEquals(bag1.getCount(emptyString), 1);

        assertTrue(bag1.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        bag1.add(nonEmptyString, 10);

        assertEquals(bag1.getCount(nonEmptyString), 10);

        bag1.remove(nonEmptyString, 5);

        assertEquals(bag1.getCount(nonEmptyString), 5);

        bag1.remove(nonEmptyString, 6);

        assertEquals(bag1.getCount(nonEmptyString), 0);

        assertFalse(bag1.uniqueSet().contains(nonEmptyString));

    }
}

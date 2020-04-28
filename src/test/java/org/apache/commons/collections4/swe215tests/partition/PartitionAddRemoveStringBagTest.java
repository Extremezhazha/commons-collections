package org.apache.commons.collections4.swe215tests.partition;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartitionAddRemoveStringBagTest {
    TreeBag<String> m_treebag;
    String nonEmptyString;

    @Before
    public void setupTreeBag(){
        m_treebag = new TreeBag<>();
        nonEmptyString = "string content";

        m_treebag.add(nonEmptyString, 10);

        assertEquals(m_treebag.getCount(nonEmptyString), 10);

        m_treebag.remove(nonEmptyString, 5);

        assertEquals(m_treebag.getCount(nonEmptyString), 5);
    }

    @Test
    public void testRemoveNegativeStringBag() {
        assertFalse(m_treebag.remove(nonEmptyString, -1));

        assertEquals(m_treebag.getCount(nonEmptyString), 5);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testRemoveZeroStringBag() {
        assertFalse(m_treebag.remove(nonEmptyString, 0));

        assertEquals(m_treebag.getCount(nonEmptyString), 5);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testRemoveOneStringBag() {
        assertTrue(m_treebag.remove(nonEmptyString, 1));

        assertEquals(m_treebag.getCount(nonEmptyString), 4);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testRemoveExactStringBag() {
        assertTrue(m_treebag.remove(nonEmptyString, 5));

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testRemoveExtraStringBag() {
        assertTrue(m_treebag.remove(nonEmptyString, 6));

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));

    }

    @Test
    public void testNonExistZeroStringBag() {
        assertFalse(m_treebag.remove("", 0));
    }

    @Test
    public void testNonExistMultipleStringBag() {
        assertFalse(m_treebag.remove("", 6));
    }
}

package org.apache.commons.collections4.swe215tests.partition;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartitionAddStringBagTest {
    TreeBag<String> m_treebag;
    String nonEmptyString;

    @Before
    public void setupEmptyTreeBag(){
        m_treebag = new TreeBag<>();
        nonEmptyString = "string content";
    }

    @Test
    public void testAddNegativeStringBag() {
        m_treebag.add(nonEmptyString, -1);

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddZeroStringBag() {
        m_treebag.add(nonEmptyString, 0);

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddOneStringBag() {
        m_treebag.add(nonEmptyString, 1);

        assertEquals(m_treebag.getCount(nonEmptyString), 1);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddMultipleStringBag() {
        m_treebag.add(nonEmptyString, 5);

        assertEquals(m_treebag.getCount(nonEmptyString), 5);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));
    }
}

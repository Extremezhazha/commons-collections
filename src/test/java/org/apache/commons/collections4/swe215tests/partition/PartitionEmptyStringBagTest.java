package org.apache.commons.collections4.swe215tests.partition;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartitionEmptyStringBagTest {
    TreeBag<String> m_treebag;
    String emptyString;

    @Before
    public void setupEmptyTreeBag(){
        m_treebag = new TreeBag<>();
        emptyString = "";
    }

    @Test
    public void testEmptyTreeBag() {
        assertEquals(m_treebag.getCount(emptyString), 0);
        assertFalse(m_treebag.uniqueSet().contains(emptyString));
    }

    @Test
    public void testOneEmptyStringBag() {
        assertTrue(m_treebag.add(emptyString));
        assertEquals(m_treebag.getCount(emptyString), 1);
        assertTrue(m_treebag.uniqueSet().contains(emptyString));
    }

    @Test
    public void testZeroNonEmptyStringBag() {
        assertTrue(m_treebag.add(emptyString));
        assertEquals(m_treebag.getCount(emptyString), 1);
        assertTrue(m_treebag.uniqueSet().contains(emptyString));

        String nonEmptyString = "string content";

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));

    }
}

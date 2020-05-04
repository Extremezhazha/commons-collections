package org.apache.commons.collections4.swe215tests.functionalmodels;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;

import static org.apache.commons.collections4.swe215tests.functionalmodels.FSMStateTreeBagTest.*;
import static org.junit.Assert.*;

public class FSMPathTreeBagTest {
    Bag<String> m_treebag = new TreeBag<>();
    String string1 = "string content";
    String string2 = "string content 1";
    String string3 = "string content 2";

    @Test
    public void testPathAdd011nn() {
        isAtq0(m_treebag);
        m_treebag.add(string1, 1);
        isAtq1(m_treebag);
        m_treebag.add(string1, 10);
        isAtq1(m_treebag);
        m_treebag.add(string2, 1);
        isAtqn(m_treebag);
        m_treebag.add(string2, 10);
        isAtqn(m_treebag);
    }

    @Test
    public void testPathRemovenn110() {
        m_treebag.add(string1, 10);
        m_treebag.add(string2, 10);
        m_treebag.add(string3, 10);
        isAtqn(m_treebag);
        m_treebag.remove(string2, 5);
        m_treebag.remove(string3, 10);
        isAtqn(m_treebag);
        m_treebag.remove(string2, 5);
        isAtq1(m_treebag);
        m_treebag.remove(string1, 5);
        isAtq1(m_treebag);
        m_treebag.remove(string1, 5);
        isAtq0(m_treebag);
    }

    @Test
    public void testPathAddRemove011n1n10() {
        isAtq0(m_treebag);
        m_treebag.add(string1, 5);
        isAtq1(m_treebag);
        m_treebag.add(string1, 5);
        isAtq1(m_treebag);
        m_treebag.add(string2, 10);
        isAtqn(m_treebag);
        m_treebag.remove(string2, 10);
        isAtq1(m_treebag);
        m_treebag.add(string3, 10);
        isAtqn(m_treebag);
        m_treebag.remove(string3, 10);
        isAtq1(m_treebag);
        m_treebag.remove(string1, 10);
        isAtq0(m_treebag);
    }
}

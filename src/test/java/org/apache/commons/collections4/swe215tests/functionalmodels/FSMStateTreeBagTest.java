/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.swe215tests.functionalmodels;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class FSMStateTreeBagTest {
    Bag<String> m_treebag = new TreeBag<>();

    public static void isAtq0(Bag<String> stringBag) {
        assertEquals(stringBag.getCount(""), 0);
        assertTrue(stringBag.uniqueSet().isEmpty());
        assertEquals(stringBag.size(), 0);

        Iterator<String> it = stringBag.iterator();

        assertFalse(it.hasNext());
    }

    public static void isAtq1(Bag<String> stringBag) {
        assertFalse(stringBag.uniqueSet().isEmpty());
        assertEquals(stringBag.uniqueSet().size(), 1);

        assertTrue(stringBag.size() >= 1);

        Iterator<String> it = stringBag.iterator();

        assertTrue(it.hasNext());

        String firstValue = it.next();

        while (it.hasNext()) {
            assertEquals(firstValue, it.next());
        }
    }

    public static void isAtqn(Bag<String> stringBag) {
        Set<String> uniqueSet = stringBag.uniqueSet();
        assertFalse(uniqueSet.isEmpty());
        assertTrue(uniqueSet.size() >= 2);

        assertTrue(stringBag.size() >= 2);

        Iterator<String> it = stringBag.iterator();

        assertTrue(it.hasNext());

        int neqCount = 0;
        String firstValue = it.next();

        while (it.hasNext()) {
            String curentElement = it.next();
            assertTrue(uniqueSet.contains(curentElement));
            if (!curentElement.equals(firstValue))
                neqCount++;
        }
        assertNotEquals(neqCount, 0);
    }

    @Test
    public void testAtq0() {
        isAtq0(m_treebag);
    }

    @Test
    public void testAtq1() {
        m_treebag.add("string content", 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.getCount(""), 0);
        assertEquals(m_treebag.getCount("string content"), 10);
    }

    @Test
    public void testAtqn() {
        m_treebag.add("string content", 10);
        m_treebag.add("string content 1", 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.getCount(""), 0);
        assertEquals(m_treebag.getCount("string content"), 10);
        assertEquals(m_treebag.getCount("string content 1"), 10);
    }
}

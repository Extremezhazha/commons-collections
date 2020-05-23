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
package org.apache.commons.collections4.swe215tests.partition;

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

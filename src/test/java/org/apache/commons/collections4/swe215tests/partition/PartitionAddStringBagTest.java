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
        assertFalse(m_treebag.add(nonEmptyString, -1));

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddZeroStringBag() {
        assertFalse(m_treebag.add(nonEmptyString, 0));

        assertEquals(m_treebag.getCount(nonEmptyString), 0);

        assertFalse(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddOneStringBag() {
        assertTrue(m_treebag.add(nonEmptyString, 1));

        assertEquals(m_treebag.getCount(nonEmptyString), 1);

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));
    }

    @Test
    public void testAddMultipleStringBag() {
        assertTrue(m_treebag.add(nonEmptyString, 5));

        assertEquals(m_treebag.getCount(nonEmptyString), 5);
    }

    @Test
    public void testAddNoChangeStringBag() {
        assertTrue(m_treebag.add(nonEmptyString, 5));

        assertEquals(m_treebag.getCount(nonEmptyString), 5);

        assertFalse(m_treebag.add(nonEmptyString, 0));

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));

        assertFalse(m_treebag.add(nonEmptyString, 5));

        assertTrue(m_treebag.uniqueSet().contains(nonEmptyString));
    }
}

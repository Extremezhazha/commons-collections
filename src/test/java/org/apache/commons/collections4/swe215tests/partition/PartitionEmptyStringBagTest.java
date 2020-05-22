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

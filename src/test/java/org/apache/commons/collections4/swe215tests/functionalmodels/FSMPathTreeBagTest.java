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
        assertEquals(m_treebag.getCount(string1), 1);
        m_treebag.add(string1, 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.getCount(string1), 11);
        m_treebag.add(string2, 1);
        isAtqn(m_treebag);
        assertEquals(m_treebag.getCount(string1), 11);
        assertEquals(m_treebag.getCount(string2), 1);
        m_treebag.add(string2, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.getCount(string1), 11);
        assertEquals(m_treebag.getCount(string2), 11);
    }

    @Test
    public void testPathRemovennn110() {
        m_treebag.add(string1, 10);
        m_treebag.add(string2, 10);
        m_treebag.add(string3, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 3);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 10);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string2, 5);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 3);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 5);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string3, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 2);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 5);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.remove(string2, 5);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.remove(string1, 5);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 5);
        m_treebag.remove(string1, 5);
        isAtq0(m_treebag);
        assertEquals(m_treebag.getCount(string1), 0);
    }

    @Test
    public void testPathAddRemove011n1n10() {
        isAtq0(m_treebag);
        assertEquals(m_treebag.getCount(string1), 0);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string1, 5);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 5);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string1, 5);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string2, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 2);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 10);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.remove(string2, 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string3, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 2);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string3, 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.remove(string1, 10);
        isAtq0(m_treebag);
    }

    @Test
    public void testPathAddRemove01nnnn1() {
        isAtq0(m_treebag);
        m_treebag.add(string1, 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string2, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 2);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 10);
        assertEquals(m_treebag.getCount(string3), 0);
        m_treebag.add(string3, 10);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 3);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 10);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string2, 5);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 3);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 5);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string2, 5);
        isAtqn(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 2);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 10);
        m_treebag.remove(string3, 10);
        isAtq1(m_treebag);
        assertEquals(m_treebag.uniqueSet().size(), 1);
        assertEquals(m_treebag.getCount(string1), 10);
        assertEquals(m_treebag.getCount(string2), 0);
        assertEquals(m_treebag.getCount(string3), 0);
    }
}

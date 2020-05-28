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
package org.apache.commons.collections4.swe215tests.mocking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.bag.AbstractMapBag;
import org.apache.commons.collections4.bag.SortedMapBag;
import org.apache.commons.collections4.map.LazySortedMap;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

public class MockingTest {
    private static class MockableString implements Comparable<MockableString> {
        private String content;

        MockableString(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public int compareTo(MockableString mockableString) {
            return content.compareTo(mockableString.content);
        }
    }

    @Test
    public void TreeMapTest() {
        Bag<String> stringBag = new SortedMapBag<>(new TreeMap<>());
        stringBag.add("1");
        stringBag.add("2");
        stringBag.add("2");

        assertEquals(1, stringBag.getCount("1"));
        assertEquals(2, stringBag.getCount("2"));
    }

    @Test
    public void TrieTest() {
        Bag<String> stringBag = new SortedMapBag<>(new PatriciaTrie<>());
        stringBag.add("1");
        stringBag.add("2");
        stringBag.add("2");

        assertEquals(1, stringBag.getCount("1"));
        assertEquals(2, stringBag.getCount("2"));
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void LazyMapTest() {
        Bag<String> stringBag = new SortedMapBag<>(LazySortedMap.lazySortedMap(
                new TreeMap<>(),
                (Factory) () -> {
                    try {
                        Constructor<?> mutableIntegerConstructor = AbstractMapBag.class.getDeclaredClasses()[0].getDeclaredConstructor(int.class);
                        mutableIntegerConstructor.setAccessible(true);
                        return mutableIntegerConstructor.newInstance(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ));
        stringBag.add("1");
        stringBag.add("2");
        stringBag.add("2");

        assertEquals(1, stringBag.getCount("1"));
        assertEquals(2, stringBag.getCount("2"));
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void SpyTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TreeMap spyMap = spy(new TreeMap<>());
        Bag<MockableString> stringBag = spy(new SortedMapBag<>(spyMap));
        MockableString str3 = new MockableString("3");
        MockableString str2 = new MockableString("2");

        Constructor<?> mutableIntegerConstructor = AbstractMapBag.class.getDeclaredClasses()[0].getDeclaredConstructor(int.class);
        mutableIntegerConstructor.setAccessible(true);
        when(spyMap.get(str3)).thenReturn(mutableIntegerConstructor.newInstance(3));

        MockableString str1 = spy(new MockableString("1"));
        stringBag.add(str1);
        stringBag.add(str2);
        stringBag.add(str2);

        assertEquals(1, stringBag.getCount(str1));
        assertEquals(2, stringBag.getCount(str2));

        verify(spyMap, times(2)).get(str1);
        verify(spyMap, times(3)).get(str2);

        verify(spyMap, times(1)).put(eq(str1), any());
        verify(spyMap, times(1)).put(eq(str2), any());

        stringBag.remove(str2, 1);

        verify(spyMap, times(4)).get(str2);

        stringBag.remove(str2, 1);

        verify(spyMap, times(5)).get(str2);

        verify(spyMap, times(1)).remove(str2);
        verify(spyMap, times(0)).remove(str1);


        stringBag.remove(str1, 1);


        verify(spyMap, times(3)).get(str1);

        verify(spyMap, times(1)).remove(str1);

        assertEquals(3, stringBag.getCount(str3));

        verify(spyMap, times(1)).get(str3);
    }
}

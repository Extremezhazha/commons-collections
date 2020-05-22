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
package org.apache.commons.collections4.swe215tests.whitebox;

import org.apache.commons.collections4.*;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.apache.commons.collections4.map.FixedSizeMap;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AdditionWhiteBoxTests {
    @Test
    public void testTreeBagCollectionConstructor() {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("String 1");
        stringSet.add("String 2");
        stringSet.add("String 3");
        Bag<String> stringBag = new TreeBag<>(stringSet);

        assertEquals(stringBag.uniqueSet().size(), 3);
        assertEquals(stringBag.getCount("String 1"), 1);
        assertEquals(stringBag.getCount("String 2"), 1);
        assertEquals(stringBag.getCount("String 3"), 1);
    }

    @Test
    public void testAbstractMapBagRetainAll() {

        Set<String> stringSet = new HashSet<>();
        stringSet.add("String 1");
        stringSet.add("String 2");
        stringSet.add("String 3");
        Bag<String> stringBag = new TreeBag<>(stringSet);

        Bag<String> stringSubsetBag = new TreeBag<>();
        stringSubsetBag.add("String 1", 1);
        stringSubsetBag.add("String 2", 1);
        stringSubsetBag.add("String 4", 1);

        assertTrue(stringBag.retainAll(stringSubsetBag));

        assertEquals(2, stringBag.uniqueSet().size());
        assertFalse(stringBag.retainAll(stringSubsetBag));
    }

    @Test
    public void testPredicateUtils() {
        Predicate<String> nullPredicate = PredicateUtils.nullPredicate();

        assertTrue(nullPredicate.evaluate(null));
        assertFalse(nullPredicate.evaluate(""));

        Predicate<String> truePredicate = PredicateUtils.truePredicate();

        assertTrue(truePredicate.evaluate(null));
        assertTrue(truePredicate.evaluate(""));

        Predicate<String> falsePredicate = PredicateUtils.falsePredicate();

        assertFalse(falsePredicate.evaluate(null));
        assertFalse(falsePredicate.evaluate(""));

        Predicate<String> combinedPredicateAllTrueNull = PredicateUtils.allPredicate(truePredicate, nullPredicate);

        assertTrue(combinedPredicateAllTrueNull.evaluate(null));
        assertFalse(combinedPredicateAllTrueNull.evaluate(""));

        Predicate<String> combinedPredicateAllFalseNull = PredicateUtils.allPredicate(falsePredicate, nullPredicate);

        assertFalse(combinedPredicateAllFalseNull.evaluate(null));
        assertFalse(combinedPredicateAllFalseNull.evaluate(""));

        Predicate<String> combinedPredicateAnyTrueNull = PredicateUtils.anyPredicate(truePredicate, nullPredicate);

        assertTrue(combinedPredicateAnyTrueNull.evaluate(null));
        assertTrue(combinedPredicateAnyTrueNull.evaluate(""));

        Predicate<String> combinedPredicateAnyFalseNull = PredicateUtils.anyPredicate(falsePredicate, nullPredicate);

        assertTrue(combinedPredicateAnyFalseNull.evaluate(null));
        assertFalse(combinedPredicateAnyFalseNull.evaluate(""));


        Set<Predicate<String>> predicateSetTrueNull = new HashSet<>();
        predicateSetTrueNull.add(truePredicate);
        predicateSetTrueNull.add(nullPredicate);

        Predicate<String> combinedPredicateAllTrueNullSet = PredicateUtils.allPredicate(predicateSetTrueNull);

        assertTrue(combinedPredicateAllTrueNullSet.evaluate(null));
        assertFalse(combinedPredicateAllTrueNullSet.evaluate(""));

        Set<Predicate<String>> predicateSetFalseNull = new HashSet<>();
        predicateSetFalseNull.add(falsePredicate);
        predicateSetFalseNull.add(nullPredicate);

        Predicate<String> combinedPredicateAllFalseNullSet = PredicateUtils.allPredicate(predicateSetFalseNull);

        assertFalse(combinedPredicateAllFalseNullSet.evaluate(null));
        assertFalse(combinedPredicateAllFalseNullSet.evaluate(""));
    }

    private static <E> SetUtils.SetView<E> plainSetView(Set<E> originalSet){
        return new SetUtils.SetView<E>() {
            @Override
            protected Iterator<E> createIterator() {
                return originalSet.iterator();
            }
        };
    }

    @Test
    public void testSetUtils() {
        Set<String> stringSet = new HashSet<>();
        String emptyString = "";
        String nonEmptyString = "String 1";
        stringSet.add(null);
        stringSet.add(emptyString);
        stringSet.add(nonEmptyString);

        int val = SetUtils.hashCodeForSet(stringSet);
        assertEquals(val, emptyString.hashCode() + nonEmptyString.hashCode());

        SetUtils.SetView<String> stringSetView = plainSetView(stringSet);

        assertEquals(3, stringSetView.size());
        assertEquals(stringSet, stringSetView.toSet());
    }

    @Test
    public void testUnmodifiableBagExceptions() {
        Bag<String> stringBag = new TreeBag<>();
        stringBag.add("");
        Bag<String> unmodifiableStringBag = UnmodifiableBag.unmodifiableBag(stringBag);

        try {
            unmodifiableStringBag.add("String 1");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }

        try {
            unmodifiableStringBag.remove("String 1");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }

        try {
            Set<String> stringSet = new HashSet<>();
            unmodifiableStringBag.addAll(stringSet);
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Test
    public void testFixedSizeMapPutAll() {
        Map<String, String> stringMap = new TreeMap<>();
        stringMap.put("String 1", "Value 1");
        stringMap.put("String 2", "Value 1");
        FixedSizeMap<String, String> stringFixedSizeMap = FixedSizeMap.fixedSizeMap(stringMap);

        assertTrue(stringFixedSizeMap.isFull());
        assertEquals(stringMap.size(), stringFixedSizeMap.maxSize());

        stringMap.put("String 1", "Value 2");
        stringMap.put("String 2", "Value 2");

        stringFixedSizeMap.putAll(stringMap);

        stringMap.put("String 1", "Value 3");
        stringMap.put("String 2", "Value 3");
        stringMap.put("String 3", "Value 3");

        try {
           stringFixedSizeMap.putAll(stringMap);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testUnmodifiableMap() {
        Map<String, String> stringBidiMap = new TreeBidiMap<>();
        stringBidiMap.put("String 1", "Value 1");
        stringBidiMap.put("String 2", "Value 2");

        UnmodifiableMap<String, String> unmodifiableStringBidiMap =
                (UnmodifiableMap<String, String>) UnmodifiableMap.unmodifiableMap(stringBidiMap);

        MapIterator<String, String> bidiMapIt = unmodifiableStringBidiMap.mapIterator();

        assertTrue(bidiMapIt.hasNext());
        assertEquals("String 1", bidiMapIt.next());
        assertEquals("String 1", bidiMapIt.getKey());
        assertEquals("Value 1", bidiMapIt.getValue());
        assertTrue(bidiMapIt.hasNext());
        assertEquals("String 2", bidiMapIt.next());
        assertEquals("String 2", bidiMapIt.getKey());
        assertEquals("Value 2", bidiMapIt.getValue());
        assertFalse(bidiMapIt.hasNext());


        Map<String, String> stringMap = new TreeMap<>();
        stringMap.put("String 1", "Value 1");
        stringMap.put("String 2", "Value 2");

        UnmodifiableMap<String, String> unmodifiableStringMap =
                (UnmodifiableMap<String, String>) UnmodifiableMap.unmodifiableMap(stringMap);


        MapIterator<String, String> mapIt = unmodifiableStringMap.mapIterator();

        assertTrue(mapIt.hasNext());
        assertEquals("String 1", mapIt.next());
        assertEquals("String 1", mapIt.getKey());
        assertEquals("Value 1", mapIt.getValue());
        assertTrue(mapIt.hasNext());
        assertEquals("String 2", mapIt.next());
        assertEquals("String 2", mapIt.getKey());
        assertEquals("Value 2", mapIt.getValue());
        assertFalse(mapIt.hasNext());

    }

    @Test
    public void testEntrySetMapIterator() {

        Map<String, String> stringMap = new TreeMap<>();
        stringMap.put("String 1", "Value 1");
        stringMap.put("String 2", "Value 2");

        EntrySetMapIterator<String, String> mapIt = new EntrySetMapIterator<>(stringMap);

        assertTrue(mapIt.hasNext());
        try {
            mapIt.getKey();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        try {
            mapIt.getValue();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        try {
            mapIt.setValue("Value 1");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertEquals("String 1", mapIt.next());
        assertEquals("String 1", mapIt.getKey());
        assertEquals("Value 1", mapIt.getValue());
        assertTrue(mapIt.hasNext());
        assertEquals("String 2", mapIt.next());
        assertEquals("String 2", mapIt.getKey());
        assertEquals("Value 2", mapIt.getValue());

        assertEquals("Value 2", mapIt.setValue("Value 3"));
        assertFalse(mapIt.hasNext());

        mapIt.reset();
        assertEquals("MapIterator[]", mapIt.toString());
        assertTrue(mapIt.hasNext());
        try {
            mapIt.remove();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertEquals("String 1", mapIt.next());
        assertEquals("String 1", mapIt.getKey());
        assertEquals("Value 1", mapIt.getValue());
        assertTrue(mapIt.hasNext());
        assertEquals("String 2", mapIt.next());
        assertEquals("String 2", mapIt.getKey());
        assertEquals("Value 3", mapIt.getValue());

        assertEquals("MapIterator[String 2=Value 3]", mapIt.toString());
        mapIt.remove();

        assertFalse(mapIt.hasNext());
    }
}

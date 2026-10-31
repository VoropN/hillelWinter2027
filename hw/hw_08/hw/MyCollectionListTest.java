package hw_08.hw;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyCollectionListTest {
    private static int passed;
    private static int failed;

    public static void main(String[] args) {
        run("append and size", MyCollectionListTest::testAppendAndSize);
        run("indexed insertion", MyCollectionListTest::testIndexedInsertion);
        run("set and get", MyCollectionListTest::testSetAndGet);
        run("index boundaries", MyCollectionListTest::testIndexBoundaries);
        run("delete", MyCollectionListTest::testDelete);
        run("contain", MyCollectionListTest::testContain);
        run("equals collection", MyCollectionListTest::testEqualsCollection);
        run("clear", MyCollectionListTest::testClear);
        run("growth", MyCollectionListTest::testGrowth);

        System.out.printf("Passed: %d, failed: %d%n", passed, failed);
        if (failed > 0) {
            throw new AssertionError("Some MyCollectionList tests failed");
        }
    }

    private static void testAppendAndSize() {
        MyCollectionList list = new MyCollectionList();
        checkEquals(0, list.size(), "new list size");
        checkTrue(list.add("first"), "add should return true");
        checkTrue(list.add("second"), "add should return true");
        checkEquals(2, list.size(), "size after append");
        checkContents(list, Arrays.asList("first", "second"));
    }

    private static void testIndexedInsertion() {
        MyCollectionList list = listOf("a", "c");
        checkTrue(list.add(1, "b"), "middle insertion should return true");
        checkContents(list, Arrays.asList("a", "b", "c"));

        checkTrue(list.add(0, "start"), "front insertion should return true");
        checkContents(list, Arrays.asList("start", "a", "b", "c"));

        checkTrue(list.add(list.size(), "end"), "append by index should return true");
        checkContents(list, Arrays.asList("start", "a", "b", "c", "end"));
    }

    private static void testSetAndGet() {
        MyCollectionList list = listOf("old", "keep");
        checkTrue(list.set(0, "new"), "set should return true");
        checkEquals("new", list.get(0), "get after set");
        checkEquals("keep", list.get(1), "set must not change other elements");
    }

    private static void testIndexBoundaries() {
        MyCollectionList list = listOf("only");
        expectThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "x"));
        expectThrows(IndexOutOfBoundsException.class, () -> list.add(2, "x"));
        expectThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "x"));
        expectThrows(IndexOutOfBoundsException.class, () -> list.set(1, "x"));
        expectThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        expectThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        checkContents(list, Arrays.asList("only"));
    }

    private static void testDelete() {
        MyCollectionList list = listOf("a", "b", "a");
        checkTrue(list.delete(new String("a")), "delete should match equal string values");
        checkContents(list, Arrays.asList("b", "a"));
        checkTrue(list.delete("a"), "delete should remove a matching last element");
        checkContents(list, Arrays.asList("b"));
        checkFalse(list.delete("missing"), "delete should return false when absent");
        checkTrue(list.delete("b"), "delete should remove the only element");
        checkEquals(0, list.size(), "size after deleting all elements");
        checkFalse(list.delete("b"), "delete on empty list should return false");
    }

    private static void testContain() {
        MyCollectionList list = listOf("first", "second");
        checkTrue(list.contain(new String("first")), "contain should compare string values");
        checkFalse(list.contain("missing"), "contain should return false when absent");
        checkFalse(new MyCollectionList().contain("anything"), "empty list should contain nothing");
    }

    private static void testEqualsCollection() {
        MyCollectionList list = listOf("a", "b");
        checkTrue(list.equals(Arrays.asList("a", "b")), "equal ordered collection");
        checkFalse(list.equals(Arrays.asList("b", "a")), "different order");
        checkFalse(list.equals(Arrays.asList("a")), "different size");
        checkFalse(list.equals(Arrays.asList("a", "different")), "different value");
        checkFalse(list.equals(null), "null collection");

        MyCollectionList empty = new MyCollectionList();
        checkTrue(empty.equals(Arrays.asList()), "two empty collections");
    }

    private static void testClear() {
        MyCollectionList list = listOf("a", "b");
        checkTrue(list.clear(), "clear should return true");
        checkEquals(0, list.size(), "size after clear");
        checkFalse(list.contain("a"), "cleared list should not contain prior values");
        checkTrue(list.add("after clear"), "list should be reusable after clear");
        checkContents(list, Arrays.asList("after clear"));
    }

    private static void testGrowth() {
        MyCollectionList list = new MyCollectionList();
        for (int i = 0; i < 50; i++) {
            list.add("item-" + i);
        }
        checkEquals(50, list.size(), "size after growing");
        for (int i = 0; i < 50; i++) {
            checkEquals("item-" + i, list.get(i), "value after growth at index " + i);
        }
    }

    private static MyCollectionList listOf(String... values) {
        MyCollectionList list = new MyCollectionList();
        for (String value : values) {
            list.add(value);
        }
        return list;
    }

    private static void checkContents(MyCollectionList list, List<String> expected) {
        checkEquals(expected.size(), list.size(), "collection size");
        for (int i = 0; i < expected.size(); i++) {
            checkEquals(expected.get(i), list.get(i), "element at index " + i);
        }
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("PASS: " + name);
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("FAIL: " + name + " - " + error);
        }
    }

    private static void checkTrue(boolean actual, String message) {
        if (!actual) {
            throw new AssertionError(message);
        }
    }

    private static void checkFalse(boolean actual, String message) {
        checkTrue(!actual, message);
    }

    private static void checkEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(message + ": expected <" + expected + "> but was <" + actual + ">");
        }
    }

    private static void expectThrows(Class<? extends RuntimeException> expected, Runnable action) {
        try {
            action.run();
        } catch (RuntimeException error) {
            if (expected.isInstance(error)) {
                return;
            }
            throw new AssertionError("expected " + expected.getSimpleName() + " but got " + error, error);
        }
        throw new AssertionError("expected " + expected.getSimpleName() + " to be thrown");
    }
}

package hw_08.hw;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class MyCollectionList implements CollectionList {
    private double coefGrow = 1.2;
    private int maxSize = 10;
    private int length = 0;
    private String[] state = new String[maxSize];

    public boolean add(String o) {
        ensureSize();
        state[length++] = o;
        return true;
    }

    public boolean add(int index, String o) throws IndexOutOfBoundsException {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index out of boundaries");
        }
        ensureSize();

        String cur = state[index];
        state[index] = o;
        length++;
        for (int i = index + 1; i < length; i++) {
            String next = state[i];
            state[i] = cur;
            cur = next;
        }
        return true;
    }

    public boolean set(int index, String o) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of boundaries");
        }
        state[index] = o;
        return true;
    }

    public boolean delete(String o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(state[i], o)) {
                for (int j = i; j < length - 1; j++) {
                    state[j] = state[j + 1];
                }
                state[--length] = null;
                return true;
            }
        }
        return false;
    }

    public String get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of boundaries");
        }
        return state[index];
    }

    public boolean contain(String o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(state[i], o)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Collection collection) {
        if (
            collection == null ||
            collection.size() != length
        ) {
            return false;
        }
        Iterator<?> iterator = collection.iterator();
        for (int i = 0; i < length; i++) {
            if (!Objects.equals(state[i], iterator.next())) {
                return false;
            }
        }

        return true;
    }

    public boolean clear() {
        state = new String[maxSize];
        length = 0;
        return true;
    }

    public int size() {
        return length;
    }

    private void ensureSize() {
        if (1 + length >= maxSize) {
            copyList();
        }
    }

    private void copyList() {
        maxSize = (int) (maxSize * coefGrow);
        String[] nextState = new String[maxSize];
        for (int i = 0; i < length; i++) {
            nextState[i] = state[i];
        }
        state = nextState;
    }
}

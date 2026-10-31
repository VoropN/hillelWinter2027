package hw_08.hw;
import java.util.Collection;


public interface CollectionList {
    boolean add(String o);
    boolean add(int index, String o);
    boolean set(int index, String o);
    boolean delete (String o);
    String get(int index);
    boolean contain(String o);
    boolean equals (Collection collection);
    boolean clear();
    int size();
}

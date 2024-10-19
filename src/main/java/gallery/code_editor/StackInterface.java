package gallery.code_editor;

import java.lang.reflect.Array;

public interface StackInterface<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
    T[] toArray();
    void clear();
}

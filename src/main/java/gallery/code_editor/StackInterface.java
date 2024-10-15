package gallery.code_editor;

public interface StackInterface<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}

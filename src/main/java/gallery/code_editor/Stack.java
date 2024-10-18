package gallery.code_editor;

import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T> {
    private T[] stack;
    private int top;
    private static final int INITIAL_CAPACITY = 16;

    // Constructor with default capacity
    public Stack() {
        stack = (T[]) new Object[INITIAL_CAPACITY];
        top = -1;
    }

    // Constructor with custom capacity
    public Stack(int capacity) {
        stack = (T[]) new Object[capacity];
        top = -1;
    }

    @Override
    public void push(T item) {
        // Ensure there's space otherwise resize
        if (top == stack.length - 1) {
            resize(stack.length * 2);
        }
        stack[++top] = item;
    }

    @Override
    public T pop() {
        // Check if the stack is empty before popping
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = stack[top];
        stack[top--] = null;

        // Resize down if needed
        if (top > 0 && top == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top]; // Return the top item without removing it
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    // Resize the stack
    private void resize(int newCapacity) {
        T[] newStack = (T[]) new Object[newCapacity];
        System.arraycopy(stack, 0, newStack, 0, top + 1);
        // Replace the old stack with the new one
        stack = newStack;
    }
}

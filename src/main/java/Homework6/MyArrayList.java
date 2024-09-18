package Homework6;

import java.util.AbstractList;
import java.util.Arrays;

public class MyArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];
    private int size;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        checkSize(index);
        return (E) elements[index];
    }

    @Override
    public void add(int index, E element) {
        checkSize(index);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;

        if(size > elements.length - 1){
            ensureCapacity();
        }
    }

    @Override
    public E remove(int index) {
        checkSize(index);
        Object item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (E) item;
    }

    public int getCapacity() {
        return elements.length;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    private void checkSize(int index){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }
}

package ru.academits.repetskiy.array_list;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    int modCount;
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new IllegalArgumentException("Размерность не должна быть меньше 0: " +
                    initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENT_DATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
                && !(minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            elementData = grow();
        }
    }

    private Object[] grow() {
        return elementData = Arrays.copyOf(elementData, 2 * size);
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        //noinspection unchecked
        return (E) elementData[index];
    }

    @Override
    public void set(int index, E element) {
        rangeCheck(index);

        elementData[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == o) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;

        MyListIterator() {
        }

        ;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            currentIndex++;

            if (currentIndex >= size)
                throw new NoSuchElementException();

            if (currentIndex >= elementData.length)
                throw new ConcurrentModificationException();

            //noinspection unchecked
            return (E) elementData[currentIndex];
        }
    }

    private void add(E element, Object[] elementData, int s) {
        if (elementData.length == s) {
            elementData = grow();
        }

        elementData[s] = element;
        size++;
    }

    @Override
    public void add(E element) {
        modCount++;

        add(element, elementData, size);

    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);

        add(element, elementData, index);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Objects.deepEquals(elementData, that.elementData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(elementData), size);
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
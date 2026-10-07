package ru.academits.repetskiy.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размерность не должна быть меньше 0: " +
                    initialCapacity);
        } else if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else {
            elementData = new Object[]{};
        }
    }

    private void increaseCapacity() {
        elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }

    public void trimToSize() {
        System.arraycopy(elementData, 0, elementData, 0, size);
    }

    public int len() {
        return elementData.length;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < elementData.length) {
            return;
        }

        int newLength = Math.max(minCapacity, elementData.length * 2);
        Object[] newElementData = new Object[newLength];
        System.arraycopy(elementData, 0, newElementData, 0, size);

        elementData = newElementData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Индекс не попадает в диапазон списка: " + index
                            + ", допустимый диапазон [0, " + (size - 1) + "]");
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        //noinspection unchecked
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        elementData[index] = element;
        return element;
    }

    @Override
    public boolean add(E element) {
        if (size == elementData.length) {
            increaseCapacity();
        }

        elementData[size] = element;
        size++;

        return true;
    }


    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size == elementData.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }

        elementData[index] = element;
        size++;
    }


    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Object[] elements = collection.toArray();
        int elementsLength = elements.length;

        if (elementsLength == 0) {
            return false;
        }

        ensureCapacity(size + elementsLength);

        System.arraycopy(elements, 0, elementData, size, elementsLength);
        size += elementsLength;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        Object[] elements = collection.toArray();
        int elementsLength = elements.length;

        if (elementsLength == 0) {
            return false;
        }

        ensureCapacity(size + elementsLength);

        System.arraycopy(elementData, index, elementData, index + elementsLength, size - index);
        System.arraycopy(elements, 0, elementData, index, elementsLength);
        size += elementsLength;

        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > 0; i--) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object c : collection) {
            if (!this.contains(c)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removeElement = null;

        if (index < size - 1) {

            //noinspection unchecked
            removeElement = (E) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }

        elementData[size - 1] = null;
        size--;

        return removeElement;
    }

    @Override
    public boolean remove(Object o) {
        int objectIndex = indexOf(o);

        if (objectIndex == -1) {
            return false;
        } else {
            remove(objectIndex);
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object c : collection) {
            this.remove(c);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            currentIndex++;

            if (currentIndex >= size)
                throw new NoSuchElementException();

            if (currentIndex >= elementData.length)
                throw new ConcurrentModificationException();

            //noinspection unchecked
            return (E) elementData[currentIndex];
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayList<?> arrayList = (ArrayList<?>) o;
        return size == arrayList.size && Objects.deepEquals(elementData, arrayList.elementData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(elementData), size);
    }
}

package ru.academits.repetskiy.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[10];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размерность не должна быть меньше 0: " +
                    initialCapacity);
        }

        elements = (E[]) new Object[initialCapacity];
    }

    private void increaseCapacity() {
        int currentCapacity = elements.length;

        if (currentCapacity == 0) {
            currentCapacity = 10;
        } else {
            currentCapacity *= 2;
        }

        elements = Arrays.copyOf(elements, currentCapacity);
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }

        elements = Arrays.copyOf(elements, minCapacity);
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
                    "Индекс не попадает в диапазон списка: " + index + ", допустимый диапазон [0, " + (size - 1) + "]");
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E currentElement = elements[index];
        elements[index] = element;

        return currentElement;
    }

    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            increaseCapacity();
        }

        elements[size] = element;
        size++;
        modCount++;

        return true;
    }


    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size == elements.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
        modCount++;
    }


    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addAll(size, collection);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        Objects.requireNonNull(collection);

        @SuppressWarnings("unchecked")
        E[] newElements = (E[]) collection.toArray();
        int newElementsLength = newElements.length;

        if (newElementsLength == 0) {
            return false;
        }

        ensureCapacity(size + newElementsLength);

        System.arraycopy(elements, index, elements, index + newElementsLength, size - index);
        System.arraycopy(newElements, 0, elements, index, newElementsLength);
        size += newElementsLength;
        modCount++;

        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
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
        Objects.requireNonNull(collection);

        for (Object c : collection) {
            if (!contains(c)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        size--;
        modCount++;

        return removedElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);

        boolean step = false;
        for (Object c: collection){
            while(remove(c)){
               step = true;
            }

            return step;
        }


        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(elements, null);
            size = 0;
        }

        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorArrayList();
    }

    private class IteratorArrayList implements Iterator<E> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Индекс: " + currentIndex + " выходит за пределы размера листа: " + size);
            }

            if (currentIndex >= elements.length) {
                throw new ConcurrentModificationException("Индекс: " + currentIndex + " выходит за пределы размеры буфера листа: " + elements.length);
            }

            if (modCount != currentModCount) {
                throw new ConcurrentModificationException(
                        "Список изменён во время итерации: " + currentModCount
                                + ", фактических изменений: " + modCount);
            }

            currentIndex++;

            return elements[currentIndex];
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
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, size, a.getClass());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                sb.append(elements[i]).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(']');

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArrayList<?> arrayList = (ArrayList<?>) o;
        if (size != arrayList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(arrayList.elements[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = 31 * result + Objects.hashCode(elements[i]);
        }

        return result;
    }
}

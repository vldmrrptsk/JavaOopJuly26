package ru.academits.repetskiy.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private int size;
    private final ArrayList<E>[] table;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[10];
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размерность не должна быть меньше 0: " +
                    initialCapacity);
        } else {
            //noinspection unchecked
            table = (ArrayList<E>[]) new ArrayList[initialCapacity];
        }
    }

    private int getIndex(E e) {
        return Math.abs(e.hashCode() % table.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashTable<?> hashTable = (HashTable<?>) o;
        if (size != hashTable.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(table[i], hashTable.table[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        for (ArrayList<E> list : table) {
            for (E element : list) {
                result = 31 * result + Objects.hashCode(element);
            }
        }

        return result;
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
    public boolean contains(Object o) {
        //noinspection unchecked
        int index = getIndex((E) o);

        if (table[index] == null) {
            return false;
        }

        return table[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashIterator();
    }

    private class HashIterator implements Iterator<E> {
        private int currentIndex;
        private int listIndex;
        private E currentElement;
        private E nextElement;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }

        @Override
        public E next() {
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException(
                        "Хэш-таблица изменена во время итерации: " + currentModCount
                                + ", фактических изменений: " + modCount);
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Индекс: " + currentIndex + " выходит за пределы размера хэш-таблицы: " + size);
            }

            while (listIndex < table.length) {
                ArrayList<E> list = table[listIndex];

                if (list != null && listIndex < list.size()) {
                    nextElement = list.get(currentIndex++);
                }

                listIndex++;
                currentIndex = 0;
            }

            return currentElement;
        }
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(table, size);
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        if (!table[index].contains(e)) {
            table[index].add(e);
            size++;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        //noinspection unchecked
        int index = getIndex((E) o);

        if (table[index] == null) {
            return false;
        } else {
            table[index].remove(o);
        }

        size--;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object c : collection) {
            //noinspection unchecked
            add((E) c);
        }

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        for (Object c : collection) {
            //noinspection unchecked
            remove((E) c);
        }
        modCount++;
        return true;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return (Object[]) Arrays.copyOf(table, size, a.getClass());
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "size=" + size +
                ", table=" + Arrays.toString(table) +
                '}';
    }
}

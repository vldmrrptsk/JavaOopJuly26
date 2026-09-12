package ru.academits.repetskiy.hash_table;

public class MyHashTable<E> implements MyCollection<E> {
    private Object[] table;
    private int size;

    private static class Node<E> {
        E element;
        int hash;
        Node<E> next;

        Node(E element, int hash, Node<E> next) {
            this.element = element;
            this.hash = hash;
            this.next = next;
        }
    }

    public MyHashTable() {
        this.table = new Object[10];
    }

    public MyHashTable(int initialCapacity) {
        if (initialCapacity > 0) {
            this.table = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.table = new Object[]{};
        } else {
            throw new IllegalArgumentException("Размерность не должна быть меньше 0: " +
                    initialCapacity);
        }
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
    public boolean add(E e) {
        int h = e.hashCode();
        int index = Math.abs(h % table.length);

        if (table[index] == null) {
            table[index] = new Node<E>(e, h, null);
        } else {
            //noinspection unchecked
            Node<E> previousElement = (Node<E>) table[index];
            Node<E> currentElement = previousElement.next;
            while (currentElement != null) {
                previousElement = currentElement;
                currentElement = currentElement.next;
            }

            previousElement.next = new Node<E>(e, h, null);
        }
        size++;

        return true;
    }

    @Override
    public boolean contains(Object o) {
        int h = o.hashCode();
        int index = Math.abs(h % table.length);

        //noinspection unchecked
        Node<E> currentElement = (Node<E>) table[index];
        while (currentElement != null) {
            if (currentElement.element.equals(o)) {
                return true;
            }

            currentElement = currentElement.next;
        }

        return false;
    }

    public boolean remove(Object o) {
        int h = o.hashCode();
        int index = Math.abs(h % table.length);

        Node<E> previousElement = null;
        //noinspection unchecked
        Node<E> currentElement = (Node<E>) table[index];

        while (currentElement != null) {
            if (currentElement.element.equals(o)) {
                if (previousElement == null) {
                    table[index] = currentElement.next;
                } else {
                    previousElement.next = currentElement.next;
                }
                size--;

                return true;
            }

            previousElement = currentElement;
            currentElement = currentElement.next;
        }

        return false;
    }

    public boolean clear() {
        this.table = new Object[]{};
        size = 0;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int idx = 0;
        for (int i = 0; i < table.length; i++) {

            //noinspection unchecked
            Node<E> currentElement = (Node<E>) table[i];
            sb.append('[');

            while (currentElement != null) {
                sb.append(currentElement.element);
                if (currentElement.next != null) {
                    sb.append(", ");
                }
                currentElement = currentElement.next;
            }
            sb.append(']');
        }

        sb.append(']');
        return sb.toString();
    }
}

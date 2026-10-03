package ru.academits.repetskiy.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int size() {
        return size;
    }

    private void checkListEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пустой!");
        }
    }

    public E getFirst() {
        checkListEmpty();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Индекс не попадает в диапазон списка: " + index
                            + ", допустимый диапазон [0, " + (size - 1) + "]");
        }
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    public E get(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public E set(int index, E data) {
        ListItem<E> currentItem = getItem(index);
        E oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    public void add(E data) {
        add(size, data);
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public E removeFirst() {
        checkListEmpty();

        E data = head.getData();
        head = head.getNext();
        size--;

        return data;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> itemToRemove = previousItem.getNext();
        E removedData = itemToRemove.getData();
        previousItem.setNext(itemToRemove.getNext());
        size--;

        return removedData;
    }

    public void add(int index, E data) {
        checkIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> newNode = new ListItem<>(data, previousItem.getNext());
        previousItem.setNext(newNode);
        size++;
    }

    public boolean removeData(E data) {
        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            if (Objects.equals(currentItem.getData(), data)) {
                if (previousItem == null) {
                    removeFirst();
                } else {
                    previousItem.setNext(currentItem.getNext());
                    size--;
                }

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copyList = new SinglyLinkedList<>();

        for (ListItem<E> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            copyList.add(currentItem.getData());
        }

        return copyList;
    }

    public void reverse() {
        if (head == null) {
            return;
        }

        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        ListItem<E> currentItem = head;

        while (currentItem != null) {
            stringBuilder.append(currentItem.getData()).append(", ");
            currentItem = currentItem.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SinglyLinkedList<?> other = (SinglyLinkedList<?>) o;
        if (size != other.size) {
            return false;
        }

        ListItem<?> currentItem = head;
        ListItem<?> otherCurrentItem = other.head;

        while (currentItem != null && otherCurrentItem != null) {
            if (!Objects.equals(currentItem.getData(), otherCurrentItem.getData())) {
                return false;
            }

            currentItem = currentItem.getNext();
            otherCurrentItem = otherCurrentItem.getNext();
        }

        return currentItem == null && otherCurrentItem == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (ListItem<E> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            result = 31 * result + Objects.hashCode(currentItem.getData());
        }

        return result;
    }
}

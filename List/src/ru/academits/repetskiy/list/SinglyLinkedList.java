package ru.academits.repetskiy.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int size() {
        return size;
    }

    private void isListEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пустой!");
        }
    }

    public E getFirst() {
        isListEmpty();

        return head.getData();
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private ListItem<E> getElement(int index) {
        ListItem<E> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    public E get(int index) {
        rangeCheck(index);

        return getElement(index).getData();
    }

    public E set(int index, E data) {
        rangeCheck(index);

        ListItem<E> currentItem = head;

        if (index == 0) {
            head.setData(data);
        } else {
            currentItem = getElement(index - 1);
            currentItem.setNext(new ListItem<>(data, currentItem.getNext()));
        }

        size++;

        return currentItem.getData();
    }

    public void add(E data) {
        if (head == null) {
            addFirst(data);
        } else {
            set(size - 1, data);
        }
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public E removeFirst() {
        isListEmpty();

        E data = head.getData();
        head = head.getNext();
        size--;

        return data;
    }

    public E remove(int index) {
        rangeCheck(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> previousItem = getElement(index - 1);
        ListItem<E> itemToRemove = previousItem.getNext();
        E removedData = itemToRemove.getData();
        previousItem.setNext(itemToRemove.getNext());
        size--;

        return removedData;
    }

    public void add(int index, E data) {
        set(index, data);
    }

    public boolean removeData(E data) {
        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem.getNext() != null) {
            if (currentItem.getData().equals(data)) {
                if (head.getData().equals(data)) {
                    removeFirst();
                } else {
                    assert previousItem != null; // Как сделать без assert?
                    previousItem.setNext(currentItem.getNext());
                    size--;
                }

                currentItem.setNext(null);

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copyList = new SinglyLinkedList<>();
        copyList.head = new ListItem<>(this.head.getData(), this.head.getNext());

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
            stringBuilder.append(currentItem.getData());
            currentItem = currentItem.getNext();
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinglyLinkedList<?> that = (SinglyLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
}

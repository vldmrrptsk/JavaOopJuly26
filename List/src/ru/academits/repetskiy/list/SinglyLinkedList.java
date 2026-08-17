package ru.academits.repetskiy.list;

import java.util.ArrayList;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public T getFirst() {
        return head.getData();
    }

    public T get(int index) {
        if (index > size) {
            throw new IllegalArgumentException("Длина списка меньше указанного индекса! " + "{Длина списка: " + size + "}");
        }

        int currentIndex = 0;
        ListItem<T> current = head;

        while (currentIndex != index) {
            current = current.getNext();
            currentIndex++;
        }

        return current.getData();
    }

    public T replace(int index, T data) {
        int currentIndex = 0;
        T oldData = head.getData();
        ListItem<T> current = head;

        while (currentIndex != index) {
            current = current.getNext();
            oldData = current.getData();
            currentIndex++;
        }

        current.setData(data);

        return oldData;
    }

    private ListItem<T> getLastItem() {
        ListItem<T> current = head;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        return current;
    }

    public void add(T data) {
        ListItem<T> newItem = new ListItem<T>(data);

        if (head == null) {
            head = newItem;
        } else {
            ListItem<T> current = getLastItem();
            current.setNext(newItem);
        }

        size++;
    }

    public void addFirstItem(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public T removeFirstItem() {
        T data = head.getData();
        head = head.getNext();
        size--;

        return data;
    }

    public T remove(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Длина списка меньше указанного индекса! " + "{Длина списка: " + size + "}");
        }

        T currentData = head.getData();

        if (index == 0) {
            currentData = removeFirstItem();
        } else {
            ListItem<T> current = head;
            ListItem<T> previous = null;
            int currentIndex = 0;

            while (currentIndex < index) {
                previous = current;
                current = current.getNext();
                currentData = current.getData();
                currentIndex++;
            }

            previous.setNext(current.getNext());
            current = null;

            size--;
        }

        return currentData;
    }

    public void insert(int index, T data) {
        if (index > size) {
            throw new IllegalArgumentException("Длина списка меньше указанного индекса! " + "{Длина списка: " + size + "}");
        }

        if (index == 0) {
            addFirstItem(data);
        } else if (index == size) {
            add(data);
        } else {
            ListItem<T> current = head;
            ListItem<T> previous = null;
            int currentIndex = 0;

            while (currentIndex < index) {
                previous = current;
                current = current.getNext();
                currentIndex++;
            }

            ListItem<T> newItem = new ListItem<T>(data, current);
            previous.setNext(newItem);

            size++;
        }
    }

    public boolean removeData(T data) {
        ListItem<T> current = head;
        ListItem<T> previous = null;
        int currentIndex = 0;

        while (currentIndex < size) {
            if (current.getData() == data) {
                if (currentIndex == 0) {
                    removeFirstItem();
                } else {
                    previous.setNext(current.getNext());
                    size--;
                }

                current = null;

                return true;
            }

            previous = current;
            current = current.getNext();
            currentIndex++;
        }

        return false;
    }

    public SinglyLinkedList<T> copy() {
        ListItem<T> current = head;
        SinglyLinkedList<T> copyList = new SinglyLinkedList<>();
        int currentIndex = 0;

        while (currentIndex < size) {
            copyList.add(current.getData());
            current = current.getNext();
            currentIndex++;
        }

        return copyList;
    }

    @Override
    public String toString() {
        ListItem<T> current = head;
        ArrayList<T> arrayList = new ArrayList<>();

        while (current != null) {
            arrayList.add(current.getData());
            current = current.getNext();
        }

        return "Список: " + arrayList.toString();
    }
}

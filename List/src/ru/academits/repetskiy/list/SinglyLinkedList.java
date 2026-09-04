package ru.academits.repetskiy.list;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int size() {
        return size;
    }

    private void checkAnEmptyList() {
        if (size == 0) {
            throw new NullPointerException("Список пустой!");
        }
    }

    public E getFirst() {
        checkAnEmptyList();

        return head.getData();
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private ListItem<E> goToIndex(int index) {
        rangeCheck(index);

        ListItem<E> currentItem = head;
        int i = 0;

        while (i != index) {
            currentItem = currentItem.getNext();
            i++;
        }

        return currentItem;
    }

    public E get(int index) {
        rangeCheck(index);

        return goToIndex(index).getData();
    }

    public E set(int index, E data) {
        rangeCheck(index);

        ListItem<E> currentItem = head;

        currentItem = goToIndex(index);
        currentItem.setData(data);

        return currentItem.getData();
    }


    public void add(E data) {
        ListItem<E> newItem = new ListItem<E>(data);

        if (head == null) {
            head = newItem;
        } else {
            ListItem<E> currentItem = null;

            currentItem = goToIndex(size - 1);

            currentItem.setNext(newItem);
        }

        size++;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public E removeFirst() {
        checkAnEmptyList();

        E data = head.getData();
        head = head.getNext();
        size--;

        return data;
    }


    public E remove(int index) {
        rangeCheck(index);

        E currentData = head.getData();

        if (index == 0) {
            currentData = removeFirst();

            return currentData;
        } else {
            ListItem<E> previousItem = goToIndex(index - 1);

            ListItem<E> itemToRemove = previousItem.getNext();
            E removedData = itemToRemove.getData();

            previousItem.setNext(itemToRemove.getNext());

            itemToRemove.setNext(null);

            size--;

            return removedData;
        }
    }

    public void add(int index, E data) {
        rangeCheck(index);

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<E> previousItem = goToIndex(index - 1);

            ListItem<E> newItem = new ListItem<E>(data, previousItem.getNext());

            previousItem.setNext(newItem);

            size++;
        }
    }

    public boolean removeData(E data) {
        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;
        int i = 0;

        while (i < size) {
            if ((E) currentItem.getData() == (E) data) {
                if (i == 0) {
                    removeFirst();
                } else {
                    previousItem.setNext(currentItem.getNext());
                    size--;
                }

                currentItem = null;

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
            i++;
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

    public boolean reverse() {
        checkAnEmptyList();

        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;

        }

        head = previousItem;

        return true;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<E> current = head;
        while (current != null) {
            stringBuilder.append(current.getData());
            current = current.getNext();

            if (current != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

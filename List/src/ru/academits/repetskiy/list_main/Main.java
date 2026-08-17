package ru.academits.repetskiy.list_main;

import ru.academits.repetskiy.list.ListItem;
import ru.academits.repetskiy.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.add(5);
        list.add(10);
        list.add(20);
        list.addFirstItem(100);

        System.out.println(list);
        System.out.println("Размер списка: " + list.size());
        System.out.println("Первый элемент списка: " + list.getFirst());
        System.out.println("Элемент списка: " + list.get(2));
        System.out.println(list.replace(2, -100));
        System.out.println(list);
        System.out.println(list.removeFirstItem());
        System.out.println(list);

        System.out.println(list.remove(0));
        System.out.println(list);

        list.insert(2, 40);
        System.out.println(list);

        System.out.println(list.removeData(10));
        System.out.println(list);

        SinglyLinkedList<Integer> list1 = list.copy();
        System.out.println(list1);
    }
}
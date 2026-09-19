package ru.academits.repetskiy.list_main;

import ru.academits.repetskiy.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.add(5);
        list.add(10);
        list.add(20);
        list.addFirst(100);
        list.set(2, 0);

        System.out.println(list);
        System.out.println("Размер списка: " + list.size());
        System.out.println("Первый элемент списка: " + list.getFirst());
        System.out.println("Элемент списка: " + list.get(2));
        System.out.println(list.set(2, -100));
        System.out.println(list);
        System.out.println(list.removeFirst());
        System.out.println("Удалил первый элемент: " + list);

        System.out.println(list.remove(3));
        System.out.println("Удалил элемент по индексу: " + list);

        list.add(2, 40);
        System.out.println("Добавил элемент: " + list);

        System.out.println(list.removeData(-100));
        System.out.println("Удалил элемент: " + list);

        list.reverse();
        System.out.println(list);

        SinglyLinkedList<Integer> copyList = list.copy();
        System.out.println("Копия: " + copyList);

        System.out.println(list.equals(copyList));
        list.addFirst(1000000);
        list.set(1, 90);
        System.out.println(list);
        System.out.println(copyList);
    }
}
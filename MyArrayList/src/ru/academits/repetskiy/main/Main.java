package ru.academits.repetskiy.main;

import ru.academits.repetskiy.array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>(2);

        myList.add(10);
        myList.add(20);
        myList.add(50);
        myList.add(-50);
        myList.add(-1000);
        myList.add(100);

        System.out.println("Список: " + myList);
        System.out.println("Длина списка: " + myList.size());

        myList.add(myList.size(), 0);
        myList.add(myList.size(), 99);
        myList.add(myList.size(), 102);
        myList.add(myList.size(), 703);

        System.out.println("Список: " + myList);
        System.out.println("Длина списка: " + myList.size());

        System.out.println("Содержит значение? " + myList.contains(76));
        myList.clear();
    }
}

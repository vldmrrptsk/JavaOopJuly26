package ru.academits.repetskiy.main;

import ru.academits.repetskiy.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>(5);

        myList.add(10);
        myList.add(20);
        myList.add(50);
        myList.add(-50);
        myList.add(-1000);
        myList.add(100);

        System.out.println("Список: " + myList);
        System.out.println("Длина списка: " + myList.size());

        myList.add(3, 20);
        System.out.println("Список: " + myList);
        System.out.println("Длина списка: " + myList.size());

        ArrayList<Integer> myList2 = new ArrayList<>(5);

        myList2.add(67);
        myList2.add(93);
        myList2.add(121);

        System.out.println("Список: " + myList2);
        System.out.println("Длина списка: " + myList2.size());

        myList.addAll(3, myList2);

        System.out.println("Список: " + myList);
        System.out.println("Длина списка: " + myList.size());
        System.out.println("Capacity: " + myList.len());

        System.out.println(myList.contains(34));

        myList.removeAll(Arrays.asList(50, 100, -1000));

        System.out.println("Список: " + myList);
    }
}

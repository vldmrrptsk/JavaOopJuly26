package ru.academits.repetskiy.main;

import ru.academits.repetskiy.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> emptyListCheck = new ArrayList<>();
        System.out.println(emptyListCheck);

        ArrayList<Integer> listCheck = new ArrayList<>(5);

        listCheck.add(10);
        listCheck.add(20);
        listCheck.add(50);
        listCheck.add(-50);
        listCheck.add(-1000);
        listCheck.add(100);

        System.out.println("Список: " + listCheck);
        System.out.println("Длина списка: " + listCheck.size());

        listCheck.add(3, 20);
        System.out.println("Список: " + listCheck);
        System.out.println("Длина списка: " + listCheck.size());

        ArrayList<Integer> listCheck2 = new ArrayList<>(5);

        listCheck2.add(67);
        listCheck2.add(93);
        listCheck2.add(121);

        System.out.println("Список: " + listCheck2);
        System.out.println("Длина списка: " + listCheck2.size());

        listCheck2.addAll(3, listCheck2);

        System.out.println("Список: " + listCheck2);
        System.out.println("Длина списка: " + listCheck.size());

        System.out.println(listCheck.contains(34));

        listCheck.removeAll(Arrays.asList(50, 100, -1000));

        System.out.println("Список: " + listCheck);
    }
}

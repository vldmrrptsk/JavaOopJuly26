package ru.academits.repetskiy.main;

import ru.academits.repetskiy.hash_table.HashTable;

public class Main {
    public static void main(String[] args){
        HashTable<Integer> tableCheck = new HashTable<>();
        tableCheck.add(10);
        tableCheck.add(11);
        tableCheck.add(11);
        tableCheck.add(12);
        tableCheck.add(30);
        tableCheck.add(35);

        System.out.println("Первоначальная таблица: " + tableCheck);

        System.out.println("Содержит ли таблица элемент? " + tableCheck.contains(100));

        tableCheck.remove(11);
        System.out.println("Измененная таблица: " + tableCheck);

        tableCheck.clear();
        System.out.println("Пустая таблица? "+ tableCheck.isEmpty());
    }
}


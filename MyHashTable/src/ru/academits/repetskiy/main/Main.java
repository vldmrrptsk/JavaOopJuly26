package ru.academits.repetskiy.main;

import ru.academits.repetskiy.hash_table.MyHashTable;

public class Main {
    public static void main(String[] args){
        MyHashTable<Integer> myTable = new MyHashTable<>();
        myTable.add(10);
        myTable.add(11);
        myTable.add(11);
        myTable.add(12);
        myTable.add(30);

        System.out.println("Первоначальная таблица: " + myTable);

        System.out.println("Содержит ли таблица элемент? " + myTable.contains(100));

        myTable.remove(11);
        System.out.println("Измененная таблица: " + myTable);

        myTable.clear();
        System.out.println("Пустая таблица? "+ myTable.isEmpty());
    }
}

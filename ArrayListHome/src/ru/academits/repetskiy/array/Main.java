package ru.academits.repetskiy.array;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void removeEvenNumbers(ArrayList<Integer> array) {
        ArrayList<Integer> evenElementsArray = new ArrayList<>();

        for (int element : array) {
            if (element % 2 == 0) {
                evenElementsArray.add(element);
            }
        }

        array.removeAll(evenElementsArray);
    }

    public static ArrayList<Integer> unique(ArrayList<Integer> array) {
        ArrayList<Integer> uniqueElementsArray = new ArrayList<>();

        for (int element : array) {
            if (!uniqueElementsArray.contains(element)) {
                uniqueElementsArray.add(element);
            }
        }

        return uniqueElementsArray;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> array = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/test.txt"))) {

            while (scanner.hasNextLine()) {
                int number = Integer.parseInt(scanner.nextLine());
                array.add(number);
            }
        }

        System.out.println("Исходный массив: " + array);

        removeEvenNumbers(array);
        System.out.println("После удаления четных: " + array);

        ArrayList<Integer> uniqueArray = unique(array);
        System.out.println("Уникальные элементы: " + uniqueArray);
    }
}


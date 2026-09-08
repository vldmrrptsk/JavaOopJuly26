package ru.academits.repetskiy.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void removeEvenNumbers(List<Integer> list) {
        int listSize = list.size();
        int k = 0;

        for (int j = 0; j < listSize; j++) {
            if (list.get(j) % 2 != 0) {
                list.set(k, list.get(j));
                k++;
            }
        }

        list.subList(k, listSize).clear();
    }

    public static <T> List<T> getUniqueNumbers(List<T> list) {
        List<T> uniqueElementsArray = new ArrayList<>(list.size());

        for (T element : list) {
            if (!uniqueElementsArray.contains(element)) {
                uniqueElementsArray.add(element);
            }
        }

        return uniqueElementsArray;
    }

    public static List<String> getStringLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        return lines;
    }

    public static void main(String[] args) {
        List<String> lines = getStringLinesFromFile("ArrayListHome/src/test.txt");
        System.out.println("Строки из файла: " + lines);

        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 12, 20, 15, 25, 35, 98, 101));

        System.out.println("Исходный список чисел: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("После удаления четных: " + numbers);

        List<Integer> uniqueNumbers = getUniqueNumbers(numbers);
        System.out.println("Уникальные элементы: " + uniqueNumbers);
    }
}

package ru.academits.repetskiy.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void removeEvenNumbers(List<Integer> list) {
        int listSize = list.size();
        int j = 0;

        for (int i = 0; i < listSize; i++) {
            if (list.get(i) % 2 != 0) {
                list.set(j, list.get(i));
                j++;
            }
        }

        while (list.size() > j) {
            list.removeLast();
        }
    }

    public static <T> List<T> getDistinctElements(List<T> list) {
        List<T> uniqueElementsArray = new ArrayList<>(list.size());

        for (T element : list) {
            if (!uniqueElementsArray.contains(element)) {
                uniqueElementsArray.add(element);
            }
        }

        return uniqueElementsArray;
    }

    public static List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void main(String[] args) {
        String filePath = "ArrayListHome/src/test.txt";
        List<String> lines;

        try {
            lines = readLinesFromFile(filePath);
            System.out.println("Строки из файла: " + lines);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 12, 20, 15, 25, 35, 98, 101));

        System.out.println("Исходный список чисел: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("После удаления четных: " + numbers);

        List<Integer> uniqueNumbers = getDistinctElements(numbers);
        System.out.println("Уникальные элементы: " + uniqueNumbers);
    }
}

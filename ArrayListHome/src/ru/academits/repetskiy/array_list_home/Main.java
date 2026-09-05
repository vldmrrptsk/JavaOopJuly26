package ru.academits.repetskiy.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void removeEvenNumbers(ArrayList<Integer> arrayList) {
        Iterator<Integer> iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            int element = iterator.next();
            if (element % 2 == 0) {
                iterator.remove(); // Безопасное удаление через итератор
            }
        }
    }

    public static <T> ArrayList<T> getUnique(ArrayList<T> arrayList) {
        ArrayList<T> uniqueElementsArray = new ArrayList<>(arrayList.size());

        for (T element : arrayList) {
            if (!uniqueElementsArray.contains(element)) {
                uniqueElementsArray.add(element);
            }
        }

        return uniqueElementsArray;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "ArrayListHome/src/test.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        numbers.add(25);
        numbers.add(30);
        numbers.add(15);
        numbers.add(35);
        numbers.add(40);
        numbers.add(25);
        numbers.add(45);

        System.out.println("Исходный список чисел: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("После удаления четных: " + numbers);

        ArrayList<Integer> uniqueArray = getUnique(numbers);
        System.out.println("Уникальные элементы: " + uniqueArray);
    }
}

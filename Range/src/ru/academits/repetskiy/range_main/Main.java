package ru.academits.repetskiy.range_main;

import ru.academits.repetskiy.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(-2.1, 10);

        System.out.println("Длина первого диапазона: " + range1.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для проверки попадания в первый диапазон: ");
        double number = scanner.nextDouble();

        System.out.println("Число " + number + " в первом диапазоне? " + range1.isInside(number));
        System.out.println();

        Range range2 = new Range(5, 20);
        Range range3 = new Range(100, 101);
        Range range4 = new Range(-10, -2.1);
        Range range5 = new Range(-10, -2.1);
        Range range6 = new Range(-2.1, 0);
        Range range7 = new Range(1, 5);
        Range range8 = new Range(1, 3);
        Range range9 = new Range(2, 3);

        Range[] ranges = {range1, range2, range3, range4, range5, range6, range7, range8, range9};

        for (int i = 0; i < ranges.length - 1; i++) {
            Range intersection = ranges[i].getIntersection(ranges[i + 1]);
            Range[] union = ranges[i].getUnion(ranges[i + 1]);
            Range[] difference = ranges[i].getDifference(ranges[i + 1]);

            System.out.printf("Результат пересечения %d и %d интервалов: %s%n", i + 1, i + 2, intersection);
            System.out.printf("Результат объединения %d и %d интервалов: %s%n", i + 1, i + 2, Arrays.toString(union));
            System.out.printf("Результат разности %d и %d интервалов: %s%n", i + 1, i + 2, Arrays.toString(difference));
            System.out.println();
        }
    }
}

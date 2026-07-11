package ru.academits.repetskiy.range_main;

import ru.academits.repetskiy.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(-2.1, 10);

        System.out.println("Длина первого диапазона: " + range1.getInterval());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для проверки попадания в первый диапазон: ");
        double number = scanner.nextDouble();

        System.out.println("Число " + number + " в первом диапазоне? " + range1.isInside(number));
        System.out.println();

        Range range2 = new Range(5, 20);
        Range range3 = new Range(100, 101);
        Range range4 = new Range(-10, -2.1);

        Range[] ranges = new Range[]{range1, range2, range3, range4};

        for (int i = 0; i < ranges.length - 1; i++) {
            String intersection = Arrays.toString(ranges[i].getIntersection(ranges[i + 1]));
            String union = Arrays.toString(ranges[i].getUnion(ranges[i + 1]));
            String difference = Arrays.toString(ranges[i].getAsymmetricDifference(ranges[i + 1]));

            System.out.printf("Интервал-пересечения %d и %d интервалов: %s%n", i + 1, i + 2, intersection);
            System.out.printf("Интервал-объединения %d и %d интервалов: %s%n", i + 1, i + 2, union);
            System.out.printf("Интервал-разности %d и %d интервалов: %s%n", i + 1, i + 2, difference);
            System.out.println();
        }
    }
}

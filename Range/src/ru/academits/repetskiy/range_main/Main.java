package ru.academits.repetskiy.range_main;

import ru.academits.repetskiy.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-2.1, 10);

        System.out.println("Длина диапазона: " + range.getRange());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число для проверки попадания в диапазон: ");
        double number = scanner.nextDouble();

        System.out.println("Число " + number + " в диапазоне? " + range.isInside(number));
    }
}

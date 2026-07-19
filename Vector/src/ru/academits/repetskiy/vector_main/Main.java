package ru.academits.repetskiy.vector_main;

import ru.academits.repetskiy.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(new double[]{1, 2, 3});
        Vector v2 = new Vector(new double[]{1, 2, 3, 4});
        Vector v3 = new Vector(v2);
        Vector v4 = new Vector(10);
        Vector v5 = new Vector(5, new double[]{7, 100, 4, 200});

        for (int i = 0; i < 5; i++) {
            Vector[] reference = new Vector[]{v1, v2, v3, v4, v5};
            System.out.printf("Вектор %d, имеет размерность = %d\n", i + 1, reference[i].getVectorLength());
        }

        System.out.println();
        v1.addVector(v4);
        System.out.println("Сумма векторов 1 и 4: " + v1);

        System.out.println();
        v1.addVector(v5);
        System.out.println("Сумма векторов 1 и 5: " + v1);

        System.out.println();
        v4.subtractVector(v3);
        System.out.println("Разность векторов 4 и 3: " + v4);

        System.out.println();
        v4.turnOverVector();
        System.out.println("Разворот векторов 4: " + v4);

        System.out.println();
        System.out.println("Вектор 2: " + v2);
        v2.setElementIndex(3, -1000);
        System.out.println("Измененный вектор 2: " + v2);
        // v2.setElementIndex(10, -1000); // Проверка на исключение
        // System.out.println("Измененный вектор 2: " +  v2);

        System.out.println();
        System.out.println("Вектор 3 и 4 равны? " + v3.equals(v4));

        System.out.println();
        System.out.println("Вектор 3: " + v3);
        System.out.println("Вектор 4: " + v4);
        System.out.println("Скалярное произведение 3 и 4 векторов: " + Vector.scalarMultiplication(v3, v4));
    }
}

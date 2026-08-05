package ru.academits.repetskiy.vector_main;

import ru.academits.repetskiy.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(10);
        Vector vector5 = new Vector(5, new double[]{7, 100, 4, 200});

        Vector[] vectors = {vector1, vector2, vector3, vector4, vector5};
        for (int i = 0; i < vectors.length; i++) {
            System.out.printf("Вектор %d, имеет размерность = %d%n", i + 1, vectors[i].getSize());
        }

        System.out.println();
        vector1.add(vector4);
        System.out.println("Сумма векторов 1 и 4: " + vector1);

        System.out.println();
        vector1.add(vector5);
        System.out.println("Сумма векторов 1 и 5: " + vector1);

        System.out.println();
        vector4.subtract(vector3);
        System.out.println("Разность векторов 4 и 3: " + vector4);

        System.out.println();
        vector4.turnOver();
        System.out.println("Разворот вектора 4: " + vector4);

        System.out.println();
        System.out.println("Вектор 2: " + vector2);
        vector2.setCoordinate(3, -1000);
        System.out.println("Измененный вектор 2: " + vector2);

        System.out.println();
        System.out.println("Вектор 3 и 4 равны? " + vector3.equals(vector4));

        System.out.println();
        System.out.println("Вектор 3: " + vector3);
        System.out.println("Вектор 4: " + vector4);
        System.out.println("Скалярное произведение 3 и 4 векторов: " + Vector.getDotProduct(vector3, vector4));

        System.out.println();
        System.out.println("Сумма векторов 3 и 4: " + Vector.getSum(vector3, vector4));

        System.out.println("Хэш-Код вектора 3: " + vector3.hashCode());
    }
}

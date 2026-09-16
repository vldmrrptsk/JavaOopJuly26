package ru.academits.repetskiy.matrix_main;

import ru.academits.repetskiy.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 5);

        System.out.println("Элементы матрицы 1: " + matrix1);
        System.out.println("Элементы 3й строки матрицы 1: " + matrix1.getRow(2));

        double[][] array2D = {{1, 2, 3, 9}, {4, 5, 6, 10}, {1, 1, 1, 1}};

        Matrix matrix2 = new Matrix(array2D);

        System.out.println("Элементы матрицы 2: " + matrix2);
        matrix2.add(matrix2);
        matrix2.multiplyByScalar(2);
        System.out.println("Элементы матрицы 2 после операций сложения и умножение на скаляр: " + matrix2);
        matrix2 = matrix2.transpose();
        System.out.println("Элементы матрицы 2 после транспонирования: " + matrix2);

        double[][] array2D2 = {{1, 2, 3}, {1, 10, 3}, {1, 2, 4}};

        Matrix matrix3 = new Matrix(array2D2);
        System.out.println("Определитель матрицы 3: " + matrix3.determinant());
        System.out.println("Произведение матрицы 2 и 2: " + Matrix.getProduct(matrix2, matrix2));
    }
}

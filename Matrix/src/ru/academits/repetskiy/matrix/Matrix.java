package ru.academits.repetskiy.matrix;

import ru.academits.repetskiy.vector.Vector;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше 0: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0");
        }

        int columnsCount = matrix[0].length;

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше 0");
        }

        rows = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != columnsCount) {
                throw new IllegalArgumentException("Строки матрицы должны быть одинаковой длины: " + matrix[i].length + " != " + columnsCount);
            }

            rows[i] = new Vector(matrix[i]);
        }
    }

    public Matrix(Matrix matrix) {
        Objects.requireNonNull(matrix, "Матрица не должна быть null");

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0");
        }

        int columnsCount = 0;

        for (Vector vector : vectors) {
            columnsCount = Math.max(vector.getSize(), columnsCount);
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше 0");
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(columnsCount, vectors[i].toArray());
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + (rows.length - 1) + ": " + index);
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        Objects.requireNonNull(vector, "Вектор не должен быть null");

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора должен совпадать с количеством столбцов матрицы: " + vector.getSize() + " != " + getColumnsCount());
        }

        checkIndex(index);

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + (getColumnsCount() - 1) + ": " + index);
        }

        double[] columnData = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            columnData[i] = rows[i].getCoordinate(index);
        }

        return new Vector(columnData);
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        Objects.requireNonNull(matrix, "Матрица не должна быть null");

        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размерности матриц должны совпадать: " + rows.length + "x" + getColumnsCount() + " != " + matrix.rows.length + "x" + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        Objects.requireNonNull(matrix, "Матрица не должна быть null");

        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размерности матриц должны совпадать: " + rows.length + "x" + getColumnsCount() + " != " + matrix.rows.length + "x" + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static void swapRows(Vector[] rows, int index1, int index2) {
        Vector temp = rows[index1];
        rows[index1] = rows[index2];
        rows[index2] = temp;
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной: " + rows.length + "x" + getColumnsCount());
        }

        final double EPSILON = 1e-10;
        int matrixSize = rows.length;
        Vector[] matrixRowsCopy = new Vector[matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            matrixRowsCopy[i] = new Vector(rows[i]);
        }

        double determinant = 1.0;

        for (int i = 0; i < matrixSize; i++) {
            int maxRowIndex = i;
            double maxValue = Math.abs(matrixRowsCopy[i].getCoordinate(i));

            for (int j = i + 1; j < matrixSize; j++) {
                double currentValue = Math.abs(matrixRowsCopy[j].getCoordinate(i));

                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    maxRowIndex = j;
                }
            }

            if (maxValue < EPSILON) {
                return 0.0;
            }

            if (maxRowIndex != i) {
                swapRows(matrixRowsCopy, i, maxRowIndex);
                determinant = -determinant;
            }

            for (int j = i + 1; j < matrixSize; j++) {
                double factor = matrixRowsCopy[j].getCoordinate(i) / matrixRowsCopy[i].getCoordinate(i);

                for (int k = i; k < matrixSize; k++) {
                    double newValue = matrixRowsCopy[j].getCoordinate(k) - factor * matrixRowsCopy[i].getCoordinate(k);
                    matrixRowsCopy[j].setCoordinate(k, newValue);
                }
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            determinant *= matrixRowsCopy[i].getCoordinate(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        Objects.requireNonNull(vector, "Вектор не должен быть null");

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно совпадать с размером вектора: " + getColumnsCount() + " != " + vector.getSize());
        }

        double[] vectorCoordinates = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            vectorCoordinates[i] = Vector.getDotProduct(rows[i], vector);
        }

        return new Vector(vectorCoordinates);
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Objects.requireNonNull(matrix1, "Первая матрица не должна быть null");
        Objects.requireNonNull(matrix2, "Вторая матрица не должна быть null");

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Objects.requireNonNull(matrix1, "Первая матрица не должна быть null");
        Objects.requireNonNull(matrix2, "Вторая матрица не должна быть null");

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        Objects.requireNonNull(matrix1, "Первая матрица не должна быть null");
        Objects.requireNonNull(matrix2, "Вторая матрица не должна быть null");

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно совпадать с количеством строк второй матрицы: " + matrix1.getColumnsCount() + " != " + matrix2.rows.length);
        }

        int resultRowsCount = matrix1.rows.length;
        int resultColumnsCount = matrix2.getColumnsCount();

        double[][] resultProductMatrix = new double[resultRowsCount][resultColumnsCount];

        for (int i = 0; i < resultRowsCount; i++) {
            for (int j = 0; j < resultColumnsCount; j++) {
                double element = 0.0;

                for (int k = 0; k < matrix1.getColumnsCount(); k++) {
                    element += matrix1.rows[i].getCoordinate(k) * matrix2.rows[k].getCoordinate(j);
                }

                resultProductMatrix[i][j] = element;
            }
        }

        return new Matrix(resultProductMatrix);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        int hash = 1;
        hash = prime * hash + Arrays.hashCode(rows);

        return hash;
    }
}

package ru.academits.repetskiy.matrix;

import ru.academits.repetskiy.vector.Vector;
import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsLength, int columnsLength) {
        if (rowsLength <= 0 || columnsLength <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть больше 0: " + rowsLength + " " + columnsLength);
        }

        rows = new Vector[rowsLength];
        for (int i = 0; i < rowsLength; i++) {
            rows[i] = new Vector(columnsLength);
        }
    }

    public Matrix(double[][] matrix) {
        rows = new Vector[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rows[i] = new Vector(matrix[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int maxVectorsLength = vectors[0].getSize();

        for (Vector vector : vectors) {
            maxVectorsLength = Math.max(vector.getSize(), maxVectorsLength);
        }

        rows = new Vector[maxVectorsLength];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorsLength, vectors[i].toArray());
        }
    }

    public int getRowsLength() {
        return rows.length;
    }

    public int getColumnsLength() {
        return rows[0].getSize();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0: " + index);
        }

        if (index > rows.length) {
            throw new IllegalArgumentException("Индекс выходит за границы размера матрицы: " + index);
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkIndex(index);

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        checkIndex(index);

        double[] columnData = new double[rows[0].getSize()];

        for (int i = 0; i < rows.length; i++) {
            columnData[i] = rows[i].getCoordinate(index);
        }

        return new Vector(columnData);
    }

    public Matrix transpose() {
        Matrix resultMatrix = new Matrix(rows[0].getSize(), rows.length);

        for (int i = 0; i < rows[0].getSize(); i++) {
            resultMatrix.setRow(i, getColumn(i));
        }

        return resultMatrix;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    private static void checkArgument(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Аргумент не должен быть null!");
        }
    }

    public void add(Matrix matrix) {
        checkArgument(matrix);

        for (int i = 0; i < rows.length; i++) {
            this.rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkArgument(matrix);

        for (int i = 0; i < rows.length; i++) {
            this.rows[i].subtract(matrix.rows[i]);
        }
    }

    private static void swapRows(Vector[] rows, int index1, int index2) {
        Vector temporaryVector = rows[index1];
        rows[index1] = rows[index2];
        rows[index2] = temporaryVector;
    }

    public double determinant() {
        final double EPSILON = 1e-10;
        int copyRowsLengthMatrix = rows.length;
        Vector[] copyMatrixElements = new Vector[copyRowsLengthMatrix];

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            copyMatrixElements[i] = new Vector(rows[i]);
        }

        double det = 1.0;

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            int maxRow = i;
            double maxValue = Math.abs(copyMatrixElements[i].getCoordinate(i));

            for (int j = i + 1; j < copyRowsLengthMatrix; j++) {
                double currentValue = Math.abs(copyMatrixElements[j].getCoordinate(i));
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    maxRow = j;
                }
            }

            if (maxValue < EPSILON) {
                return 0.0;
            }

            if (maxRow != i) {
                swapRows(copyMatrixElements, i, maxRow);
                det = -det;
            }

            for (int k = i + 1; k < copyRowsLengthMatrix; k++) {
                double factor = copyMatrixElements[k].getCoordinate(i) / copyMatrixElements[i].getCoordinate(i);

                for (int j = i; j < copyRowsLengthMatrix; j++) {
                    double newValue = copyMatrixElements[k].getCoordinate(j)
                            - factor * copyMatrixElements[i].getCoordinate(j);

                    copyMatrixElements[k].setCoordinate(j, newValue);
                }
            }
        }

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            det *= copyMatrixElements[i].getCoordinate(i);
        }

        return det;
    }

    public Vector dot(Vector vector) {
        if (rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора не совпадает с размером столбцов в матрице! " + vector.getSize());
        }

        double[] vectorCoordinates = new double[rows.length];
        double coordinate = 0.0;

        for (int i = 0; i < rows.length; i++) {
            coordinate = Vector.getDotProduct(rows[i], vector);
            vectorCoordinates[i] = coordinate;
        }

        return new Vector(vectorCoordinates);
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkArgument(matrix1);
        checkArgument(matrix2);

        Matrix resultMatrix = new Matrix(matrix1.rows);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkArgument(matrix1);
        checkArgument(matrix2);

        Matrix resultMatrix = new Matrix(matrix1.rows);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("Размерности матриц должны совпадать!");
        }

        int rowsLength = matrix1.rows.length;
        int columnsLength = matrix2.rows.length;

        double[][] resultProductMatrix = new double[rowsLength][columnsLength];

        for (int i = 0; i < rowsLength; i++) {
            for (int j = 0; j < columnsLength; j++) {
                double element = 0.0;
                for (int k = 0; k < columnsLength; k++) {
                    element += matrix1.rows[i].getCoordinate(k) * matrix2.getColumn(j).getCoordinate(k);
                }
                resultProductMatrix[i][j] = element;
            }
        }

        return new Matrix(resultProductMatrix);
    }

    @Override
    public String toString() {
        int rowsLength = rows.length;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        for (int i = 0; i < rowsLength; i++) {
            stringBuilder.append(rows[i].toString()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;
        return Objects.deepEquals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        int hash = 1;
        hash = prime * hash + Arrays.hashCode(rows);

        return hash;
    }
}

package ru.academits.repetskiy.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + size);
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + array.length);
        }

        coordinates = Arrays.copyOf(array, array.length);
    }

    public Vector(int arrayLength, double[] array) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + arrayLength);
        }

        coordinates = Arrays.copyOf(array, arrayLength);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (int i = 1; i < coordinates.length - 1; i++) {
            if (i > 1) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(coordinates[i]);
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] += vector.coordinates[i];

        }
    }

    public void subtract(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] -= vector.coordinates[i];

        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= scalar;
        }
    }

    public void turnOver() {
        multiplyByScalar(-1);
    }

    public double getCoordinate(int index) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне допустимого диапазона [0, " + (coordinates.length - 1) + "]. Размер вектора: " + coordinates.length);
        }

        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне допустимого диапазона [0, " + (coordinates.length - 1) + "]. Размер вектора: " + coordinates.length);
        }

        coordinates[index] = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(coordinates);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.coordinates.length, vector2.coordinates.length);
        double[] resultCoordinates = Arrays.copyOf(vector1.coordinates, maxSize);

        for (int i = 0; i < vector2.coordinates.length; i++) {
            resultCoordinates[i] += vector2.coordinates[i];
        }

        return new Vector(resultCoordinates);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.coordinates.length, vector2.coordinates.length);
        double[] resultCoordinates = Arrays.copyOf(vector1.coordinates, maxSize);

        for (int i = 0; i < vector2.coordinates.length; i++) {
            resultCoordinates[i] -= vector2.coordinates[i];
        }

        return new Vector(resultCoordinates);
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        double dotProduct = 0.0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return dotProduct;
    }

    public double getLength() {
        double sumCoordinates = 0.0;
        for (double coordinate : coordinates) {
            sumCoordinates += coordinate * coordinate;
        }
        return Math.sqrt(sumCoordinates);
    }
}

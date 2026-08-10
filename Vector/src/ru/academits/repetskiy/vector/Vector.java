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
        this.coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
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
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < coordinates.length; i++) {
            if (i == 0) {
                string.append("{");
            }

            string.append(coordinates[i]);

            if (i < coordinates.length - 1) {
                string.append(", ");
            }

            if (i == coordinates.length - 1) {
                string.append("}");
            }
        }

        return string.toString();
    }

    public void add(Vector vector) {
        int maxSize = Math.max(coordinates.length, vector.coordinates.length);

        if (coordinates.length < maxSize) {
            coordinates = Arrays.copyOf(coordinates, maxSize);
        }

        for (int i = 0; i < maxSize; i++) {
            if (i < vector.coordinates.length) {
                coordinates[i] += vector.coordinates[i];
            }
        }
    }

    public void subtract(Vector vector) {
        int maxSize = Math.max(coordinates.length, vector.coordinates.length);

        if (coordinates.length < maxSize) {
            coordinates = Arrays.copyOf(coordinates, maxSize);
        }

        for (int i = 0; i < maxSize; i++) {
            if (i < vector.coordinates.length) {
                coordinates[i] -= vector.coordinates[i];
            }
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
        vector1.add(vector2);

        return vector1;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.subtract(vector2);

        return vector1;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return dotProduct;
    }
}

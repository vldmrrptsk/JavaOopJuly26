package ru.academits.repetskiy.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private double[] coordinates;

    public Vector(int vectorLength) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + vectorLength);
        }

        coordinates = new double[vectorLength];
    }

    public Vector(Vector vector) {
        coordinates = new double[vector.getSize()];

        System.arraycopy(vector.coordinates, 0, coordinates, 0, vector.getSize());
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
        String[] stringCoordinates = new String[this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            stringCoordinates[i] = String.valueOf(coordinates[i]);
        }

        return String.format("{%s}", String.join(", ", stringCoordinates));
    }

    public Vector getAdd(Vector vector) {
        double[] coordinatesCopy = Arrays.copyOf(this.coordinates, this.getVectorLength());

        if (this.getVectorLength() != vector.getVectorLength()) {
            int maxVectorLength = Math.max(this.getVectorLength(), vector.getVectorLength());
            coordinatesCopy = Arrays.copyOf(coordinates, maxVectorLength);
            vector.coordinates = Arrays.copyOf(vector.coordinates, maxVectorLength);
        }

        for (int i = 0; i < vector.getVectorLength(); i++) {
            coordinatesCopy[i] += vector.coordinates[i];
        }

        return new Vector(coordinatesCopy.length, coordinatesCopy);
    }

    public Vector getSubtract(Vector vector) {
        double[] coordinatesCopy = Arrays.copyOf(this.coordinates, this.getVectorLength());

        if (this.getVectorLength() != vector.getVectorLength()) {
            int maxVectorLength = Math.max(this.getVectorLength(), vector.getVectorLength());
            coordinatesCopy = Arrays.copyOf(coordinates, maxVectorLength);
            vector.coordinates = Arrays.copyOf(vector.coordinates, maxVectorLength);
        }

        for (int i = 0; i < vector.getVectorLength(); i++) {
            coordinatesCopy[i] -= vector.coordinates[i];
        }

        return new Vector(coordinatesCopy.length, coordinatesCopy);
    }

    public void multiplyVectorByScalar(double scalar) {
        for (int i = 0; i < this.getSize(); i++) {
            coordinates[i] *= scalar;
        }
    }

    public void turnOverVector() {
        multiplyVectorByScalar(-1);
    }

    public int getVectorLength() {
        return coordinates.length;
    }

    public double getCoordinate(int index) {
        if (index > this.getVectorLength() || index < 0) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора! {Размер вектора: " + this.getVectorLength() + "}");
        }

        return coordinates[index];
    }

    public void setCoordinate(int index, double element) {
        if (index > this.getVectorLength() || index < 0) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора! {Размер вектора: " + this.getVectorLength() + "}");
        }

        coordinates[index] = element;
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
        return this.getSize() == vector.getSize() && Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(this.getSize());
        hash = prime * hash + Arrays.hashCode(coordinates);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        vector1.getAdd(vector2);

        return vector1;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.getSubtract(vector2);

        return vector1;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        if (vector1.getVectorLength() != vector2.getVectorLength()) {
            int maxVectorLength = Math.max(vector1.getVectorLength(), vector2.getVectorLength());
            vector1.coordinates = Arrays.copyOf(vector1.coordinates, maxVectorLength);
            vector2.coordinates = Arrays.copyOf(vector2.coordinates, maxVectorLength);
        }

        double scalarSum = 0;
        for (int i = 0; i < vector1.getSize(); i++) {
            scalarSum += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return scalarSum;
    }
}

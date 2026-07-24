package ru.academits.repetskiy.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int vectorLength;
    private double[] coordinates;

    public Vector(int vectorLength) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + vectorLength);
        }
        this.vectorLength = vectorLength;
        coordinates = new double[vectorLength];
    }

    public Vector(Vector vector) {
        vectorLength = vector.getSize();
        coordinates = new double[vector.getSize()];

        System.arraycopy(vector.coordinates, 0, coordinates, 0, vector.getSize());
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + array.length);
        }

        vectorLength = array.length;
        coordinates = new double[array.length];

        System.arraycopy(array, 0, coordinates, 0, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0: " + n);
        }
        vectorLength = n;
        coordinates = new double[Math.max(n, array.length)];

        System.arraycopy(array, 0, coordinates, 0, array.length);
    }

    public int getSize() {
        return this.vectorLength;
    }

    @Override
    public String toString() {

        return String.format("Vector: %s", Arrays.toString(coordinates));
    }

    public void add(Vector vector) {
        int maxVectorLength = Math.max(this.getVectorLength(), vector.getVectorLength());

        this.coordinates = Arrays.copyOf(this.coordinates, maxVectorLength);
        vector.coordinates = Arrays.copyOf(vector.coordinates, maxVectorLength);


        for (int i = 0; i < vector.getVectorLength(); i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        int maxVectorLength = Math.max(this.getVectorLength(), vector.getVectorLength());

        this.coordinates = Arrays.copyOf(this.coordinates, maxVectorLength);
        vector.coordinates = Arrays.copyOf(vector.coordinates, maxVectorLength);


        for (int i = 0; i < vector.getVectorLength(); i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }

    }

    public void multiplyVectorByScalar(double scalar) {
        for (int i = 0; i < this.vectorLength; i++) {
            this.coordinates[i] *= scalar;
        }
    }

    public void turnOverVector() {
        multiplyVectorByScalar(-1);
    }

    public int getVectorLength() {
        return coordinates.length;
    }

    public double getElement(int index) {
        if (index > this.getSize()) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора! {Размер вектора: " + this.getSize() + "}");
        }

        return this.coordinates[index];
    }

    public void setElement(int index, double element) {
        if (index > this.getSize()) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора! {Размер вектора: " + this.getSize() + "}");
        }

        this.coordinates[index] = element;
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
        return vectorLength == vector.vectorLength && Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vectorLength, Arrays.hashCode(coordinates));
    }

    public static Vector addVectors(Vector vector1, Vector vector2) {
        vector1.add(vector2);

        return vector1;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        vector1.subtract(vector2);

        return vector1;
    }

    public static double dotProduct(Vector vector1, Vector vector2) {
        double scalarSum = 0;

        for (int i = 0; i < vector1.getSize(); i++) {
            scalarSum += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return scalarSum;
    }
}

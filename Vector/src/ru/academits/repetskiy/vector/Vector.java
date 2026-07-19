package ru.academits.repetskiy.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int n;
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Длина вектора должна быть больше 0: " + n);
        }
        this.n = n;
        this.vector = new double[n];
    }

    public Vector(Vector other) {
        this.n = other.getSize();
        this.vector = new double[other.getSize()];

        for (int i = 0; i < other.getSize(); i++) {
            this.vector[i] = other.vector[i];
        }
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина вектора должна быть больше 0: " + n);
        }

        this.n = array.length;
        this.vector = new double[this.n];

        for (int i = 0; i < array.length; i++) {
            this.vector[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Длина вектора должна быть больше 0: " + n);
        }
        this.n = n;
        this.vector = new double[Math.max(n, array.length)];

        for (int i = 0; i < array.length; i++) {
            this.vector[i] = array[i];
        }
    }

    public int getSize() {
        return this.n;
    }

    public void setSize(int n) {
        this.n = n;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public double[] getVectorElements() {
        return this.vector;
    }

    @Override
    public String toString() {
        return "Vector: " +
                '{' + Arrays.toString(vector) + '}';
    }

    private void extendVector(Vector other) {
        int newLength = Math.max(this.getSize(), other.getSize());
        double[] extendsVector = new double[newLength];

        for (int i = 0; i < this.getSize(); i++) {
            extendsVector[i] = this.vector[i];
        }
        this.n = newLength;
        this.vector = extendsVector;

    }

    public void addVector(Vector other) {
        if (this.getSize() < other.getSize()) {
            extendVector(other);
        }

        for (int i = 0; i < other.getSize(); i++) {
            this.vector[i] += other.vector[i];
        }
    }

    public void subtractVector(Vector other) {
        if (this.getSize() < other.getSize()) {
            extendVector(other);
        }

        for (int i = 0; i < other.getSize(); i++) {
            this.vector[i] -= other.vector[i];
        }
    }

    public void multiplyVectorScalar(int scalar) {
        for (int i = 0; i < this.n; i++) {
            this.vector[i] *= scalar;
        }
    }

    public void turnOverVector() {
        for (int i = 0; i < this.n; i++) {
            this.vector[i] *= -1;
        }
    }

    public int getVectorLength() {
        return this.vector.length;
    }

    public void setElementIndex(int index, int element) {
        if (index > this.getSize()) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора!");
        }
        this.vector[index] = element;
    }

    public double getElementIndex(int index) {
        if (index > this.getSize()) {
            throw new IllegalArgumentException("Значение индекса выходит за пределы размера вектора!");
        }
        return this.vector[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector1 = (Vector) o;
        return n == vector1.n && Objects.deepEquals(vector, vector1.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, Arrays.hashCode(vector));
    }

    public static Vector sumVectors(Vector vector1, Vector vector2) {
        int n = vector1.getSize();
        double[] newVector = new double[n];
        Vector vector3 = new Vector(n, newVector);

        for (int i = 0; i < n; i++) {
            newVector[i] = vector1.vector[i] + vector2.vector[i];
        }

        return vector3;
    }

    public static Vector substructionVectors(Vector vector1, Vector vector2) {
        int n = vector1.getSize();
        double[] newVector = new double[n];
        Vector vector3 = new Vector(n, newVector);

        for (int i = 0; i < n; i++) {
            newVector[i] = vector1.vector[i] - vector2.vector[i];
        }

        return vector3;
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double scalarSum = 0;

        for (int i = 0; i < vector1.getSize(); i++) {
            scalarSum += vector1.vector[i] * vector2.vector[i];
        }

        return scalarSum;
    }
}

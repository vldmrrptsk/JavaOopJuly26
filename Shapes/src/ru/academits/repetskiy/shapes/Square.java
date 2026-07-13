package ru.academits.repetskiy.shapes;

import java.util.Objects;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public void setLength(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }

    public double getArea() {
        return Math.pow(width, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * width;
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(width, square.width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width);
    }
}

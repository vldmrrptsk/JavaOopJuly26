package ru.academits.repetskiy.shapes_main;

import ru.academits.repetskiy.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] figures = new Shape[]{new Square(10),
                new Square(10),
                new Rectangle(10, 3),
                new Rectangle(1, 20),
                new Circle(3),
                new Circle(57),
                new Triangle(0, 1, 3, 7, 2, 10),
                new Triangle(0, 100, 3, 7, 2, 20)};

        Arrays.sort(figures, Comparator.comparingDouble(Shape::getArea));
        Shape maxArea = figures[figures.length - 1];

        System.out.printf("Фигура с наибольшей площадью: %s = %.2f\n", maxArea.toString(), maxArea.getArea());

        Arrays.sort(figures, Comparator.comparingDouble(Shape::getPerimeter));
        Shape secondMaxPerimeter = figures[figures.length - 2];

        System.out.printf("Фигура со вторым по величине периметром: %s = %.2f\n", secondMaxPerimeter.toString(), secondMaxPerimeter.getPerimeter());
    }
}

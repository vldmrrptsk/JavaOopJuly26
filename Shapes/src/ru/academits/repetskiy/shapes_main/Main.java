package ru.academits.repetskiy.shapes_main;

import ru.academits.repetskiy.shapes.*;
import ru.academits.repetskiy.shape_comparators.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(10),
                new Square(10),
                new Rectangle(10, 3),
                new Rectangle(1, 20),
                new Circle(3),
                new Circle(57),
                new Triangle(0, 1, 3, 7, 2, 10),
                new Triangle(0, 100, 3, 7, 2, 20)
        };

        System.out.println("Список заданных фигур: " + Arrays.toString(shapes));

        Arrays.sort(shapes, new ShapeAreaComparator());
        System.out.println("Фигура с наибольшей площадью " + shapes[0] + " имеет площадь: " + shapes[0].getArea());

        Arrays.sort(shapes, new ShapePerimeterComparator());
        System.out.println("Фигура со вторым по величине периметром " + shapes[1] + " имеет периметр: " + shapes[1].getPerimeter());
    }
}

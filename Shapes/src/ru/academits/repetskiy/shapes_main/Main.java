package ru.academits.repetskiy.shapes_main;

import ru.academits.repetskiy.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    @SuppressWarnings({"rawtypes", "unchecked"})
   public static void main(String[] args) {
        Shape[] shapes = {
                new Square(10),
                new Square(10),
                new Rectangle(10, 3),
                new Rectangle(1, 20),
                new Circle(3),
                new Circle(57),
                new Triangle(0, 1, 3, 7, 2, 10),
                new Triangle(0, 100, 3, 7, 2, 20)};

       Comparator areaComparator = (s1, s2) -> {
           Shape shape1 = (Shape) s1;
           Shape shape2 = (Shape) s2;
           return Double.compare(shape1.getArea(), shape2.getArea());
       };

       Arrays.sort(shapes, areaComparator);

       System.out.println("Фигура с максимальной площадью: " + shapes[0]);

       Comparator perimeterComparator = (s1, s2) -> {
           Shape shape1 = (Shape) s1;
           Shape shape2 = (Shape) s2;
           return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
       };

       Arrays.sort(shapes, perimeterComparator);

       System.out.println("Фигура со вторым по величине периметром: " + shapes[1]);
   }
}

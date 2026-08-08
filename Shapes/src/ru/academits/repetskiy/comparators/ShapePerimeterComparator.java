package ru.academits.repetskiy.comparators;

import ru.academits.repetskiy.shapes.Shape;
import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}

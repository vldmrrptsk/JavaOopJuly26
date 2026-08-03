package ru.academits.repetskiy.comparator;

import ru.academits.repetskiy.shapes.Shape;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public class FigureSorted {
    public void sortByMethod(Shape[] shapes, ToDoubleFunction<Shape> method) {
        Arrays.sort(shapes, Comparator.comparingDouble(method).reversed());
    }
}

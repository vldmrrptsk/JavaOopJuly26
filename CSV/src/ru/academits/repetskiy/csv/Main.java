package ru.academits.repetskiy.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\vladi\\OpenIDEProjects\\JavaOopJuly26\\CSV\\src\\ru\\academits\\repetskiy\\csv\\data.csv"))) {
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                System.out.print(scanner.next() + " ");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}

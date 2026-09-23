package ru.academits.repetskiy.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Alexey", 40),
                new Person("Nikolay", 50),
                new Person("Ivan", 5),
                new Person("Artem", 10),
                new Person("Elena", 30),
                new Person("Svetlana", 100),
                new Person("Bogdana", 43),
                new Person("Nikolay", 8)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println("Список уникальных имен: " + uniqueNames);
        System.out.println();

        String joinedUniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(joinedUniqueNames);
        System.out.println();

        OptionalDouble averageMinorAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        if (averageMinorAge.isPresent()) {
            System.out.printf("Средний возраст каждого из списка людей: %.2f%n", averageMinorAge.getAsDouble());
        } else {
            System.out.println("Нет человека для расчёта среднего возраста.");
        }
        System.out.println();

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Средний возраст по именам: " + averageAgesByNames);
        System.out.println();

        List<String> sortedNamesByAge = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .toList();
        System.out.println("Список имен по условию: " + sortedNamesByAge);
    }
}

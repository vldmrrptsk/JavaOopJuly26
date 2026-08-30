package ru.academits.repetskiy.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

        String uniqueNamesWithPrefix = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesWithPrefix);

        double averageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0.0);

        System.out.println("Средний возраст: " + averageAge);

        Map<String, Double> averageAgeByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        System.out.println("Средний возраст по именам: " + averageAgeByNames);

        List<String> sortedNamesByAge = persons.stream()
                .filter(p -> p.getAge() < 45 && p.getAge() > 20)
                .sorted((p1, p2) -> p1.getAge() - p2.getAge())
                .map(Person::getName)
                .toList();

        System.out.println("Список имен по условию: " + sortedNamesByAge);
    }
}

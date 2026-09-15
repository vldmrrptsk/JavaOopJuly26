package ru.academits.repetskiy.lambdas;

import java.util.Arrays;
import java.util.Comparator;
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
        System.out.println();

        String uniqueStringNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueStringNames);
        System.out.println();

        double averageAgeInListPeople = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.printf("Средний возраст каждого из списка людей: %.2f%n", averageAgeInListPeople);
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

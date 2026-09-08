package ru.academits.repetskiy.lambdas;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Возраст должен быть больше 0: " + age);
        }
        if (name == null) {
            throw new NullPointerException("Имя не должно быть NULL!");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("Имя не должно быть NULL!");
        }

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Возраст должен быть больше 0: " + age);
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + "Age: " + age;
    }
}

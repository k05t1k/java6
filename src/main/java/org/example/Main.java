package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Employee employee = new Employee("Заводчанин Великий", 25, 50000);
        Manager manager = new Manager("Менеджер заводчиков великих", 34, 70000);
        Product product = new Product("Спички высококачественные");

        LOGGER.warn("Происходит выполнение функции raiseSalary класса Employee");
        employee.raiseSalary(employee.getSalary(), 2);

        LOGGER.warn("Происходит выполнение функции raiseSalary класса Manager");
        manager.raiseSalary(employee.getSalary(), 2);

        LOGGER.warn("Происходит выполнение функции calculateDiscountedPrice класса Product");
        product.calculateDiscountedPrice(100, 5);

        LOGGER.warn("Происходит выполнение функции sum класса Product");
        System.out.println(product.sum(10, 2));

        LOGGER.warn("Происходит выполнение функции calculateProductionCapacity класса Product");
        System.out.println(product.calculateProductionCapacity(10, 2));

        employee.setName("Заводчанин не великий");
        employee.setSalary(7500);

        manager.setName("Менеджер не великих заводчанов");
        manager.setSalary(15000);

        product.setName("Стул");
        product.setPrice(1000);

        LOGGER.warn("Происходит выполнение функции formatToJson класса JsonFormatter");
        String jsonData = JsonFormatter.formatToJson("Hello, world!");
        LOGGER.info("Выполнено успешно!");

        LOGGER.warn("Происходит выполнение функции power класса MathOperations");
        double result = MathOperations.power(2, 3);
        LOGGER.info("Выполнено успешно!");

        LOGGER.warn("Происходит выполнение функции getRandomNumber класса MathOperations");
        int randomNum = MathOperations.getRandomNumber(1, 100);
        LOGGER.info("Выполнено успешно!");

        LOGGER.warn("Происходит создание массива");
        int[] numbers = {10, 5, 8, 15, 3};
        LOGGER.info("Выполнено успешно!");

        LOGGER.warn("Происходит выполнение функции findMax класса ArrayOperations");
        int maxNumber = ArrayOperations.findMax(numbers);
        LOGGER.info("Выполнено успешно!");
    }
}

abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Employee extends Person {
    private static final Logger LOGGER = LogManager.getLogger(Employee.class);

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
        LOGGER.debug(String.format("Зарплата работника %s изменена на %s", getName(), salary));
    }

    protected double salary;

    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
        LOGGER.trace(String.format("Создан новый работник: %s", name));
    }

    public void raiseSalary(double salary, double cooficent) {
        if (cooficent < 0) {
            LOGGER.error(String.format("Коэффициент повышения зарплаты не может быть отрицательным: %e", cooficent));
            return;
        }
        this.salary = salary + (salary * cooficent);
        LOGGER.info(String.format("Работник %s получил повышение зарплаты до %e", getName(), salary));
    }
}

class Manager extends Employee {
    private static final Logger LOGGER = LogManager.getLogger(Manager.class);

    public Manager(String name, int age, double salary) {
        super(name, age, salary);
        LOGGER.trace(String.format("Создан новый менеджер: %s", name));
    }

    @Override
    public void raiseSalary(double salary, double cooficent) {
        if (cooficent < 0) {
            LOGGER.error(String.format("Коэффициент повышения зарплаты не может быть отрицательным: %e", cooficent));
            return;
        }
        this.salary = salary + (salary * cooficent);
        LOGGER.info(String.format("Менеджер %s получил повышение зарплаты до %e", getName(), salary));    }
}

class Product {
    private static final Logger LOGGER = LogManager.getLogger(Product.class);

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        LOGGER.debug(String.format("Имя продукта %s изменено на %s", this.name, name));
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        LOGGER.debug(String.format("Цена продукта %s изменена на %s", this.name, price));
    }

    public Product(String name) {
        this.name = name;
    }

    public double calculateDiscountedPrice(double originalPrice, double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            LOGGER.error(String.format("Некорректный процент скидки: %e", discountPercentage));
            return originalPrice;
        }
        double discountAmount = originalPrice * (discountPercentage / 100.0);
        double discountedPrice = originalPrice - discountAmount;
        LOGGER.info(String.format("Рассчитана цена продукта %s со скидкой %e процентов: %e", this.name, discountPercentage, discountedPrice));
        return discountedPrice;
    }

    public int sum(int a, int b) {
        int result = a + b;
        LOGGER.debug(String.format("Рассчитана сумма %d + %d = %d", a, b, result));
        return result;
    }

    public double calculateProductionCapacity(int numberOfWorkers, int workingHoursPerDay) {
        if (numberOfWorkers <= 0 || workingHoursPerDay <= 0) {
            LOGGER.error(String.format("Некорректные параметры для расчета производительности: %d %d", numberOfWorkers, workingHoursPerDay));
            return 0;
        }
        double productionCapacity = numberOfWorkers * workingHoursPerDay;
        LOGGER.info(String.format("Рассчитана производительность для продукта %s: %d рабочих * %d часов в день = %e ед/день", this.name, numberOfWorkers, workingHoursPerDay, productionCapacity));
        return productionCapacity;
    }
}

class JsonFormatter {
    public static String formatToJson(String data) {
        return "{ \"data\": \"" + data + "\" }";
    }
}

class MathOperations {
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

class ArrayOperations {
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Нельзя передавать пустой массив");
        }
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
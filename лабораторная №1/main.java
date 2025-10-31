package org.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try {
            System.out.println("загрузка из JSON-файла");
            String jsonFilePath = "C:/Users/user/Desktop/customers.json";
            Customer customerFromJson = new Customer(jsonFilePath, true);
            printCustomer(customerFromJson);

            System.out.println("\nсоздание через обычный конструктор");
            Customer customerFromParams = new Customer(
                    "ООО Василёк",
                    "г. Екатеринбург, ул. Ленина, д. 7",
                    "+7 912 333-44-55",
                    "Петров Сергей Николаевич"
            );
            printCustomer(customerFromParams);

            System.out.println("\nсоздание из строки (CSV-формат)");
            String dataString = "ООО Тюльпан;г. Новосибирск, ул. Кирова, д. 3;8 (913) 222-33-44;Смирнов Алексей Иванович";
            Customer customerFromString = new Customer(dataString);
            printCustomer(customerFromString);

            System.out.println("\nПроверка toString(), toShortString() и equals()");

            Customer c1 = new Customer(
                    "ООО Ромашка",
                    "г. Москва, ул. Пушкина, д. 10",
                    "+7 (999) 123-45-67",
                    "Иванов Иван Иванович"
            );

            Customer c2 = new Customer(
                    "ООО Ромашка",
                    "г. Москва, ул. Пушкина, д. 10",
                    "+7 (999) 123-45-68",
                    "Иванов Иван Иванович"
            );

            System.out.println("Полная версия объекта:");
            System.out.println(c1.toString());

            System.out.println("\nКраткая версия объекта:");
            System.out.println(c1.toShortString());

            System.out.println("\nРавны ли объекты c1 и c2? " + c1.equals(c2));

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printCustomer(Customer customer) {
        System.out.println("Имя: " + customer.getName());
        System.out.println("Адрес: " + customer.getAddress());
        System.out.println("Телефон: " + customer.getPhone());
        System.out.println("Контактное лицо: " + customer.getContactPerson());
    }
}

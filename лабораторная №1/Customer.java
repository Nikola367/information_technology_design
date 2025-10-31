package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.FileReader;
import java.io.IOException;

public class Customer extends MainCustomer{

    private String address;
    private String contactPerson;

    public Customer(String name, String address, String phone, String contactPerson) {
        super(name, phone);
        this.address = validateAddress(address);
        this.contactPerson = validateContactPerson(contactPerson);
    }

    public Customer(String dataString) {
        super();
        if (dataString == null || dataString.trim().isEmpty()) {
            throw new IllegalArgumentException("Строка с данными пуста");
        }
        String[] parts = dataString.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException(
                    "Строка должна содержать 4 параметра, разделённых ';'"
            );
        }
        this.name = validateName(parts[0]);
        this.address = validateAddress(parts[1]);
        this.phone = validateAndNormalizePhone(parts[2]);
        this.contactPerson = validateContactPerson(parts[3]);
    }

    public Customer(String jsonFilePath, boolean isJsonFile) throws IOException {
        super();
        if (!isJsonFile) {
            throw new IllegalArgumentException("Для CSV используйте другой конструктор");
        }

        try (FileReader reader = new FileReader(jsonFilePath)) {
            Gson gson = new GsonBuilder().create();

            Customer temp = gson.fromJson(reader, Customer.class);

            this.name = validateName(temp.name);
            this.address = validateAddress(temp.address);
            this.phone = validateAndNormalizePhone(temp.phone);
            this.contactPerson = validateContactPerson(temp.contactPerson);

        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Ошибка в формате JSON", e);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = validateAddress(address);
    }


    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = validateContactPerson(contactPerson);
    }


    public static String validateAddress(String addr) {
        if (addr == null) {
            throw new IllegalArgumentException("Адрес не может быть null");
        }
        String trimmed = addr.trim();
        if (trimmed.length() < 3) {
            throw new IllegalArgumentException("Адрес слишком короткий (мин. 3 символа)");
        }
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("Адрес слишком длинный (макс. 100 символов)");
        }
        if (!trimmed.matches("^[0-9A-Za-zА-Яа-яЁё ,.-]{3,100}$")) {
            throw new IllegalArgumentException("Адрес содержит недопустимые символы");
        }
        return trimmed;
    }


    public static String validateContactPerson(String person) {
        if (person == null) {
            throw new IllegalArgumentException("Контактное лицо не может быть null");
        }
        String trimmed = person.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Контактное лицо не может быть пустым");
        }
        if (!trimmed.matches("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+( [А-ЯЁ][а-яё]+)?$")) {
            throw new IllegalArgumentException(
                    "Контактное лицо должно быть в формате 'Фамилия Имя (Отчество)', каждое слово с заглавной буквы"
            );
        }
        return trimmed;
    }
    @Override
    public String toString() {
        return "Customer {" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name)
                && address.equals(customer.address)
                && phone.equals(customer.phone)
                && contactPerson.equals(customer.contactPerson);
    }
}

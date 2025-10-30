package org.example;

public class Customer {

    private String name;
    private String address;
    private String phone;
    private String contactPerson;

    public Customer(String name, String address, String phone, String contactPerson) {
        this.name = validateName(name);
        this.address = validateAddress(address);
        this.phone = validateAndNormalizePhone(phone);
        this.contactPerson = validateContactPerson(contactPerson);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = validateAddress(address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = validateAndNormalizePhone(phone);
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = validateContactPerson(contactPerson);
    }

    public static String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя клиента не может быть null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым");
        }
        if (trimmed.length() < 2) {
            throw new IllegalArgumentException("Имя клиента слишком короткое (мин. 2 символа)");
        }
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("Имя клиента слишком длинное (макс. 100 символов)");
        }
        return trimmed;
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

    public static String validateAndNormalizePhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не может быть null");
        }
        String cleaned = phone.replaceAll("[^0-9+]", "");
        if (cleaned.matches("8\\d{10}")) {
            return cleaned;
        }
        if (cleaned.matches("\\+7\\d{10}")) {
            return "8" + cleaned.substring(2);
        }
        if (cleaned.matches("\\d{10}")) {
            return "8" + cleaned;
        }
        throw new IllegalArgumentException("Некорректный формат номера телефона");
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
}

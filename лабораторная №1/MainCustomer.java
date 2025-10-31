package org.example;

public class MainCustomer {

    protected static int idCounter = 0;
    protected int customerId;
    protected String name;
    protected String phone;

    public MainCustomer() {
        this.customerId = idCounter++;
    }

    public MainCustomer(String name, String phone) {
        this.customerId = idCounter++;
        this.name = validateName(name);
        this.phone = validateAndNormalizePhone(phone);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setPhone(String phone) {
        this.phone = validateAndNormalizePhone(phone);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    protected static String validateName(String name) {
        if (name == null) throw new IllegalArgumentException("Имя клиента не может быть null");
        String trimmed = name.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("Имя клиента не может быть пустым");
        if (trimmed.length() < 2) throw new IllegalArgumentException("Имя клиента слишком короткое (мин. 2 символа)");
        if (trimmed.length() > 100) throw new IllegalArgumentException("Имя клиента слишком длинное (макс. 100 символов)");
        return trimmed;
    }

    protected static String validateAndNormalizePhone(String phone) {
        if (phone == null) throw new IllegalArgumentException("Телефон не может быть null");
        String cleaned = phone.replaceAll("[^0-9+]", "");
        if (cleaned.matches("8\\d{10}")) return cleaned;
        if (cleaned.matches("\\+7\\d{10}")) return "8" + cleaned.substring(2);
        if (cleaned.matches("\\d{10}")) return "8" + cleaned;
        throw new IllegalArgumentException("Некорректный формат номера телефона");
    }

    public String toShortString() {
        return "MainCustomer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

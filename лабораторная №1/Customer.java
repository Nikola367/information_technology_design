package org.example;

public class Customer {

    private String name;
    private String address;
    private String phone;
    private String contactPerson;

    public Customer(String name, String address, String phone, String contactPerson) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

}


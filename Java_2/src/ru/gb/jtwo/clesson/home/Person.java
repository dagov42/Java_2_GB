package ru.gb.jtwo.clesson.home;


public class Person {
    String surname;
    String phone;
    String email;

    public Person(String surname, String phone, String email) {
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

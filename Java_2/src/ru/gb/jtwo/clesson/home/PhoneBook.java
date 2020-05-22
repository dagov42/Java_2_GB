package ru.gb.jtwo.clesson.home;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, HashMap<String, String>> phoneBook = new HashMap<String, HashMap<String, String>>();

    public PhoneBook() {
        Initialize(new Person("Petrov", "+7(959)999-99-99", "petrov@mail.ru"));
        Initialize(new Person("Ivanov", "+7(959)888-99-99", "ivanov@mail.ru"));
        Initialize(new Person("Ivanov", "+7(959)888-77-99", "ivanov1@mail.ru"));
        Initialize(new Person("Petrov", "+7(959)999-99-99", "petrov@mail.ru"));
        Initialize(new Person("Petrov", "+7(959)999-99-99", "petrov@mail.ru"));
    }

    public void Initialize(Person person) {
        if (phoneBook.containsKey(person.getSurname())){
            phoneBook.get(person.getSurname()).put(person.getPhone(), person.getEmail());
        }else {
            HashMap <String, String> temp = new HashMap<>();
            temp.put(person.getPhone(), person.getEmail());
            phoneBook.put(person.getSurname(), temp);
        }

    }

    public HashMap<String, HashMap<String, String>> findPhone(String surname){
        ArrayList<String> list = new ArrayList<>();
        for (String a:phoneBook.keySet()) {
            if (a.equals(surname)){
                list.addAll(phoneBook.get(a).keySet());
                return phoneBook;
            }
        }

        return null;
    }

//    public findMail(String surname){
//
//        return email;
//    }
}

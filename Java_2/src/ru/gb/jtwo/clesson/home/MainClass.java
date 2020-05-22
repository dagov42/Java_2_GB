package ru.gb.jtwo.clesson.home;

import java.util.*;

public class MainClass {

    static HashMap<String , Integer> hashMap = new HashMap<>();

    Person petrov = new Person("Petrov", "+7(959)999-99-99", "petrov@mail.ru");
    Person ivanov = new Person("Ivanov", "+7(959)888-99-99", "ivanov@mail.ru");
    Person ivanov1 = new Person("Ivanov", "+7(959)888-77-99", "ivanov1@mail.ru");
    Person ivanov2 = new Person("Ivanov", "+7(959)888-77-88", "ivanov2@mail.ru");
    Person sidorov = new Person("sidorov", "+7(959)777-99-99", "sidorov@mail.ru");
    Person sobolev = new Person("Sobolev", "+7(959)666-99-99", "sobolev@mail.ru");

    static final String[] wordsArray = {"lorem", "ipsum","dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod", "tempor", "incididund",
    "us", "labore", "et", "sed", "dolore","consectetur","ipsum", "eiusmod","dolor", "magna", "aliqua"};

    static HashSet<String> hashSet = new HashSet<>(Arrays.asList(wordsArray));
    static ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(wordsArray));

    public static void main(String[] args) {

        System.out.println(wordsArray.length);
        System.out.println(hashSet.toString());
        frequencyUseWords();
        System.out.println(Collections.frequency(arrayList,"dolor")); // tried to use another functions
        PhoneBook phoneBook = new PhoneBook();
        
    }
    public static void frequencyUseWords(){
        for (String s : wordsArray) {
            Integer result = hashMap.get(s);
            hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
        }
        System.out.println(hashMap.keySet());
    }
}

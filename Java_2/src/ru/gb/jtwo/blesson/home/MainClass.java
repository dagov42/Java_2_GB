package ru.gb.jtwo.blesson.home;

import static java.util.Arrays.deepToString;

public class MainClass {
    public static String toArray = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";


    public static void main(String[] args) {
        String[][] array = convert(toArray);
        arraySum(array);

    }

    public static String[][] convert(String string) {
        int length = string.split("\n").length;
        String[][] newArray = new String[length][length];
        String[] splitN = string.split("\n");
        System.out.println(deepToString(splitN));

        for (int i = 0; i < length; i++) {
            String[] splitSpaces = splitN[i].split(" ");
            for (int j = 0; j < length; j++) {
                newArray[i][j] = splitSpaces[j];
            }
        }
        return newArray;
    }

    public static double arraySum(String[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){
                    System.out.println("There aren't a number in a cells");
                    e.printStackTrace();
                }
            }
        }
        return sum/2;
    }
}


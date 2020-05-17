package ru.gb.jtwo.alesson.online;

public class Array {

    private static int DEFAULT_CAPACITY = 10;
    private final int CUTRATE = 4;
    private static Object[] array = new Object[DEFAULT_CAPACITY];
    private static int index;

    public static int getDefaultCapacity() {
        return DEFAULT_CAPACITY;
    }

    public static void add(int item) {
        if (index == array.length - 1)
            resize(array.length >> 1);
        array[index++] = item;
    }

    public static void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, index);
        array = newArray;
    }

    public void remove(int index) {
        for (int i = index; i < this.index; i++)
            array[i] = array[i + 1];
        array[this.index] = null;
        this.index--;
        if (array.length > DEFAULT_CAPACITY && this.index < array.length / CUTRATE)
            resize(array.length >> 1);
    }
}

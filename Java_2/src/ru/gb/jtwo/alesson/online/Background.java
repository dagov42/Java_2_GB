package ru.gb.jtwo.alesson.online;

import java.awt.*;
import java.util.*;

public class Background {

    static int red = 0;
    static int green = 0;
    static int blue = 0;

    public static Color randomColor() {
        Random random = new Random();
        blue +=random.nextInt(5);
        if (blue >= 255){
            blue %=255;
        }
        green +=random.nextInt(5);
        if (green >= 255){
            green %=255;
        }
        red +=random.nextInt(5);
        if (red >= 255){
            red %=255;
        }
        return new Color(red, green, blue);

    }
}

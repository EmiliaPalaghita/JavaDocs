package com.timnet.examples;

import java.util.Random;

/**
 * Created by Emilia.Palaghita on 10-Jul-17.
 */
public class MyClass {
    public String concatenate(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return null;
        } else if (str1 == null) {
            return str2;
        } else if (str2 == null) {
            return str1;
        }
        return str1 + str2;
    }

    public boolean getBoolean() {
        return new Random().nextBoolean();
    }
}

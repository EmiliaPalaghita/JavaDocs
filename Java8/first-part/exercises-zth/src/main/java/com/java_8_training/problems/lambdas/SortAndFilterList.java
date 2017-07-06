package com.java_8_training.problems.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortAndFilterList {

    public static void main(String[] args) {
        sortAndFilterList();
    }

    private static void sortAndFilterList() {
        List<String> animals = Arrays.asList("lion", "bear", "dear", "Dog", "Cat", "mouse", "cougar", "elephant", "giraffe", "lemur", "Bison", "chimpanzee", "hyena", "cheetah");

        // TODO use lambda and functional interfaces / method references to sort by

        // 1. length
        animals.sort((a1, a2) -> a1.length() - a2.length());
        System.out.println(animals.toString());
        System.out.println("----------------------");
        // 2. reverse length
        animals.sort((a1, a2) -> a2.length() - a1.length());
        System.out.println(animals.toString());
        System.out.println("----------------------");
        // 3. alphabetically
        animals.sort(String::compareToIgnoreCase);
        System.out.println(animals.toString());
        System.out.println("----------------------");
        // 4. put the strings that contain 'e' first in the list. The other ones last.
        animals.sort((o1, o2) -> {
            if (!o1.contains("e")) {
                return o1.compareToIgnoreCase(o2);
            } else {
                return o2.compareToIgnoreCase(o1);
            }
        });
        System.out.println(animals.toString());
        System.out.println("----------------------");
        // 5. filter only the strings that have the first letter capitalized
        animals = filter(animals, new UpperLetter() {
            @Override
            public boolean filterUpperLetter(String str) {
                char c = str.charAt(0);
                return Character.isUpperCase(c);
            }
        });
        System.out.println(animals.toString());
        System.out.println("----------------------");
    }

    public static List<String> filter(List<String> animals, UpperLetter p) {
        List<String> result = new ArrayList<String>();
        for(String s : animals) {
            if(p.filterUpperLetter(s)) {
                result.add(s);
            }
        }
        return result;
    }
}
@FunctionalInterface
interface UpperLetter {
    public boolean filterUpperLetter(String str);
}
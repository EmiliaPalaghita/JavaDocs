package ro.teamnet.zerotohero.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

public class ClassReflectionDemoMain {

    class Inner {

    }

    public static void main(String[] args) {
        // get the class for a String object, and print it
        System.out.println("gigica".getClass());

        // get the class of an Enum, and print it

        System.out.println(Day.FRIDAY.getClass());

        // get the class of a collection, and print it

        Class col = new HashSet().getClass();
        System.out.println(col);


        // get the class of a primitive type, and print it

        System.out.println(int.class);

        // get and print the class for a field of primitive type

        Clasa c = new Clasa();
        try {
            Field cls = c.getClass().getField("chestie");
            System.out.println(cls.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // get and print the class for a primitive type, using the wrapper class

        System.out.println(Integer.TYPE.getName());

        // get the class for a specified class name

        try {
            System.out.println(Class.forName("ro.teamnet.zerotohero.reflection.ClassReflectionDemoMain"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // get the superclass of a class, and print it
        // get the superclass of the superclass above, and print it

        System.out.println(ArrayList.class.getSuperclass());
        System.out.println(ArrayList.class.getSuperclass().getSuperclass());

        // get and print the declared classes within some other class

        Class[] classes = ClassReflectionDemoMain.class.getDeclaredClasses();
        for (Class cl : classes) {
            System.out.println(cl.getName());
        }

        // print the number of constructors of a class

        System.out.println(ArrayList.class.getDeclaredConstructors().length);

        // get and invoke a public constructor of a class

        try {
            Constructor constructor = ArrayList.class.getConstructor();
            try {
                ArrayList l = (ArrayList) constructor.newInstance();
                System.out.println("a mers");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        // get and print the class of one private field

        try {
            Field clasa = Clasa.class.getDeclaredField("f");
            System.out.println(clasa.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        // set and print the value of one private field for an object

        try {
            Field privateField = c.getClass().getDeclaredField("f");
            privateField.setAccessible(true);
            try {
                privateField.set(c, 2);
                int result = (int) privateField.get(c);
                System.out.println("result is " + result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // get and print only the public fields class

        Field[] fields = Clasa.class.getFields();
        for (Field f : fields) {
            System.out.println(f.getType());
        }


        // get and invoke one public method of a class

        try {
            Method m = Clasa.class.getMethod("afisare");
            try {
                m.invoke(c);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        // get and invoke one inherited method of a class

        try {
            Method tostr = c.getClass().getMethod("toString");
            try {
                tostr.invoke(new Object());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?

        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            c.mere();
        }
        long stop = System.nanoTime();
        System.out.println("timp pentru 10 ori " + (stop - start));

        start = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            try {
                Method m = Clasa.class.getMethod("mere");
                try {
                    m.invoke(c);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        stop = System.nanoTime();
        System.out.println("timp pentru 100 ori " + (stop - start));

    }
}

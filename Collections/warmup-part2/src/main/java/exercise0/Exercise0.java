package exercise0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 * <p>
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 * print all its elements.
 */
public class Exercise0 {

    public Exercise0() {

    }

    public void iterateThroughMap() {

        Map<Integer, String> myMap = new HashMap<Integer, String>();
        myMap.put(1, new String("Gigel"));
        myMap.put(1, new String("Marcel"));
        myMap.put(2, new String("Ana"));
        myMap.put(3, new String("Ion"));
        //  Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        //  Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map

        Set keys = myMap.keySet();

        Iterator it = keys.iterator();
        while(it.hasNext()) {
            Integer i = (Integer)it.next();
            System.out.println(i + " = " + myMap.get(i));
        }

        //  Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        //  Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]

    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}

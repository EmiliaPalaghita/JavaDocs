package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 * <p>
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {

        //  Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        //
        if (key == null) {
            return null;
        }
        int hash = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        if (hash < 0 || hash >= BUCKET_ARRAY_SIZE) {
            return null;
        }
        if (buckets.size() < hash) {
            return null;
        }
        LinkedList<MyEntry> result = buckets.get(hash);
        if (result.size() == 0) {
            return null;
        }
        for (MyEntry entry : result) {
            if (entry.getKey().equals(key) && entry != null) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        //
        if (key == null) {
            buckets.get(0).add(new MyEntry(key, value));
        } else {
            // boolean ok = true;
            int hash = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
            if (hash >= 0 && hash < 16) {
                if (hash < buckets.size()) {

                    if (buckets.get(hash).size() == 0) {
                        buckets.get(hash).add(new MyEntry(key, value));
                    } else {
                        for (MyEntry entry : buckets.get(hash)) {
                            if (entry.equals(new MyEntry(key, value))) {
                                entry.setValue(value);
                            }
                        }

                    }

                }
            }
        }
    }

    public Set<String> keySet() {
        //
        Set<String> keys = new HashSet<String>();
        for (LinkedList<MyEntry> list : buckets) {
            for (MyEntry entry : list) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public Collection<String> values() {
        //
        Collection<String> values = new ArrayList<String>();
        for (LinkedList<MyEntry> list : buckets) {
            for (MyEntry entry : list) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    public String remove(String key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found

        int hash = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        if (hash < 0 || hash >= 16) {
            return null;
        }
        Iterator it = buckets.get(hash).listIterator();
        while (it.hasNext()) {
            MyEntry entry = (MyEntry) it.next();
            if(entry.getKey().equals(key)) {
                it.remove();
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        //
        for (LinkedList<MyEntry> list : buckets) {
            for (MyEntry entry : list) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        //
        for (LinkedList<MyEntry> list : buckets) {
            for (MyEntry entry : list) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        int sum = 0;
        for (LinkedList<MyEntry> list : buckets) {
            sum += list.size();
        }
        return sum;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            buckets.get(i).clear();
        }
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> result = new HashSet<MyEntry>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            result.addAll(buckets.get(i));
        }
        return result;
    }

    public boolean isEmpty() {
        //
        return size() == 0;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

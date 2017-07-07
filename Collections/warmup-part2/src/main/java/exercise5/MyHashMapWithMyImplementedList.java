package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 * <p>
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {

    //  uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMapWithMyImplementedList() {
        //
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
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
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> list = buckets.get(i);
            for (MyEntry entry : list) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public Collection<String> values() {
        //
        Collection<String> values = new ArrayList<String>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> list = buckets.get(i);
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
            if (entry.getKey().equals(key)) {
                it.remove();
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        //
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> list = buckets.get(i);
            for (MyEntry entry : list) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {

        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> list = buckets.get(i);
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
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            sum += buckets.get(i).size();
        }
        return sum;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            buckets.get(i).clear();
        }
    }

    public Set<MyHashMap.MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyHashMap.MyEntry> result = new HashSet<MyHashMap.MyEntry>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            LinkedList<MyEntry> list = buckets.get(i);
            for(int j = 0; j < list.size(); j ++) {
                MyEntry newEntry = list.get(j);
                result.add(new MyHashMap.MyEntry(newEntry.getKey(), newEntry.getValue()));
            }
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

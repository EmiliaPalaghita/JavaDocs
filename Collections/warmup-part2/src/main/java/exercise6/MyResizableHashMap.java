package exercise6;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Create a resizable generic HashMap. When the number of entries exceeds (load capacity * bucket array size)
 * the HashMap needs to be resized.
 * <p>
 * Resizing (rehashing) consists in increasing the size of the bucket array with the value of
 * INCREASE_SIZE_FACTOR. After this step, all the entries that were stored in the HashMap
 * must be inserted in the new bucket array according to the insertion algorithm in a HashMap:
 * the entry must be placed in a bucket after applying the hash function (hashcode modulo (bucket array size))
 * on the key's hashcode value. The result of the hash function will return the index from the
 * bucket array where the entry will be stored. (see HashMap documentation)
 * <p>
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyResizableHashMap<K, V> {

    /**
     * The array of buckets.
     */
    private Node<K, V>[] buckets;

    /**
     * Default initial capacity for the bucket array.
     */
    private final int DEFAULT_BUCKET_ARRAY_SIZE = 16;

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * The number of entries stored in the Map.
     */
    private int size;
    private int capacity;

    public MyResizableHashMap() {

        //  Initialize buckets list
        size = 0;
        capacity = DEFAULT_BUCKET_ARRAY_SIZE;
        buckets = new Node[capacity];
    }

    private void resize() {
        // TODO function that does the rehashing of the HashMap
        int newSize = size * INCREASE_SIZE_FACTOR;
        Node<K, V>[] newBuckets = new Node[newSize];
        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> node = buckets[i];
            // newBuckets[i] = new Node<K,V>(node.getEntry(), node.getHash(), );
            while (node.getNextElement() != null) {
                MyEntry entry = node.getEntry();


            }

        }
    }

    public V get(K key) {
        //
        if (key == null) {
            return null;
        }
        if (size == 0) {
            return null;
        }
        int hash = Math.abs(key.hashCode() % capacity);
        if (hash < 0 || hash >= capacity) {
            return null;
        }

        if (capacity < hash) {
            return null;
        }

        Node<K, V> node = buckets[hash];
        if (node == null) {
            return null;
        }
        while (node != null) {
            MyEntry entry = node.getEntry();
            if (entry.getKey().equals(key)) {
                return (V) entry.getValue();
            }
            node = node.getNextElement();
        }
        return null;
    }

    public void put(K key, V value) {
        //
        if (key == null) {
            Node n = buckets[0];
            if (n == null) {
                while (n.getNextElement() != null) {
                    if (n.getEntry().getKey().equals(key)) {
                        n.getEntry().setValue(value);
                        return;
                    }
                    n = n.getNextElement();
                }
                Node newNode = new Node(new MyEntry(key, value), key.hashCode(), null);
                n.setNextElement(newNode);
            } else {
                buckets[0] = new Node(new MyEntry(key, value), 0, null);
            }
        } else {
            int hash = Math.abs(key.hashCode() % capacity);
            if (hash >= 0 && hash < 16) {
                if (hash < capacity) {
                    Node n = buckets[hash];
                    if (n != null) {
                        while (n.getNextElement() != null) {
                            if (n.getEntry().getKey().equals(key)) {
                                n.getEntry().setValue(value);
                                return;
                            }
                            n = n.getNextElement();
                        }
                        Node newNode = new Node(new MyEntry(key, value), key.hashCode(), null);
                        n.setNextElement(newNode);
                    } else {
                        buckets[hash] = new Node<K, V>(new MyEntry(key, value), key.hashCode(), null);
                    }
                }
            }
        }
        size++;
    }

    public Set<K> keySet() {
        //
        Set<K> keys = new HashSet<K>();
        for (Node node : buckets) {
            while (node != null) {
                keys.add((K) node.getEntry().getKey());
                node = node.getNextElement();
            }
        }
        return keys;
    }

    public Collection<V> values() {
        //
        Collection<V> values = new ArrayList<V>();
        for (Node node : buckets) {
            while (node != null) {
                values.add((V) node.getEntry().getValue());
                node = node.getNextElement();
            }
        }
        return values;
    }

    public V remove(K key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        return null;
    }

    public boolean containsKey(K key) {
        //
        for (Node node : buckets) {
            while (node != null) {
                if (node.getEntry().getKey().equals(key)) {
                    return true;
                }
                node = node.getNextElement();
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        //
        for (Node node : buckets) {
            while (node != null) {
                if (node.getEntry().getValue().equals(value)) {
                    return true;
                }
                node = node.getNextElement();
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        for (Node n : buckets) {
            n = null;
        }
        size = 0;
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> entries = new HashSet<MyEntry>();
        for (Node n : buckets) {
            while (n != null) {
                entries.add(n.getEntry());
                n = n.getNextElement();
            }
        }
        return entries;
    }

    public boolean isEmpty() {
        //
        return size == 0;
    }

    public static class MyEntry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    static class Node<K, V> {
        private final MyEntry<K, V> entry;
        private final int hash;
        private Node<K, V> nextElement;

        public Node(MyEntry<K, V> entry, int hash, Node<K, V> nextElement) {
            this.entry = entry;
            this.hash = hash;
            this.nextElement = nextElement;
        }

        public MyEntry<K, V> getEntry() {
            return entry;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<K, V> nextElement) {
            this.nextElement = nextElement;
        }
    }
}

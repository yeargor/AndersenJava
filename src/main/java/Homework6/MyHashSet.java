package Homework6;

import java.util.Iterator;

public class MyHashSet<K> implements Iterable<K> {

    private MyHashMap<K,Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new MyHashMap<>();
    }

    public boolean contains(Object key) {
        return map.containsKey((K) key);
    }

    public Iterator<K> iterator(){
        return map.keySet().iterator();
    }

    public boolean add(K key) {
        return map.put(key, PRESENT) != null;
    }

    public boolean remove(Object key) {
        return map.delete((K) key) != null;
    }

    public int size(){
        return map.getSize();
    }
}

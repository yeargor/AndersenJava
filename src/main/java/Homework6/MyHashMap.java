package Homework6;

import lombok.AllArgsConstructor;
import java.util.*;

public class MyHashMap<K,V> implements Iterable<Map.Entry<K, V>>{

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private float loadfactor;
    private int capacity;
    private int size;
    private Node<K, V>[] table;

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadfactor){
        if (capacity <= 0){
            throw new IllegalArgumentException("Capacity must be higher than zero");
        }
        if (loadfactor <= 0 || loadfactor > 1){
            throw new IllegalArgumentException("LoadFactor must be between 0 and 1");
        }
        this.capacity = capacity;
        this.loadfactor = loadfactor;
        this.table = new Node[capacity];
    }

    /* ---------------- hashMap methods -------------- */

    public V put(K key, V value){

        ensureCapacity();

        int index = (capacity - 1) & hash(key);
        Node<K,V> node = table[index];
        Node<K,V>lastCheckedNode = null;

        if(node == null){
            table[index] = new Node<>(key,value,null);
            size++;
            return value;
        }

        while(node != null){
            if(node.key.equals(key)){
                node.value = value;
                return value;
            }

            lastCheckedNode = node;
            node = node.next;
        }

        lastCheckedNode.next = new Node<>(key, value,null);
        size++;
        return value;
    }

    public V get(K key){
        int index = (capacity - 1) & hash(key);
        Node<K,V> node = table[index];

        while(node != null){
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public V delete(K key){
        int index = (capacity - 1) & hash(key);
        Node<K,V> node = table[index];
        Node<K,V>lastCheckedNode = null;

        while(node!= null) {
            if (node.key.equals(key)){
                if(lastCheckedNode == null){
                    table[index] = node.next;
                }else{
                    lastCheckedNode.next = node.next;
                }

                size--;
                return node.value;
            }

            lastCheckedNode = node;
            node = node.next;
        }

        return null;
    }

    public boolean containsKey(K key){
        return get(key) != null;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void ensureCapacity(){
        if (size >= capacity * loadfactor){
            int newCapacity = 2 * capacity;
            Node<K,V>[] newTable = new Node[newCapacity];

            for(Node<K,V>node : table){
                while (node != null) {
                    int newIndex = (newCapacity - 1) & hash(node.key);
                    Node<K, V> nextNode = node.next;
                    node.next = newTable[newIndex];
                    newTable[newIndex] = node;
                    node = nextNode;
                }
            }

            capacity = newCapacity;
            table = newTable;
        }
    }

    @AllArgsConstructor
    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
    }

    /* ---------------- entryIterator -------------- */

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    public class EntryIterator implements Iterator<Map.Entry<K, V>>{

        private int bucketIndex = 0;
        private Node<K, V> currentNode = null;

        public EntryIterator(){
            moveNext();
        }

        public void moveNext(){
            while (bucketIndex < table.length && table[bucketIndex] == null) {
                bucketIndex++;
            }
            if (bucketIndex < table.length) {
                currentNode = table[bucketIndex];
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Map.Entry<K, V> next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<K, V> lastCheckedNode = currentNode;
            currentNode = currentNode.next;

            if (currentNode == null) {
                bucketIndex++;
                moveNext();
            }

            return new Map.Entry<K, V>() {
                @Override
                public K getKey() {
                    return lastCheckedNode.key;
                }

                @Override
                public V getValue() {
                    return lastCheckedNode.value;
                }

                @Override
                public V setValue(V value) {
                    return lastCheckedNode.value = value;
                }
            };
        }
    }

    /* ---------------- keySet, keyIterator -------------- */

    public Set<K> keySet() {
        return new KeySet();
    }

    private class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class KeyIterator implements Iterator<K> {

        private int bucketIndex = 0;
        private Node<K, V> currentNode = null;

        public KeyIterator() {
            moveNext();
        }

        public void moveNext(){
            while (bucketIndex < table.length && table[bucketIndex] == null) {
                bucketIndex++;
            }
            if (bucketIndex < table.length) {
                currentNode = table[bucketIndex];
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<K, V> lastCheckedNode = currentNode;
            currentNode = currentNode.next;

            if (currentNode == null) {
                bucketIndex++;
                moveNext();
            }

            return lastCheckedNode.key;
        }
    }
}
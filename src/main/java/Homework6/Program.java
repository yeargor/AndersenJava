package Homework6;

import java.util.Map;

public class Program {

    public static void main(String[] args) {

        //Not using DI because I want to demonstrate some logic, that is exclusive for MyArrayList
        MyArrayList<Integer> list = new MyArrayList<>(20);
        System.out.println("MyArrayList capacity before dynamic resizing: " + list.getCapacity());

        for(int i=0;i<21;i++){
            list.add(i);
        }
        System.out.println("MyArrayList: " + list);
        System.out.println("MyArrayList capacity after dynamic resizing: " + list.getCapacity());

        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        System.out.println("MyHashMap capacity before dynamic resizing: " + hashMap.getCapacity());

        for(int i = 0; i < 20; i++){
            hashMap.put("Item"+i, i);
        }

        hashMap.put("Item2", 20000000);

        for(Map.Entry<String, Integer> n : hashMap) {
            System.out.println("Key: " + n.getKey() + " Value: " + n.getValue());
        }

        System.out.println("MyHashMap capacity after dynamic resizing: " + hashMap.getCapacity());
        MyHashSet<String> hashSet = new MyHashSet<>();

        for(int i=0;i<30;i++) {
           hashSet.add("obj " + i);
           hashSet.add("obj " + i);
        }

        hashSet.remove("obj 0");

        for(String s : hashSet){
            System.out.println(s);
        }

        System.out.println("hashSet contains obj 140: " + hashSet.contains("obj 140"));
        System.out.println("Items to hashSet were added twice, but MyHashSet current size is: " + hashSet.size());
    }
}

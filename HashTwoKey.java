/*
Anvay Buch
Two-Key Database using Hash implementation
Creates a database with a Hashmap implementation and gives it operations/functions
Uses hashmap and is derived from my original TwoKey program
The benefits of using a hashmap is its performance, which is in O(1) time as opposed to
a treemap where the performance is typically O(log(n)) time
A hashmap will not do any sorting, as opposed to a treemap which does sort, therefore the hashmap allows for heterogenous values 
*/

import java.util.HashMap;

public class HashTwoKey<K1 extends Comparable<K1>, K2 extends Comparable<K2>, D> {
    public K1 key1;
    public K2 key2;
    public D data;

    public HashTwoKey(K1 key1, K2 key2, D data) {// constructor to init keys and data
        this.key1 = key1;
        this.key2 = key2;
        this.data = data;
    }

    public HashTwoKey() {// default constructor

    }

    HashMap<K1, HashTwoKey<K1, K2, D>> Hash1 = new HashMap<>(); // creating hashmap for key1
    HashMap<K2, HashTwoKey<K1, K2, D>> Hash2 = new HashMap<>();// creating hashmap for key2

    // insert a record into the database.
    void insert(K1 key1, K2 key2, D data) {
        HashTwoKey<K1, K2, D> rec = new HashTwoKey<K1, K2, D>(key1, key2, data); // Creating of record (rec references
                                                                                 // record
        // going forward)
        Hash1.put(key1, rec);
        Hash2.put(key2, rec);
    }

    // search and return the data associated with key1
    D search1(K1 key1) {
        HashTwoKey<K1, K2, D> rec = Hash1.get(key1);
        if (rec == null)
            return null; // return null if no record associated
        else
            return rec.data;
    }

    // search and return the data associated with key2
    D search2(K2 key2) {
        HashTwoKey<K1, K2, D> rec = Hash2.get(key2);
        if (rec == null)
            return null; // return null if no record associated
        else
            return rec.data;
    }

    // replace the record associated with key1 to contain the new data
    void modify1(K1 key1, D data) {
        HashTwoKey<K1, K2, D> rec = Hash1.get(key1);
        HashTwoKey<K1, K2, D> TempR = new HashTwoKey<K1, K2, D>(key1, rec.key2, data);
        Hash1.replace(key1, TempR);
        Hash2.replace(TempR.key2, TempR);
    }

    // replace the record associated with key2 to contain the new data
    void modify2(K2 key2, D data) {
        HashTwoKey<K1, K2, D> rec = Hash2.get(key2);
        HashTwoKey<K1, K2, D> TempR = new HashTwoKey<K1, K2, D>(rec.key1, key2, data);
        Hash2.replace(key2, TempR);
        Hash1.replace(TempR.key1, TempR);

    }

    // remove the record associated with key1
    void delete1(K1 key1) {
        HashTwoKey<K1, K2, D> rec = Hash1.get(key1);
        Hash1.remove(rec.key1);

    }

    // remove the record associated with key2
    void delete2(K2 key2) {
        HashTwoKey<K1, K2, D> rec = Hash2.get(key2);
        Hash2.remove(rec.key2);
    }

    // change the second key of the record associated with key1 to be key2
    void change1(K1 key1, K2 key2) {
        HashTwoKey<K1, K2, D> rec = Hash1.get(key1);
        HashTwoKey<K1, K2, D> TempR = new HashTwoKey<K1, K2, D>(key1, key2, rec.data);
        Hash1.replace(key1, TempR);
        Hash2.remove(rec.key2);
        Hash2.put(key2, TempR);

    }

    // change the first key of record associated with key2 to be key1
    void change2(K2 key2, K1 key1) {
        HashTwoKey<K1, K2, D> rec = Hash2.get(key2);
        HashTwoKey<K1, K2, D> TempR = new HashTwoKey<K1, K2, D>(key1, key2, rec.data);
        Hash2.replace(key2, TempR);
        Hash1.remove(rec.key1);
        Hash1.put(key1, TempR);

    }

    // return the list (key, data) in order by key1 as a String
    String list1() {
        return Hash1.toString();

    }

    // return the list (key, data) in order by key2 as a String
    String list2() {
        return Hash2.toString();
    }

    public String toString() {
        return data + "";
    }
}
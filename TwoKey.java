/*
Anvay Buch
Two-Key Database using Tree implementation
Creates a database with a BST implementation and gives it operations/functions
Uses Tree.java and help was taken from a tutor on varsity tutors for the beginning of the program as I could not meet with the class tutor/ was home
*/

import java.util.*;

public class TwoKey<K1 extends Comparable<K1>, K2 extends Comparable<K2>, D> {
    public K1 key1;
    public K2 key2;
    public D data;

    public TwoKey(K1 key1, K2 key2, D data) {// constructor to init keys and data
        this.key1 = key1;
        this.key2 = key2;
        this.data = data;
    }

    public TwoKey() {// default constructor

    }

    Tree<K1, TwoKey<K1, K2, D>> Tree1 = new Tree<>(); // creating tree for key1
    Tree<K2, TwoKey<K1, K2, D>> Tree2 = new Tree<>(); // creating tree for key2

    // insert a record into the database.
    void insert(K1 key1, K2 key2, D data) {
        TwoKey<K1, K2, D> rec = new TwoKey<K1, K2, D>(key1, key2, data); // Creating of record (rec references record
                                                                         // going forward)
        Tree1.add(key1, rec);
        Tree2.add(key2, rec);
    }

    // search and return the data associated with key1
    D search1(K1 key1) {
        TwoKey<K1, K2, D> rec = Tree1.find(key1);
        if (rec == null)
            return null; // return null if no record associated
        else
            return rec.data;
    }

    // search and return the data associated with key2
    D search2(K2 key2) {
        TwoKey<K1, K2, D> rec = Tree2.find(key2);
        if (rec == null)
            return null; // return null if no record associated
        else
            return rec.data;
    }

    // modify the record associated with key1 to contain the new data
    void modify1(K1 key1, D data) {
        TwoKey<K1, K2, D> rec = Tree1.find(key1);
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, rec.key2, data);
        Tree1.modify(key1, TempR);
        Tree2.modify(TempR.key2, TempR);
    }

    // modify the record associated with key2 to contain the new data
    void modify2(K2 key2, D data) {
        TwoKey<K1, K2, D> rec = Tree2.find(key2);
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(rec.key1, key2, data);
        Tree2.modify(key2, TempR);
        Tree1.modify(TempR.key1, TempR);

    }

    // delete the record associated with key1
    void delete1(K1 key1) {
        TwoKey<K1, K2, D> rec = Tree1.find(key1);
        Tree1.delete(rec.key1);

    }

    // delete the record associated with key2
    void delete2(K2 key2) {
        TwoKey<K1, K2, D> rec = Tree2.find(key2);
        Tree2.delete(rec.key2);
        // Tree1.delete(rec.key2?);
    }

    // change the second key of the record associated with key1 to be key2
    void change1(K1 key1, K2 key2) {
        TwoKey<K1, K2, D> rec = Tree1.find(key1);
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, key2, rec.data);
        Tree1.modify(key1, TempR);
        Tree2.delete(rec.key2);
        Tree2.add(key2, TempR);

    }

    // change the first key of record associated with key2 to be key1
    void change2(K2 key2, K1 key1) {
        TwoKey<K1, K2, D> rec = Tree2.find(key2);
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, key2, rec.data);
        Tree2.modify(key2, TempR);
        Tree1.delete(rec.key1);
        Tree1.add(key1, TempR);

    }

    // return the list (key, data) in order by key1 as a String
    String list1() {
        // return Tree1.data();
        return Tree1.toString();

    }

    // return the list (key, data) in order by key2 as a String
    String list2() {
        return Tree2.toString();
    }

    public String toString() {
        return data + "";
    }
}
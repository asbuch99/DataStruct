import java.util.*;

public class TwoKey<K1 extends Comparable<K1>, K2 extends Comparable<K2>, D> {
    public K1 key1;
    public K2 key2;
    public D data;

    public TwoKey() {
    }

    public TwoKey(K1 key1, K2 key2, D data) {
        this.key1 = key1;
        this.key2 = key2;
        this.data = data;
    }

    Tree<K1, TwoKey<K1, K2, D>> t1 = new Tree<>();
    Tree<K2, TwoKey<K1, K2, D>> t2 = new Tree<>();

    // "inserts a rec to the database"
    void insert(K1 key1, K2 key2, D data) {

        TwoKey<K1, K2, D> rec = new TwoKey<K1, K2, D>(key1, key2, data);

        // add the rec of key1 into t1
        t1.add(key1, rec);
        // add the rec of key2 into t2
        t2.add(key2, rec);
    }

    // "finds and returns the data associated with key1"
    D search1(K1 key1) {

        // call find method from Tree class for:
        // create rec constructor for t1 object and key1 parameter from TwoKey
        TwoKey<K1, K2, D> rec = t1.find(key1);
        if (rec == null) // if the rec is empty
            return null; // null return
        else
            return rec.data; // if not, return the rec of the data
    }

    // "finds and returns the data associated with key2"
    D search2(K2 key2) {

        // call find method from Tree class for:
        // create rec constructor for t2 object and key2 parameter from TwoKey
        TwoKey<K1, K2, D> rec = t2.find(key2);
        if (rec == null) // if the rec is empty
            return null; // null return
        else
            return rec.data; // if not, return the rec of the data
    }

    // "modifies the rec associated with key 1 to contain the new data"
    void modify1(K1 key1, D data) {

        // find key of first tree
        // create rec constructor for t1 object and key1 parameter from TwoKey
        TwoKey<K1, K2, D> rec = t1.find(key1);

        // create TempR to replace rec.key2
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, rec.key2, data);
        // call modify on key 1 and the new rec
        t1.modify(key1, TempR);
        // then call modify for the new rec of key2
        t2.modify(TempR.key2, TempR);

    }

    // "modifies the rec associated with key2 to contain the new data"
    void modify2(K2 key2, D data) {

        // find key of second tree
        // create rec constructor for t2 object and key2 parameter from TwoKey
        TwoKey<K1, K2, D> rec = t2.find(key2);

        // create TempR to replace rec of key2
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(rec.key1, key2, data);
        // call modify on key2 and the new rec
        t2.modify(key2, TempR);
        // then call modify for the new rec
        t1.modify(TempR.key1, TempR);

    }

    // "deletes the rec associated with key1 to contain the new data"
    void delete1(K1 key1) {
        TwoKey<K1, K2, D> rec = t1.find(key1);

        // find key of second tree
        // create rec constructor for t2 object and key2 parameter from TwoKey
        // TwoKey<K1, K2, D> rec = t2.find(key2);

        t1.delete(rec.key1);

    }

    // "deletes the rec associated with key2 to contain the new data"
    void delete2(K2 key2) {

        // find key of second tree
        // create rec constructor for t2 object and key2 parameter from TwoKey
        // TwoKey<K1, K2, D> rec = t2.find(key2);

        t2.delete(this.key2);

    }

    // "changes the second key of the rec associated with key1 to be key2"
    void change1(K1 key1, K2 key2) {
        TwoKey<K1, K2, D> rec = t1.find(key1);

        // create TempR constructor to replace rec of key2
        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, key2, rec.data);
        // call modify method from Tree class for key1 and TempR
        t1.modify(key1, TempR);
        // delete rec of key2
        t2.delete(rec.key2);
        // replace it with the new rec
        t2.add(key2, TempR);

    }

    // "changes the first key of rec associated with key1 to be key2"
    void change2(K2 key2, K1 key1) {
        TwoKey<K1, K2, D> rec = t2.find(key2);

        TwoKey<K1, K2, D> TempR = new TwoKey<K1, K2, D>(key1, key2, rec.data);
        t2.modify(key2, TempR);
        t1.delete(rec.key1);
        t1.add(key1, TempR);

    }

    // "returns the list(key,data) in order by key1 as a string"
    String list1() {
        // call toString from Tree for t1
        return t1.toString();
    }

    // "returns the list (key,data) in order by key2 as a string"
    String list2() {
        // call toString from Treefor t2
        return t2.toString();
    }

    public String toString() {
        return data + "";
    }
}
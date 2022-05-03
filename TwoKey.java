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

    // "inserts a record to the database"
    void insert(K1 key1, K2 key2, D data) {

        TwoKey<K1, K2, D> record = new TwoKey<K1, K2, D>(key1, key2, data);

        // add the record of key1 into t1
        t1.add(key1, record);
        // add the record of key2 into t2
        t2.add(key2, record);
    }

    // "finds and returns the data associated with key1"
    D search1(K1 key1) {

        // call find method from Tree class for:
        // create record constructor for t1 object and key1 parameter from TwoKey
        TwoKey<K1, K2, D> record = t1.find(key1);
        if (record == null) // if the record is empty
            return null; // null return
        else
            return record.data; // if not, return the record of the data
    }

    // "finds and returns the data associated with key2"
    D search2(K2 key2) {

        // call find method from Tree class for:
        // create record constructor for t2 object and key2 parameter from TwoKey
        TwoKey<K1, K2, D> record = t2.find(key2);
        if (record == null) // if the record is empty
            return null; // null return
        else
            return record.data; // if not, return the record of the data
    }

    // "modifies the record associated with key 1 to contain the new data"
    void modify1(K1 key1, D data) {

        // find key of first tree
        // create record constructor for t1 object and key1 parameter from TwoKey
        TwoKey<K1, K2, D> record = t1.find(key1);

        // create newRecord to replace record.key2
        TwoKey<K1, K2, D> newRecord = new TwoKey<K1, K2, D>(key1, record.key2, data);
        // call modify on key 1 and the new record
        t1.modify(key1, newRecord);
        // then call modify for the new record of key2
        t2.modify(newRecord.key2, newRecord);

    }

    // "modifies the record associated with key2 to contain the new data"
    void modify2(K2 key2, D data) {

        // find key of second tree
        // create record constructor for t2 object and key2 parameter from TwoKey
        TwoKey<K1, K2, D> record = t2.find(key2);

        // create newRecord to replace record of key2
        TwoKey<K1, K2, D> newRecord = new TwoKey<K1, K2, D>(record.key1, key2, data);
        // call modify on key2 and the new record
        t2.modify(key2, newRecord);
        // then call modify for the new record
        t1.modify(newRecord.key1, newRecord);

    }

    // "deletes the record associated with key1 to contain the new data"
    void delete1(K1 key1) {
        TwoKey<K1, K2, D> record = t1.find(key1);

        // find key of second tree
        // create record constructor for t2 object and key2 parameter from TwoKey
        // TwoKey<K1, K2, D> record = t2.find(key2);

        t1.delete(record.key1);

    }

    // "deletes the record associated with key2 to contain the new data"
    void delete2(K2 key2) {

        // find key of second tree
        // create record constructor for t2 object and key2 parameter from TwoKey
        // TwoKey<K1, K2, D> record = t2.find(key2);

        t2.delete(this.key2);

    }

    // "changes the second key of the record associated with key1 to be key2"
    void change1(K1 key1, K2 key2) {
        TwoKey<K1, K2, D> record = t1.find(key1);

        // create newRecord constructor to replace record of key2
        TwoKey<K1, K2, D> newRecord = new TwoKey<K1, K2, D>(key1, key2, record.data);
        // call modify method from Tree class for key1 and newRecord
        t1.modify(key1, newRecord);
        // delete record of key2
        t2.delete(record.key2);
        // replace it with the new record
        t2.add(key2, newRecord);

    }

    // "changes the first key of record associated with key1 to be key2"
    void change2(K2 key2, K1 key1) {
        TwoKey<K1, K2, D> record = t2.find(key2);

        TwoKey<K1, K2, D> newRecord = new TwoKey<K1, K2, D>(key1, key2, record.data);
        t2.modify(key2, newRecord);
        t1.delete(record.key1);
        t1.add(key1, newRecord);

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
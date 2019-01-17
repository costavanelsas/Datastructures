package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Costa van Elsas 500782594, Jeroen Hoff 500731798
 * IS205
 */
public class LinearProbingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private int collisions;
    private int tableSize;
    private String[] keys;
//    private List<Player> values;
    private Player[] values;


    public LinearProbingMultiValueSymbolTable(int capacity) {

        tableSize = capacity;
        collisions = 0;
        keys = new String[tableSize];
//        values = Arrays.asList(new Player[capacity]);
        values = new Player[capacity];
    }

    @Override
    public void put(String key, Player value) {

        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (value == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (collisions >= tableSize / 2) resize(2 * tableSize);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % tableSize) {
            if (keys[i].equals(key) && keys[i] == null) {
//                values.set(i , value);
                values[i] = value;
                return;
            }
            collisions++;
        }
        //System.out.println("Collisions Linear Probing: " + getCollisions());
        keys[i] = key;
//        values.set(i, value);
        values[i] = value;

    }

    @Override
    public List<Player> get(String key) {
        List<Player> getPlayers = new ArrayList<>();
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % tableSize) {
            if (keys[i].equals(key))
//                getPlayers.add(values.get(i));
            getPlayers.add(values[i]);
//            System.out.println(values.get(i).getFirstName() + " " + values.get(i).getLastName());
            System.out.println(values[i].getFirstName() + " " + values[i].getLastName());
        }
        return getPlayers;
    }

    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % tableSize;
        }

        // delete key and associated value
        keys[i] = null;
//        values.set(i, null);
        values[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % tableSize;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            String   keyToRehash = keys[i];
//            Player valToRehash = values.get(i);
            Player valToRehash = values[i];
            keys[i] = null;
//            values.set(i, null);
            values[i] = null;
            collisions--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % tableSize;
        }

        collisions--;

        // halves size of array if it's 12.5% full or less
        if (collisions > 0 && collisions <= tableSize/8) resize(tableSize/2);

        assert check();
    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {

        // check that hash table is at most 50% full
        if (tableSize < 2 * collisions) {
            System.err.println("Hash table size m = " + tableSize + "; array size n = " + collisions);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < tableSize; i++) {
            if (keys[i] == null) continue;
//            else if (get(keys[i]) != values.get(i)) {
            else if (get(keys[i]) != values[i]) {
//                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + values.get(i));
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + values[i]);
                return false;
            }
        }
        return true;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingMultiValueSymbolTable temp = new LinearProbingMultiValueSymbolTable(capacity);
        for (int i = 0; i < tableSize; i++) {
            if (keys[i] != null) {
//                temp.put(keys[i], values.get(i));
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        tableSize    = temp.tableSize;
    }

    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    @Override
    public int getCollisions() {
        return collisions;
    }
}
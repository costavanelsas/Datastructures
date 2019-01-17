package nl.hva.ict.ds.util;

import java.util.List;

/**
 * This special symbol table is capable of storing multiple values under the same key. When
 * retrieving from this symbol table a list of values is returned.
 * @param <K> the type of the key.
 * @param <V> the type of the values.
 */
public interface MultiValueSymbolTable<K, V> {

    /**
     * Adds a value to the list for the given key.
     * If the value is null the list will be emptied.
     * @param key the key to use.
     * @param value the value to be stored.
     */
    void put(K key, V value);

    /**
     * Returns a list of all the values that have been added using the same key.
     * @param key the key for which the values must be returned.
     * @return the list of values that have been added using the given key.
     */
    List<V> get(K key);

    int getCollisions();
}

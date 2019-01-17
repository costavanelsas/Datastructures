package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Costa van Elsas 500782594, Jeroen Hoff 500731798
 * IS205
 */
public class DoubleHashingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private int collisions;
    private int tableSize;
    private Player[] values;
    private int primeSize;

    public DoubleHashingMultiValueSymbolTable(int arraySize) {
        tableSize = arraySize;
        collisions = 0;
        values = new Player[arraySize];
        primeSize = getPrime();
    }

    @Override
    public void put(String key, Player value) {

        int firstHash = hash1(key);
        int secondHash = hash2(key);

        while(values[firstHash] != null) {
            firstHash += secondHash;
            firstHash %= tableSize;
            collisions++;
        }

        values[firstHash] = value;

        //System.out.println("Collisions Double Hashing: " + getCollisions());
    }

    @Override
    public List<Player> get(String key) {
        List<Player> getPlayers = new ArrayList<>();

        int firstHash = hash1(key);
        int secondHash = hash2(key);

        int index;

        for (int i = 1; i < tableSize + 1; i++) {
            index = (firstHash + i * secondHash) % tableSize;
            if (values[index] != null) {
                if ((values[index].getFirstName().equals(key))) {
                    getPlayers.add(values[index]);
                } else if (values[index].getLastName().equals(key)) {
                    getPlayers.add(values[index]);
                } else if ((values[index].getFirstName() + values[index].getLastName()).equals(key)) {
                    getPlayers.add(values[index]);
                }
            }
        }

        return getPlayers;
    }

    public List<Player> remove(String key) {
        List<Player> getPlayers = new ArrayList<>();
        if (key == null) throw new IllegalArgumentException("argument to delete is null");

        int firstHash = hash1(key);
        int secondHash = hash2(key);

        int index;

        for (int i = 1; i < tableSize + 1; i++) {
            index = (firstHash + i * secondHash) % tableSize;
            if (values[index] != null) {
                if ((values[index].getFirstName().equals(key))) {
                    getPlayers.remove(values[index]);
                } else if (values[index].getLastName().equals(key)) {
                    getPlayers.remove(values[index]);
                } else if ((values[index].getFirstName() + values[index].getLastName()).equals(key)) {
                    getPlayers.remove(values[index]);
                }
            }
        }
        return getPlayers;
    }

    public int getCollisions() {
        return collisions;
    }

    //function to get a prime number smaller than the table size for hash2
    public int getPrime()
    {
        for (int i = tableSize - 1; i >= 1; i--)
        {
            int factor = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    factor++;
            if (factor == 0)
                return i;
        }

        //Return a prime number
        return 3;
    }

    private int hash1(String key) {
        int hashVal = key.hashCode();

        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;

        return hashVal;
    }

    private int hash2(String key) {
        int hashVal = key.hashCode();

        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;

        return primeSize - hashVal % primeSize;
    }
}

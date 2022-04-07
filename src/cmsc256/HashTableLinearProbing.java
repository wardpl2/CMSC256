package cmsc256;

import java.util.Iterator;

public class HashTableLinearProbing<K, V> extends HashTableOpenAddressing<K, V> {

    public int linearProbe(int index, K keyIn) {
        boolean found = false;
        int removedStateIndex = -1; // Index of first removed location

        if(table[index] == null) {  // The hash index is available
            return index;
        }

        while (!found && table[index] != null) {
            if (table[index].isIn()) {
                if (keyIn.equals(table[index].getKey())) {
                    found = true;       // Key found
                } else {                // Follow probe sequence
                    index = (index + 1) % table.length;
                }
            } else {                              // Skip entries that were removed
                // Save index of first location in removed state
                if (removedStateIndex == -1) {
                    removedStateIndex = index;
                }
                index = (index + 1) % table.length;
            }
        }

        if (found || (removedStateIndex == -1) ) {
            return index;             // Index of either key or null
        } else {
            return removedStateIndex; // Index of an available location
        }
    }

    public V put(K keyIn, V valueIn) {
        if (keyIn == null || valueIn == null) {
            throw new IllegalArgumentException("Neither key nor value can be null.");
        }

        V oldValue = null;
        Entry<K, V> entry = new Entry<>(keyIn, valueIn);
        int index = super.getHashIndex(keyIn);

        index = linearProbe(index, keyIn);

        if (table[index] == null || table[index].isRemoved()) {
            table[index] = entry;
            numEntries++;
        } else {
            oldValue = table[index].getValue();
            table[index] = entry;
        }

        if (isFull()) {
            enlargeHashTable();
        }

        return oldValue;
    }


    public V getValue(K keyIn) {
        // TODO – see bullets below
        int index = super.getHashIndex(keyIn);
        index = linearProbe(index, keyIn);

        Entry <K, V> item = table[index];

        if ((item  != null) && item.isIn())
            return item.getValue();

        return null;
    }

    public V remove(K keyIn) {
        V removedValue = null;
        // calculate the initial index
        int index = super.getHashIndex(keyIn);
        // find the location of the key using linear probing
        // if Key found; flag entry as removed and return its value
        index = linearProbe(index, keyIn);
        if (!(table[index] == null || table[index].isRemoved())) {
            removedValue = table[index].getValue();
            table[index].setToRemoved();
            numEntries--;
        }
        // decrement the number of elments
        // else key not found; return null
        return removedValue;
    }

    public boolean contains(K key) {
        return getValue(key) != null;
    }



    public static void main(String[] args){
        HashTableLinearProbing<String, Integer> purchases = new HashTableLinearProbing<>();

        String[] names = {"Pax", "Eleven", "Angel", "Abigail", "Jack"};
        purchases.put(names[0], 654);
        purchases.put(names[1], 341);
        purchases.put(names[2], 70);
        purchases.put(names[3], 867);
        purchases.put(names[4], 5309);

        System.out.println("Contents with linear probing:\n" + purchases.toString());

        System.out.println("Replaced old value was " + purchases.put(names[1], 170));
        System.out.println("Contents after changing Eleven to 170:\n" + purchases.toString());

        System.out.println("Calling getValue() on Pax, Eleven, & Angel:");
        System.out.println("\tPax: " + purchases.getValue(names[0]));
        System.out.println("\tEleven: " + purchases.getValue(names[1]));
        System.out.println("\tAngel: " + purchases.getValue(names[2]));

        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n" + purchases);

        purchases.put("Gino", 348);
        System.out.println("Contents after adding Gino:\n" + purchases);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while(keyIter.hasNext())
            System.out.println("Key-" + keyIter.next() + " : Value-" + valueIter.next());
    }
}

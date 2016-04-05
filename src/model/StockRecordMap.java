package model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author Evan Phillips
 */
public class StockRecordMap<K, V> extends Observable implements IStockMap {

    private static final int DEFAULT_INITIAL_CAPACITY = 8192;
    private static final int MAXIMUM_CAPACITY = 1 << 15;
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    private int capacity;
    private float loadFactorThreshold;
    private int size = 0;
    LinkedList<IStockMap.Entry<K, V>>[] table;

    public StockRecordMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public StockRecordMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public StockRecordMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override
    public boolean containsKey(Object key) {
        if (get(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public java.util.Set<StockRecordMap.Entry<K, V>> entrySet() {
        java.util.Set<StockRecordMap.Entry<K, V>> set
                = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    set.add(entry);
                }
            }
        }

        return set;
    }

    @Override
    public V get(Object key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<K>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    set.add(entry.getKey());
                }
            }
        }

        return set;
    }

    @Override
    public V put(Object key, Object value) {
        if (get(key) != null) {
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.value = (V) value;
                    return oldValue;
                }
            }
        }

        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }

        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<Entry<K, V>>();
        }

        table[bucketIndex].add(new StockRecordMap.Entry<K, V>((K) key, (V) value));

        size++;

        return (V) value;
    }

    @Override
    public void remove(Object key) {
        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    set.add(entry.getValue());
                }
            }
        }

        return set;
    }

    ////////////////////////////////////////////////////////////////////////////
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        return capacity;
    }

    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        table = new LinkedList[capacity];
        size = 0;

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].size() > 0) {
                for (Entry<K, V> entry : table[i]) {
                    builder.append(entry);
                }
            }
        }

        builder.append("]");

        return builder.toString();
    }
}

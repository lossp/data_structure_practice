package chapter4.algs4.HashMap;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class CopyHashMap<K, V> extends AbstractMap<K, V> implements Cloneable, Serializable {

    private static final int INIT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    // 考虑整个Map上限值
    // HashMap基于哪个数据结构进行创建的？ 还是基于Map?

    //定义loadFactor和threshold
    private final float loadFactor;
    private final int threshold;
    private Node<K, V>[] table;


    public CopyHashMap() { this(INIT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public CopyHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) throw new IllegalArgumentException("init capacity cannot be negative");
        this.loadFactor = loadFactor;
        this.threshold = tableForSize(initCapacity);
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int tableForSize(int capacity) {
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    private V putVal(int hash, K key, V value, boolean ifOnlyAbsent, boolean evict) {
        Node<K, V>[] tab;
        int n, i;
        Node<K, V> p;
        // 首先判断table是否为空，如果为空的话，先进行相应的扩容
        // 为什么要独立获取一个tab值，而非直接使用table
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        // 如果此处的位置为空，即不存在对应的Hash值，则直接进行插入操作, 减一的原因是因为数组下标从0开始
        else if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = new Node<>(hash, key, value, null);
        }
        // 即发生Hash Collision
        else {
            Node<K, V> e; K k;
//            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) {
//                e = p;
//            } else if (p instanceof TreeNode) {
//                // TODO
//            } else {
//                for (int binCount = 0 ;; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        // TREEIFY_THRESHOLD是指？
//                        if (binCount >= TREEIFY_THRESHOLD - 1)
//                            // 这个函数作用是？
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash && )
//                }
//            }
        }
        return null;
    }

    public Node<K, V>[] resize() {
        return null;
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    static final int hash(Object object) {
        int h;
        return object == null ? 0 : (h = object.hashCode()) ^ (h >>> 16);
    }

    static class Node<K, V> implements Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;
        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        @Override
        public K getKey() { return key; }

        @Override
        public V getValue() { return value; }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Entry<?,?> e = (Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }

    }


}

import java.util.LinkedList;

class MyMapNode<K, V> {
    K key;
    V value;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class MyHashTable<K, V> {

    private LinkedList<MyMapNode<K, V>>[] buckets;

    public MyHashTable(int size) {
        buckets = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void add(K key, V value) {
        int index = getBucketIndex(key);

        for (MyMapNode<K, V> node : buckets[index]) {
            if (node.key.equals(key)) {
                Integer count = (Integer) node.value;
                node.value = (V) Integer.valueOf(count + 1);
                return;
            }
        }

        buckets[index].add(new MyMapNode<>(key, value));
    }

    public void print() {
        for (LinkedList<MyMapNode<K, V>> bucket : buckets) {
            for (MyMapNode<K, V> node : bucket) {
                System.out.println(node.key + " : " + node.value);
            }
        }
    }
}

public class WordFrequency {
    public static void main(String[] args) {

        String sentence = "To be or not to be";

        MyHashTable<String, Integer> hashTable = new MyHashTable<>(10);

        String[] words = sentence.toLowerCase().split(" ");

        for (String word : words) {
            hashTable.add(word, 1);
        }

        hashTable.print();
    }
}
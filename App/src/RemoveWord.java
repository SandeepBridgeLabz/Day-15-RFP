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

    public void remove(K key) {

        int index = getBucketIndex(key);

        MyMapNode<K, V> removeNode = null;

        for (MyMapNode<K, V> node : buckets[index]) {

            if (node.key.equals(key)) {
                removeNode = node;
                break;
            }
        }

        if (removeNode != null) {
            buckets[index].remove(removeNode);
        }
    }

    public void print() {
        for (LinkedList<MyMapNode<K, V>> bucket : buckets) {
            for (MyMapNode<K, V> node : bucket) {
                System.out.println(node.key + " : " + node.value);
            }
        }
    }
}

public class RemoveWord {

    public static void main(String[] args) {

        String paragraph = "Paranoids are not paranoid because they are paranoid " +
                "but because they keep putting themselves deliberately into " +
                "paranoid avoidable situations";

        MyHashTable<String, Integer> hashTable = new MyHashTable<>(20);

        String[] words = paragraph.toLowerCase().split(" ");

        for (String word : words) {
            hashTable.add(word, 1);
        }

        System.out.println("Before Removal:");
        hashTable.print();

        hashTable.remove("avoidable");

        System.out.println("\nAfter Removal:");
        hashTable.print();
    }
}
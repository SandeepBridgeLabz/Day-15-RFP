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
class MyBinaryNode<K extends Comparable<K>> {

    K key;
    MyBinaryNode<K> left;
    MyBinaryNode<K> right;

    public MyBinaryNode(K key) {
        this.key = key;
    }
}

class BinarySearchTree<K extends Comparable<K>> {

    private MyBinaryNode<K> root;

    public void add(K key) {
        root = addRecursive(root, key);
    }

    private MyBinaryNode<K> addRecursive(MyBinaryNode<K> current, K key) {

        if (current == null) {
            return new MyBinaryNode<>(key);
        }

        if (key.compareTo(current.key) < 0) {
            current.left = addRecursive(current.left, key);
        } else if (key.compareTo(current.key) > 0) {
            current.right = addRecursive(current.right, key);
        }

        return current;
    }

    public void inorder() {
        inorderTraversal(root);
    }

    private void inorderTraversal(MyBinaryNode<K> node) {

        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }
}

public class BSTDemo {

    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(56);
        bst.add(30);
        bst.add(70);

        bst.inorder();
    }
}


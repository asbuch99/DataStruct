import java.util.Random;

public class Tree<K extends Comparable<K>, D> {

    private class Node {
        public K key;
        public D data;
        public Node left, right;

        public Node(K k, D d, Node l, Node r) {
            key = k;
            data = d;
            left = l;
            right = r;
        }

        public Node(K k, D d) {
            this(k, d, null, null);
        }

        public String toString() {
            return "(" + key + ","
                    + data + ")";
        }
    }

    private Node root;
    private static Random random = new Random();

    public Tree() {
        root = null;
    }

    public Tree(K[] keys, D[] data) {
        root = buildTree(keys, data, 0, keys.length - 1);
    }

    public Node buildTree(K[] keys, D[] data, int low, int high) {
        // assumes keys in order.
        // build balanced tree for keys[low..high] and data[low..high]
        if (high < low)
            return null;
        int mid = (low + high) / 2;
        return new Node(keys[mid], data[mid],
                buildTree(keys, data, low, mid - 1),
                buildTree(keys, data, mid + 1, high));
    }

    private D find(K key, Node x) {
        if (x == null)
            return null;
        int c = key.compareTo(x.key);
        if (c == 0)
            return x.data;
        else if (c < 0)
            return find(key, x.left);
        else // c > 0
            return find(key, x.right);
    }

    public D find(K key) {
        return find(key, root);
    }

    private Node add(K key, D data, Node root) {
        // returns the tree with the added record.
        if (root == null)
            return new Node(key, data);
        int c = key.compareTo(root.key);
        if (c == 0) {
            System.err.println("Error: duplicate key: " + key);
            System.exit(1);
            return null;
        } else if (c < 0) {
            root.left = add(key, data, root.left);
            return root;
        } else { // c > 0
            root.right = add(key, data, root.right);
            return root;
        }
    }

    public void add(K key, D data) {
        root = add(key, data, root);
    }

    private void modify(K key, D data, Node root) {
        if (root == null) {
            System.err.println("Error: key not found: " + key);
            System.exit(1);
        }
        int c = key.compareTo(root.key);
        if (c == 0)
            root.data = data;
        else if (c < 0)
            modify(key, data, root.left);
        else // c > 0
            modify(key, data, root.right);
    }

    public void modify(K key, D data) {
        modify(key, data, root);
    }

    private String toString(Node root) {
        if (root == null)
            return "";
        return toString(root.left)
                + root
                + toString(root.right);
    }

    public String toString() {
        return toString(root);
    }

    private String print(Node root, int indent) {
        if (root == null)
            return "";
        return print(root.right, indent + 1)
                + (indent == 0 ? "" : String.format("%" + indent + "s", "")) + root + "\n"
                + print(root.left, indent + 1);
    }

    public String print() {
        return print(root, 0);
    }

    private Node findLeftmost(Node root) {
        // Assumes root != null.
        return root.left == null
                ? root
                : findLeftmost(root.left);
    }

    private Node removeLeftmost(Node root) {
        if (root.left == null)
            return root.right;
        else {
            root.left = removeLeftmost(root.left);
            return root;
        }
    }

    private Node delete(K key, Node root) {
        if (root == null) {
            System.err.println("Error: key not found");
            System.exit(1);
            return null;
        }
        int c = key.compareTo(root.key);
        if (c == 0) {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node t = root;
                root = findLeftmost(root.right);
                root.right = removeLeftmost(t.right);
                root.left = t.left;
                return root;
            }
        } else if (c < 0) {
            root.left = delete(key, root.left);
            return root;
        } else { // c > 0
            root.right = delete(key, root.right);
            return root;
        }
    }

    public void delete(K key) {
        root = delete(key, root);
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++)
            swap(a, i, random.nextInt(i + 1));
    }

    private int height(Node root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public int height() {
        return height(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    public int size() {
        return size(root);
    }

    public static void main(String[] args) {
        String[] keys = new String[10];
        Integer[] data = new Integer[10];
        for (int i = 0; i < 10; i++) {
            keys[i] = i + "";
            data[i] = i;
        }
        Tree<String, Integer> tree = new Tree<>(keys, data);
        System.out.println(tree);
        System.out.println(tree.print());
        System.out.println("height = " + tree.height());
        System.out.println("size = " + tree.size());
        Tree<String, Integer> tree2 = new Tree<>();
        for (int i = 0; i < 10; i++)
            tree2.add(i + "", i);
        System.out.println(tree2);
        System.out.println(tree2.print());
        System.out.println("height = " + tree2.height());
        System.out.println("size = " + tree2.size());
    }
}
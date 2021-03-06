package chapter4.algs4.binary_search_tree;


/**
 * 3.2.8 Write a static method optCompares()that takes an integer argument N and computes the number of compares
 * required by a random search hit in an optimal (perfectly balanced) BST,
 * where all the null links are on the same level if the number of links is a power of 2 or on one of two levels otherwise.
 */
public class BinaryTreeOptComparesImp<Key extends Comparable<Key>, Value> {
    private Node root;
    public BinaryTreeOptComparesImp() {
        this.root = null;
    }

    private class Node {
        Node left;
        Node right;
        Key key;
        Value value;
        int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node.size;
    }

    public int optCompares(Key key) {
        return avgCompares(root, key);
    }

    private int avgCompares(Node node, Key key) {
        int times = 0;
        if (node == null) return times;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            times = avgCompares(node.right, key);
        }
        if (cmp < 0) {
            times = avgCompares(node.left, key);
        }
        return ++times;
    }

    public void insert(Key key, Value value) {
        root = insert(root, new Node(key, value));
    }

    private Node insert(Node node, Node insertNode) {
        if (node == null) {
            insertNode.size++;
            return insertNode;
        } else {
            int cmp = insertNode.key.compareTo(node.key);
            if (cmp < 0) { node.left = insert(node.left, insertNode); }
            if (cmp > 0) { node.right = insert(node.right, insertNode); }
            node.size++;
        }
        return node;
    }

    public void printTreeMap() {
        StringBuilder stringBuilder = new StringBuilder();
        printTreeMap(stringBuilder,"", "", root);
        System.out.println(stringBuilder.toString());
    }

    private void printTreeMap(StringBuilder sb,String padding, String pointer, Node node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("|  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";

            printTreeMap(sb, paddingForBoth, pointerForLeft, node.left);
            printTreeMap(sb, paddingForBoth, pointerForRight, node.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeOptComparesImp binaryTreeOptComparesImp = new BinaryTreeOptComparesImp();
        binaryTreeOptComparesImp.insert(1, "firstOne");
        binaryTreeOptComparesImp.insert(2, "secondOne");
        binaryTreeOptComparesImp.insert(3, "thirdOne");
        binaryTreeOptComparesImp.insert(0, "the shit");
        binaryTreeOptComparesImp.printTreeMap();
        System.out.println("the size = " + binaryTreeOptComparesImp.size());
        System.out.println("the hits = " + binaryTreeOptComparesImp.optCompares(2));
    }

}

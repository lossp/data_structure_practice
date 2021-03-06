package chapter4.algs4.binary_search_tree;


import java.math.BigDecimal;

/**
 * 3.2.7 Add to BST a recursive method avgCompares() that computes the average number of compares
 * required by a random search hit in a given BST (the internal path length of the tree divided by its size, plus one).
 * Develop two implementations: a recursive method (which takes linear time and space proportional to the height),
 * and a method like size() that adds a field to each node in the tree (and takes linear space and constant time per query).
 */
public class BinaryTreeAvgComparesImp<Key extends Comparable<Key>, Value> {
    private Node root;
    public BinaryTreeAvgComparesImp() {
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

    public BigDecimal avgCompares() {
        BigDecimal sum = new BigDecimal(totalCompares());
        BigDecimal sizeNumber = new BigDecimal(size());
        return sum.divide(sizeNumber);
    }


    public int totalCompares() {
        return totalCompares(root, 1);
    }

    private int totalCompares(Node node, int depth) {
        if (node == null) return 0;
        return totalCompares(node.right, depth + 1) + totalCompares(node.left, depth + 1) + depth;
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
        BinaryTreeAvgComparesImp binaryTreeAvgComparesImp = new BinaryTreeAvgComparesImp();
        binaryTreeAvgComparesImp.insert(1, "firstOne");
        binaryTreeAvgComparesImp.insert(2, "secondOne");
        binaryTreeAvgComparesImp.insert(3, "thirdOne");
        binaryTreeAvgComparesImp.insert(0, "the shit");
        binaryTreeAvgComparesImp.printTreeMap();
        System.out.println("the size = " + binaryTreeAvgComparesImp.size());
        System.out.println("the hits = " + binaryTreeAvgComparesImp.avgCompares());
    }

}

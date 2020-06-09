package chapter4.algs4.binary_search_tree;

import java.util.NoSuchElementException;

/**
 * 3.2.29 Binary tree check. Write a recursive method isBinaryTree() that takes
 * a Node as argument and returns true if the subtree count field N is consistent
 * in the data structure rooted at that node, false otherwise.
 * Note : This check also ensures that the data structure has no cycles and is therefore a binary tree (!).
 */
public class BinaryTreeCheckImp<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value value;
        int size;
        Node left;
        Node right;
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, new Node(key, value));
    }

    public int size(Node node) {
        return node.size;
    }

    private Node put(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }
        int cmp = node.key.compareTo(root.key);
        if (cmp < 0) { root.left = put(root.left, node); }
        if (cmp > 0) { root.right = put(root.right, node); }
        if (cmp == 0) { root.value = node.value; }
        root.size++;
        return root;
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

    public boolean isBinary(Key key) {
        Node node = getNode(root, key);
        int number = isBinary(node, 0);
        System.out.println("the number = " + number);
        System.out.println("the size node = " + size(node));
        return size(node) == number + 1;
    }

    private Node getNode(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return getNode(root.right, key);
        if (cmp < 0) return getNode(root.left, key);
        return root;
    }

    private int isBinary(Node node, int number) {
        if (node == null) throw new NoSuchElementException("节点不存在");
        if (node.left != null) {
            number = isBinary(node.left, ++number);
        }
        if (node.right != null) {
            number = isBinary(node.right, ++number);
        }
        return number;
    }

    public static void main(String[] args) {
        BinaryTreeCheckImp binaryTreeCheckImp = new BinaryTreeCheckImp();
        binaryTreeCheckImp.put(5, "fifth");
        binaryTreeCheckImp.put(4, "forth");
        binaryTreeCheckImp.put(6, "sixth");
        binaryTreeCheckImp.put(7, "seventh");
        binaryTreeCheckImp.printTreeMap();

        System.out.println(binaryTreeCheckImp.isBinary(4));
    }


}

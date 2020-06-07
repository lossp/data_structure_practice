package chapter4.algs4.binary_search_tree;


import javax.annotation.processing.SupportedSourceVersion;

/**
 * 3.2.25
 * Perfect balance. Write a program that inserts a set of keys into an initially empty BST
 * such that the tree produced is equivalent to binary search, in the sense that the sequence of
 * compares done in the search for any key in the BST is the same as the sequence of compares used
 * by binary search for the same set of keys.
 *
 * tips:
 * 在没有涉及到相关的二叉树左右翻转情况的时候，需要首先定义一个数组(已经排好序了)，取中间部分的值，这样一来，最坏情况也就是
 * 两个子树的高度差1，可以构造出完美平衡的二叉树
 */
public class PerfectBalance<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }


    public void insert(Key key, Value value) {
        root = insert(root, new Node(key, value));
    }

    private Node insert(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }
        int cmp = node.key.compareTo(root.key);
        if (cmp > 0) root.right = insert(root.right, node);
        if (cmp < 0) root.left = insert(root.left, node);
        node.size++;
        return root;
    }

    public void printTreeMap() {
        StringBuilder stringBuilder = new StringBuilder();
        printTreeMap(stringBuilder,"", "", root);
        System.out.println(stringBuilder.toString());
    }

    public Key select(Key key) {
        return select(root, key, 0);
    }

    private Key select(Node root, Key key, int times) {
        if (root == null) return null;
        times++;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return select(root.left, key, times);
        }

        if (cmp > 0) {
            return select(root.right, key, times);
        }
        if (cmp == 0){
            System.out.println("找到了, 花费次数为: " + times);
            return root.key;
        }
        return null;
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
        PerfectBalance perfectBalance = new PerfectBalance();
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        int middleNumber = array.length % 2 == 0 ? array.length / 2 : (array.length - 1) / 2;
        perfectBalance.insert(middleNumber, array[middleNumber]);
        // 遍历数组➡右半部分
        for (int i = middleNumber + 1; i < array.length; i++) {
            perfectBalance.insert(i, array[i]);
        }

        // 遍历数组左半部分
        for (int i = middleNumber - 1; i > -1; i--) {
            perfectBalance.insert(i, array[i]);
        }

        perfectBalance.printTreeMap();
        perfectBalance.select(9);
    }
}

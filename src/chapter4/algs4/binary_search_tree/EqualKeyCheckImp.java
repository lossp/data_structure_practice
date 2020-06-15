package chapter4.algs4.binary_search_tree;

import java.util.HashSet;

/**
 * 3.2.31 Equal key check. Write a method hasNoDuplicates()
 * that takes a Node as argument and returns true if there are
 * no equal keys in the binary tree rooted at the argument node, false otherwise.
 * Assume that the test of the previous exercise has passed.
 *
 *
 * Question: 1.已经完美平衡的二叉树，为何会出现两个相同的Key值？
 *           2.相同的Key值，如何分配在子树中？
 *
 *           只能指定root.left = 2, root.left.left = 2类似这种形式才能模拟该问题
 */
public class EqualKeyCheckImp<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }

        public boolean sameKey(Node node) {
            return this.key.equals(node.key);
        }
    }

    public void put(Key key, Value value) {
        root = put(root, new Node(key, value));
    }

    private Node put(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }
        int cmp = node.key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, node);
        if (cmp > 0) root.right = put(root.right, node);
        root.size++;
        return root;
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
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


    // 创建错误的二叉树
    public void fakePut(Key key, Value value) {
        root = fakePut(root, new Node(key, value));
    }

    private Node fakePut(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }

        int cmp = node.key.compareTo(root.key);
        if (cmp > 0) {
            root.right = fakePut(root.right, node);
        }
        if (cmp < 0) {
            root.left = fakePut(root.left, node);
        }
        if (cmp == 0) {
            root.left = node;
        }
        root.size++;
        return root;

    }

    public boolean hasNoDuplicates(Node node) {
        // 用hashSet来作为标尺，检查唯一性，这样就不用每次去遍历整个树
        HashSet<Key> hashSet = new HashSet<>();
        return hasNoDuplicates(root, hashSet);
    }

    private boolean hasNoDuplicates(Node root, HashSet set) {
        if (root == null) return true;
        System.out.println("Node.key = " + root.key);
        if (set.contains(root.key)) {
            System.out.println("此时的 root.key = " + root.key);
            return false;
        }
        set.add(root.key);
        if (!hasNoDuplicates(root.left, set)) return false;
        if (!hasNoDuplicates(root.right, set)) return false;
        return true;
    }

    public static void main(String[] args) {
        EqualKeyCheckImp equalKeyCheckImp = new EqualKeyCheckImp();
        equalKeyCheckImp.put(1, "first");
        equalKeyCheckImp.fakePut(1, "fakeFirst");
        equalKeyCheckImp.fakePut(2, "second");
        equalKeyCheckImp.printTreeMap();
        System.out.println("没有重复的: " + equalKeyCheckImp.hasNoDuplicates(equalKeyCheckImp.root));

    }

}

package chapter4.algs4.binary_search_tree;


/**
 * 3.2.13 Give non-recursive implementations of get() and put() for BST.
 *
 * 3.2.14 Give non-recursive implementations of min(), max(), floor(), ceiling(),
 * rank(), and select().
 */
public class NonerecursiveImp<Key extends Comparable<Key>, Value> {
    private Node root;

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

    public Value get(Key key) {
        Node tmp = root;
        while (tmp != null) {
            int cmp = key.compareTo(tmp.key);
            if (cmp == 0) return tmp.value;
            else if (cmp < 0) tmp = tmp.left;
            else tmp = tmp.right;
        }
        return null;
    }

    public Node put(Key key, Value value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
            return newNode;
        } else {
            Node tmp = root;
            while (tmp != null) {
                int cmp = key.compareTo(tmp.key);
                // 大于0，右边寻找
                if (cmp > 0) {
                    if (tmp.right == null) return tmp.right = newNode;
                    tmp = tmp.right;
                }
                // 小于0, 左边寻找
                else if (cmp < 0) {
                    if (tmp.left == null) return tmp.left = newNode;
                    tmp = tmp.left;
                }
                // 等于0，更新该节点
                else {
                    tmp.value = value;
                    return tmp;
                }

            }
            tmp = newNode;
            return newNode;
        }
    }

    public Key min() {
        Node tmp = root;
        while (tmp != null) {
            if (tmp.left == null) return tmp.key;
            tmp = tmp.left;
        }
        return null;
    }

    public Key max() {
        Node tmp = root;
        while (tmp != null) {
            if (tmp.right == null) return tmp.key;
            tmp = tmp.right;
        }
        return null;
    }

    public Key floor(Key key) {
        Node tmp = root;
        while (tmp != null) {
            int cmp = key.compareTo(tmp.key);
            if (cmp == 0) return tmp.key;
            if (cmp < 0) {
                if (tmp.left == null) return tmp.key;
                tmp = tmp.left;
            }
            if (cmp > 0) {
                if (tmp.right == null) return tmp.key;
                tmp = tmp.right;
            }

        }
        return null;
    }

    public Key ceiling(Key key) {
        Node tmp = root;
        while (tmp != null) {
            int cmp = key.compareTo(tmp.key);
            if (cmp == 0) return tmp.key;
            if (cmp > 0) {
                if (tmp.left == null) return tmp.key;
                tmp = tmp.left;
            }
            if (cmp < 0) {
                if (tmp.right == null) return tmp.key;
                tmp = tmp.right;
            }
        }
        return null;
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
        NonerecursiveImp nonerecursiveImp = new NonerecursiveImp();
        nonerecursiveImp.put(2, "second");
//        nonerecursiveImp.put(3, "third");
        nonerecursiveImp.put(1, "first");
        nonerecursiveImp.put(4, "forth");
        nonerecursiveImp.put(5, "fifth");
        nonerecursiveImp.put(0, "zero");
        nonerecursiveImp.put(2, "第二个");
        System.out.println("最小的min() = " + nonerecursiveImp.min());
        System.out.println("最大的max() = " + nonerecursiveImp.max());
        System.out.println("floor(3) = " + nonerecursiveImp.floor(3));
        System.out.println("ceiling(3) = " + nonerecursiveImp.ceiling(3));
        nonerecursiveImp.printTreeMap();
    }
}

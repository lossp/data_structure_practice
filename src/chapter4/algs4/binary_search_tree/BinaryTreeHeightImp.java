package chapter4.algs4.binary_search_tree;

/**
 * 3.2.6 Add to BST a method height() that computes the height of the tree.
 * Develop two implementations: a recursive method (which takes linear time and space proportional to the height),
 * and a method like size() that adds a field to each node in the tree (and takes linear space and constant time per query).
 *
 */
public class BinaryTreeHeightImp<Key extends Comparable<Key>, Value> {
    public Node root;

    public BinaryTreeHeightImp() { }

    private class Node {
        private Key key;
        private Value value;
        private Node leftLink;
        private Node rightLink;
        private int number;
        private int height;

        public Node(Key key, Value value, int number) {
            this.key = key;
            this.value = value;
            this.number = 0;
            this.height = 0;
        }
    }

    // 还未进行重复性判断，因此可以重复插入，但是导致总数+1的BUG，而整体树却没有变化
    public void insert(Key key, Value value) {
        root = insert(root, new Node(key, value, 0));
    }

    private Node insert(Node node, Node insertOne) {
        if (node == null) {
            insertOne.number++;
            return insertOne;
        }
        int cmp = insertOne.key.compareTo(node.key);
        if (cmp < 0) {
            node.leftLink = insert(node.leftLink, insertOne);
        } else if (cmp > 0) {
            node.rightLink = insert(node.rightLink, insertOne);
        }
        node.number++;
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
            String pointerForLeft = (node.rightLink != null) ? "├──" : "└──";

            printTreeMap(sb, paddingForBoth, pointerForLeft, node.leftLink);
            printTreeMap(sb, paddingForBoth, pointerForRight, node.rightLink);
        }
    }

    // 通过在每个节点维护一个number字段来获取整个树的大小，时间花费为常数级别
    public int getTotalByField() {
        return root.number;
    }


    // 通过递归方式，来遍历整个树来获取整个树大小，时间花费为O(n)
    public int getTotal() {
        return getTotal(root);
    }

    private int getTotal(Node node) {
        if (node == null) return 0;
        int leftNumber = getTotal(node.leftLink);
        int rightNumber = getTotal(node.rightLink);
        return leftNumber + rightNumber + 1;
    }

    public int getHeight() {
        return getHeight(root);
    }

    // 分别获取左子树和右子树的高度，取两者中较大值
    private int getHeight(Node node) {
        if (node == null) return -1;
        int leftHeight = getHeight(node.leftLink);
        int rightHeight = getHeight(node.rightLink);
        return (leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.number;
    }



    public static void main(String[] args) {
        BinaryTreeHeightImp binaryTreeHeightImp = new BinaryTreeHeightImp();
        binaryTreeHeightImp.insert(3, "third");
        binaryTreeHeightImp.insert(4, "forth");
        binaryTreeHeightImp.insert(2, "second");
        binaryTreeHeightImp.insert(1, "first");
        binaryTreeHeightImp.insert(5, "fifth");
        binaryTreeHeightImp.insert(7, "seven");
        binaryTreeHeightImp.insert(6, "sixth");
        binaryTreeHeightImp.insert(8, "eight");
        binaryTreeHeightImp.insert(9, "ninth");
        binaryTreeHeightImp.insert(0, "zero");
        binaryTreeHeightImp.printTreeMap();
        System.out.println(binaryTreeHeightImp.root);
        System.out.println("总数为: " + binaryTreeHeightImp.getTotal());
        System.out.println("总高度为: " + binaryTreeHeightImp.getHeight());
        System.out.println("总高度为(通过跟节点字段获取): " + binaryTreeHeightImp.getTotalByField());
    }



}

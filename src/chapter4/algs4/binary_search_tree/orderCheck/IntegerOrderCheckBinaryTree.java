package chapter4.algs4.binary_search_tree.orderCheck;

public class IntegerOrderCheckBinaryTree extends AbstractOrderCheckBinaryTree<Integer, String> {
    @Override
    public boolean isOrdered(Integer key, Integer max, Integer min) {
        Node node = this.get(key);
        return isOrdered(node, max, min);
    }

    private boolean isOrdered(Node node, Integer max, Integer min) {
        if (node == null) throw new IllegalArgumentException();
        Integer maxNodeKey = this.max(node.key);
        Integer minNodeKey = this.min(node.key);
        boolean leftEnd = min - minNodeKey <= 0;
        boolean rightEnd = max - maxNodeKey >= 0;
        return leftEnd && rightEnd;
    }

    public static void main(String[] args) {
        IntegerOrderCheckBinaryTree integerOrderCheckBinaryTree = new IntegerOrderCheckBinaryTree();
        integerOrderCheckBinaryTree.put(5, "fifth");
        integerOrderCheckBinaryTree.put(4, "forth");
        integerOrderCheckBinaryTree.put(6, "sixth");
        integerOrderCheckBinaryTree.put(3, "third");
        integerOrderCheckBinaryTree.put(7, "seventh");
        integerOrderCheckBinaryTree.printTreeMap();

        System.out.println("min = " + integerOrderCheckBinaryTree.min(4));
        System.out.println("max = " + integerOrderCheckBinaryTree.max(4));

        System.out.println("范围内 = " + integerOrderCheckBinaryTree.isOrdered(5, 3, 1));
        System.out.println("范围内 = " + integerOrderCheckBinaryTree.isOrdered(5, 7, 1));
    }


}

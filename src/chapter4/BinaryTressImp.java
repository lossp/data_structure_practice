package chapter4;

public class BinaryTressImp<Key extends Comparable<Key>, Value> {
    private TreeNode root;

    private class TreeNode {
        private Key key;
        private Value value;
        TreeNode leftLink;
        TreeNode rightLink;
        private int number;

        public TreeNode(Key key, Value value, int number) {
            this.key = key;
            this.value = value;
            this.number = number;
        }

        public int size() {
            return number;
        }


        private int size(TreeNode root) {
            if (root == null) return 0;
            return root.number;
        }
    }
    public Key floor(Key key) {
        TreeNode node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private TreeNode floor(TreeNode treeNode, Key key) {
        if (treeNode == null) return null;
        int cmp = key.compareTo(treeNode.key);
        if (cmp == 0) return treeNode;
        if (cmp < 0) return floor(treeNode.leftLink, key);
        TreeNode node = floor(treeNode.rightLink, key);
        if (node == null) return treeNode;
        return node;
    }

    public Key min() {
        return min(root).key;
    }

    private TreeNode min(TreeNode node) {
        if (node.leftLink == null) return node;
        return min(node.leftLink);
    }


    /**
     * Selection 方法，用于在整个树中找到相应位置的节点(换句话来说，就是寻找排在K位置的节点，转换为二维数组的思想)
     * tip:
     *     1.当前排在K位置的节点，从根节点(root)开始，判断K值和跟节点当前节点的子节点总数(包括自身节点)
     *     example:
     *              (S)
     *            /    \
     *          (E)    (X)
     *         /  \    / \
     *       (A)  (R)
     *      / \   / \
     *       (C) (H)
     *           / \
     *            (M)
     *            / \
     *
     *    a. 首先，当key为3时候，从跟节点开始，包括S在内，查看S节点的左边子树节点总数，有(E,A,R,C,H,M)7个节点，此时 6 > key，找寻左边子树，下一步从E开始
     *    b. E点的左子树的节点总数2，即t = 2，此时key = 3，需要找寻E点右边的子树，key - t - 1 = 3 - 2 - 1 = 0，此时重新赋值k = 0。相当于从E的右边子树开始寻找
     *    c. R点的左子树有两个节点，此时大于k = 0; 继续从该节点的左子树从下搜索
     *    d. H点的左子树节点数目为0，此时k = 0, 即可得到k = t。此时直接返回 H 节点
     */
    public Key select(int index) {
        return select(root, index).key;
    }

    private TreeNode select(TreeNode node, int index) {
        if (node == null) return null;
        int t = node.size();
        if (t > index) return select(node.leftLink, index);
        if (t < index) return select(node.rightLink, index - t - 1);
        return node;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    /**
     * Rank方法，用于寻找目标key值，查看在整个树中的排序值
     *
     *              (S)
     *            /    \
     *          (E)    (X)
     *         /  \    / \
     *       (A)  (R)
     *      / \   / \
     *       (C) (H)
     *           / \
     *            (M)
     *            / \
     *
     * tip:
     *     1.获取指定值的在树中的排位，前提3个点是，当key大于目前节点的key值时候，找寻右子树；当key小于目前节点的key值时候，找寻左子树; 等于时候，返回该节点大小信息
     *       a). 当key值大于当前节点，即cmp > 0; 左侧节点的总数加上当前节点(1 + leftLink.size()) 再加上该节点在右侧的排位，即可以得出节点的总排位值
     *       b). 当key值小于当前节点，即cmp < 0; 直接查看在左侧的节点的排位，即在左侧找到节点之后，计算其size，得到rank值
     */

    private int rank(Key key, TreeNode node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.leftLink);
        if (cmp > 0) return 1 + node.leftLink.size() + rank(key, node.rightLink);
        return node.leftLink.size();
    }


    public void deleteMin() {
        if (root == null) return;
    }





    public void deleteMax() {

    }


}

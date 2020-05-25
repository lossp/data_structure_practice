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
        root = deleteMin(root);
    }

    private TreeNode deleteMin(TreeNode node) {
        // 如果左子树不存，则相当于将原来root节点替换成右侧节点
        if (node.leftLink == null) return node.rightLink;
        // 此时需要不断查看左子树，不断寻找最后一个位置元素
        node.leftLink = deleteMin(node.leftLink);
        // 重新计算树的大小
        node.number = node.rightLink.size() + node.leftLink.size() + 1;
        return node;

    }





    public void deleteMax() {
        root = deleteMax(root);
    }

    // 原理基本和deleteMin一致
    private TreeNode deleteMax(TreeNode node) {
        if (node.rightLink == null) return node.leftLink;
        node.rightLink = deleteMin(node.rightLink);
        node.number = node.rightLink.size() + node.leftLink.size() + 1;
        return node;
    }


    /**
     *  tip:
     *      1.删除的节点，如果有一个右节点，则因此该节点的后继节点为右子树的最小节点，只有这样才能保证树的有序性
     *      2.查找到了指定节点，如果存在左子树和右子树，则首先将此节点保存为tmp, 然后查找tmp最小右侧节点，min(tem.rightLink)
     *        再删除最小右侧节点deleteMin(tmp.rightLink) - 因为要将最小节点移动到原来tmp位置上，
     *      3.此时最小节点的左节点更换为原来tmp的左节点，完成删除
     */

    public void delete(Key key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) return delete(node.rightLink, key);
        if (cmp < 0) return delete(node.leftLink, key);
        else {
            if (node.rightLink == null) return node.leftLink;
            if (node.leftLink == null) return node.rightLink;
            else {
                TreeNode tmp = node;
                node = min(tmp.rightLink);
                node.rightLink = deleteMin(tmp.rightLink);
                node.leftLink = tmp.leftLink;
            }
            node.number = node.leftLink.size() + node.rightLink.size() + 1;
            return node;
        }
    }

    /**
     *  tip:
     *      1. 二叉树插入的思想还是依次和节点进行比较，对左侧或者右侧子树依次进行迭代
     */

    public TreeNode insert(Key key, Value value) {
        return insert(root, new TreeNode(key, value, 0));
    }

    private TreeNode insert(TreeNode root,TreeNode insertOne) {
        // 如果根节点不存，则直接将插入节点更新为根节点
        if (root == null) return insertOne;
        int cmp = insertOne.key.compareTo(root.key);
        if (cmp > 0) {
            root.rightLink = insert(root.rightLink,insertOne);
        } else if (cmp < 0) {
            root.leftLink = insert(root.leftLink, insertOne);
        }
        return insertOne;
    }


}

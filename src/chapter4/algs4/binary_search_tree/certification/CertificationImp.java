package chapter4.algs4.binary_search_tree.certification;

import java.util.NoSuchElementException;

/**
 * 此题目只是思路完成，基本编码完成，测试用例还没有添加
 * 可能解法还有些遗漏
 */
public class CertificationImp extends AbstractCertification<Integer, String> {
    @Override
    public boolean isBinaryTree() {
        int number = isBinaryTree(root, 0);
        return size(root) == number + 1;
    }

    private int isBinaryTree(Node node, int number) {
        if (node == null) throw new NoSuchElementException();
        if (node.left != null) number = isBinaryTree(node.left, ++number);
        if (node.right != null) number = isBinaryTree(node.right, ++ number);
        return number;
    }

    @Override
    public boolean isOrdered(Integer key, Integer max, Integer min) {
        Node current = get(key);
        return isOrdered(current, max, min);
    }

    private boolean isOrdered(Node node, Integer max, Integer min) {
        if (node == null) throw new IllegalArgumentException();
        Integer maxNodeKey = this.max(node.key);
        Integer minNodeKey = this.min(node.key);
        boolean leftEnd = min - minNodeKey <= 0;
        boolean rightEnd = max - maxNodeKey >= 0;
        return leftEnd && rightEnd;
    }


    @Override
    public boolean isBTS(Node node) {
        return isBTS();
    }

    private boolean isBTS() {
        if(!isBinaryTree()) return false;
        if (!hasNoDuplicates(root)) return false;
        if (!isOrdered(10, this.max(), this.min())) return false;
        return true;
    }
}

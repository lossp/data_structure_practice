package chapter4.algs4.binary_search_tree.cache;

import java.util.ArrayList;

public class CacheWithIntegerKey extends AbstractCacheBinaryTree<Integer, String> {
    private ArrayList<String> cache = new ArrayList<>();
    private ArrayList<Integer> index = new ArrayList<>();

    @Override
    public void inOrderTravel() {
        inOrderTravel(this.root);
    }

    private void inOrderTravel(Node root) {
        if (root == null) return;
        inOrderTravel(root.left);
        this.cache.add(root.value);
        this.index.add(root.key);
        inOrderTravel(root.right);

    }

    public ArrayList<String> getCache() { return this.cache; }

    public ArrayList<Integer> getIndex() { return this.index; }


    public String getRecentValue() {
        int arrayIndex = index.indexOf(this.recentIndex);
        return cache.get(arrayIndex);
    }


    public static void main(String[] args) {
        CacheWithIntegerKey cacheWithIntegerKey = new CacheWithIntegerKey();
        cacheWithIntegerKey.put(5, "fifth");
        cacheWithIntegerKey.put(6, "sixth");
        cacheWithIntegerKey.put(7, "seventh");
        cacheWithIntegerKey.put(4, "forth");
        cacheWithIntegerKey.put(3, "third");
        cacheWithIntegerKey.put(0, "shit");

        cacheWithIntegerKey.printTreeMap();
        cacheWithIntegerKey.inOrderTravel();


        System.out.println("----------------------------");
        System.out.println(cacheWithIntegerKey.getCache());
        System.out.println(cacheWithIntegerKey.getIndex());
        System.out.println("最近访问的元素: " + cacheWithIntegerKey.getRecentValue());

    }
}

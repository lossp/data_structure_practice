package chapter4.algs4.binary_search_tree;

/**
 *  3.2.3 Give five orderings of the keys A X C S E R H that,
 *  when inserted into an initially empty BST, produce the best-case tree.
 *
 *  From small index to big index, the ordering is A C E H R S X
 */
public class BestCaseBinaryTree {
    /**  case:
     *                      H
     *                     / \
     *                    C   R
     *                  / \  / \
     *                 A  E S  X
     *
     *  the inserting ordering:
     *      H C A E R S X
     *      H C E A R S X
     *      H R S X C A E
     *      H R X S C A E
     *      H R X S C E A
     *
     */
}

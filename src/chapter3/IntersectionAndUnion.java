package chapter3;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 给定两个已经排序的表L1和L2，使用基本的表操作编写计算L1交L2的过程
 * 给定两个已经排列的表L1和L2，使用基本的表操作编写计算L2并L2的过程
 *
 * 交集: 由于是两个已经排序好的数列，因此只要相互对比彼此位置上的数字大小，如果数字小了，其数列继续往后搜索，而大的数列保持不变，直到搜索到两个数字一样的，再接着重复
 * 这样一来，时间复杂度就是O(n), n代表元素最多的数组
 *
 *
 * 并集: 思想和交集差不多，只需要判断不同即可，相同的只要插入一个，需要注意的是当一个组到达末尾，另一个就相当于全部加入
 *
 */
public class IntersectionAndUnion {
    private LinkedList<Integer> L1 = new LinkedList<>();
    private LinkedList<Integer> L2 = new LinkedList<>();
    private LinkedList<Integer> intersectionResult = new LinkedList<>();
    private LinkedList<Integer> unionResult = new LinkedList<>();

    public void initL1(int... args) {
        for(int arg:args) {
            L1.add(arg);
        }
    }

    public void initL2(int... args) {
        for(int arg: args) {
            L2.add(arg);
        }
    }

    public void intersection () {
        int indexL1 = 0;
        int indexL2 = 0;
        while (L1.size() > indexL1 && L2.size() > indexL2) {
            int numberL1 = L1.get(indexL1);
            int numberL2 = L2.get(indexL2);
            if (numberL1 < numberL2) {
               indexL1++;
            } else if (numberL1 > numberL2){
                indexL2++;
            } else {
                intersectionResult.add(numberL1);
                indexL1++;
                indexL2++;
            }
        }
    }

    public void union() {
        int indexL1 = 0;
        int indexL2 = 0;
        while (indexL1 < L1.size() || indexL2 < L2.size()) {
            if (indexL1 < L1.size() && indexL2 < L2.size()) {
                int numberL1 = L1.get(indexL1);
                int numberL2 = L2.get(indexL2);
                if (numberL1 < numberL2) {
                    unionResult.add(numberL1);
                    indexL1++;
                } else if (numberL1 > numberL2) {
                    unionResult.add(numberL2);
                    indexL2++;
                } else {
                    unionResult.add(numberL1);
                    indexL1++;
                    indexL2++;
                }
            } else if (indexL1 >= L1.size() && indexL2 < L2.size()) {
                int number = L2.get(indexL2);
                unionResult.add(number);
                indexL2++;
            } else if (indexL2 >= L2.size() && indexL1 < L1.size()) {
                int numebr = L1.get(indexL1);
                unionResult.add(numebr);
                indexL1++;
            }
        }
    }



    public void printIntersectionResult() {
        Iterator<Integer> iterator = intersectionResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    public void printUnionResult() {
        Iterator<Integer> iterator = unionResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        IntersectionAndUnion intersection = new IntersectionAndUnion();
        intersection.initL1(1,2,3,4,5,6,7,8);
        intersection.initL2(1,3,7,8,9,10);
        intersection.intersection();
        intersection.union();

        intersection.printUnionResult();
        System.out.println("-------------------");
        intersection.printIntersectionResult();
    }
}

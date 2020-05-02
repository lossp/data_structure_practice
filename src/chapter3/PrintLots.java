package chapter3;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 给定一个表L和另一个表P，它们包含以升序排列的整数。操作printLots(L, P)将打印L中的那些由P指定的位置上的元素
 * 例如，如果P =1 ,3 4, 6，那么，L中位于第1，第3，第4和第6个位置上的元素被打印出来。写出过程printLots(L, P)
 * 只可以使用Collections API容器操作，该过程的运行时间是多少
 *
 * 使用迭代器，维护一个具体的计数器，由于是规定了升序，没必要重复遍历L，因此遍历一遍就可以完成任务，复杂度为O(n)
 */
public class PrintLots {
    private LinkedList<Integer> L;
    private LinkedList<Integer> P;

    public void generateL(int... args) {
        for (int arg:args) {
            L.add(arg);
        }
    }

    public void generateP(int... args) {
        for (int arg:args) {
            P.add(arg);
        }
    }


    public PrintLots(LinkedList L, LinkedList P) {
        this.L = L;
        this.P = P;
    }

    public void printLots() {
        Iterator l = L.iterator();
        Iterator p = P.iterator();
        int index = 0;
        while (l.hasNext() && p.hasNext()) {
            int indexP = (int) p.next();
            int indexL = (int) l.next();
            while (index < indexP && l.hasNext()) {
                index++;
                indexL = (int) l.next();
            }
            index++;
            System.out.println(indexL);
        }
    }


    public static void main(String[] args) {
        PrintLots printLots = new PrintLots(new LinkedList(), new LinkedList());
        printLots.generateL(3,4,5,6,7,8);
        printLots.generateP(1,3,4,5,7,9,10,123);
        printLots.printLots();
     }
}


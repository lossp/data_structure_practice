package chapter3;


import java.util.ArrayList;

/**
 * Josephus问题(Josephus problem)是下面的游戏: N个人编号从1到N，围坐成一个圆圈。
 * 从1号开始传递一个热土豆。经过M次传递后拿着热土豆的人被清除离座，围坐的圆圈缩紧，由坐在被清除人之后的人拿起热土豆继续进行游戏
 * 最后剩下的人取胜。
 * 因此，如果M=0和N=5，则游戏人依序被清除，5号游戏人获胜。如果M=1和N=5，那么清除的人的顺序是2，4，1，5。
 *
 * a.编写一个程序解决M和N在一般值下的Josephus问题，应使程序尽可能地高效率，要确保能够清除各个单元
 * b.你的程序运行时间是多少
 *
 * 要点1. 由于每次清除人后M自动清零，则基本可以判断当N小于M时候，游戏停止，所有人存活
 * 要点2. 将整一个圆圈看作一个单纯的数组，当到达末尾时候回到数组第一位，循环往复, 将index置为-1，再次进入循环时候，index++，为0，回到首位
 *
 */
public class Josephus {
    private ArrayList<Integer> line = new ArrayList<>();
    private int N;
    private int M;
    private long milliseconds;

    public Josephus(int N, int M) {

        // 初始化 line
        for (int i = 1; i < N + 1; i++) {
            line.add(i);
        }
        this.N = N;
        this.M = M;
    }

    public void process() {
        long startTime = System.currentTimeMillis();
        int position = 0;
        for (int i = 0; i < line.size(); i++) {

            if (line.size() == 1 && M != 0) {
                break;
            }

            if (position == M) {
                line.remove(i);
                position = 0;
                i--;
                // 重置index i，到达末尾，重新回到头部，这部分考虑的是到删除之后下一个为末尾之后
                if (i == line.size() - 1) {
                    i = -1;
                }
                continue;
            }
            // 重置index i，到达末尾，重新回到头部
            if (i == line.size() - 1) {
                i = -1;
            }
            position++;
        }
        long endTime = System.currentTimeMillis();
        this.milliseconds = endTime - startTime;
    }

    public void printLine() {
        for (int i:line) {
            System.out.println(i);
        }
    }
    public long getTime() {
        return this.milliseconds;
    }


    public static void main(String[] args) {
        Josephus josephus = new Josephus(5, 0);
        josephus.process();
        josephus.printLine();
        System.out.println("程序运行时间：" + josephus.getTime() + "ms");
    }

}

package chapter3;
/**
 * 用循环数组实现队列
 *
 * tip:
 * 1.要成为循环数组，需要在实例化中具体指定数组的大小，只有这样，才能具体了解到头位置以及尾位置，通过设置当尾位置在往后越过一位时候，归位到头位置，达到循环目的
 * 2.设置头指针初始化为-1，尾指针初始化为0
 *
 * example:
 *  此时为空数组
 *      ---------------------------------------------
 *      |     |      |      |       |       |       |
 *      ---------------------------------------------
 * front  rear
 *
 * 插入第一个元素之后
 *      ---------------------------------------------
 *      |||||||      |      |       |       |       |
 *      ---------------------------------------------
 *      front
 *      rear
 *      front和rear在同一个位置
 *
 * 插入第二个元素之后
 *      ---------------------------------------------
 *      |||||||      |      |       |       |||||||||
 *      ---------------------------------------------
 *                                            front
 *      rear
 *      front位置为size-1, rear为0,
 *
 * 插入第三个元素之后
 *      ---------------------------------------------
 *      |||||||      |      |       |||||||||||||||||
 *      ---------------------------------------------
 *                                    front
 *      rear
 *      front位置为size-2, rear为0, front依次往前移动
 *
 * 插入第N个元素之后，刚刚填充满
 *      ---------------------------------------------
 *      |||||||||||||||||||||||||||||||||||||||||||||
 *      ---------------------------------------------
 *             front
 *      rear
 *      front位置和rear相邻，此时后数组填充满，rear下一位就为front
 *
 */
public class CircularArrayQueue {
    static final int MAX = 100;
    private int[] array;
    private int front;
    private int rear;
    private int size;

    public CircularArrayQueue(int size) {
        this.array = new int[MAX];
        this.front = -1;
        this.rear = 0;
        this.size = size;
    }

    public boolean isFull() {
        if ((front == 0 && rear == size - 1) || front == rear + 1) return true;
        return false;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void enqueue(int key) {
        if (isFull()) {
            System.out.println("queue is overflow");
            return;
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = size - 1;
        } else {
            front -= 1;
        }
        array[front] = key;
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return;
        }
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front += 1;
        }
    }

    public int getFront() {
        if (!isEmpty()) return array[front];
        return -1;
    }

    public int getRear() {
        if (!isEmpty()) return array[rear];
        return -1;
    }

    public static void main(String[] args) {
        CircularArrayQueue circularArrayQueue = new CircularArrayQueue(10);
        System.out.println("插入数字5");
        circularArrayQueue.enqueue(5);
        System.out.println("front = " + circularArrayQueue.getFront());
        System.out.println("rear = " + circularArrayQueue.getRear());

        System.out.println("插入数字4");
        circularArrayQueue.enqueue(4);
        System.out.println("front = " + circularArrayQueue.getFront());
        System.out.println("rear = " + circularArrayQueue.getRear());


        System.out.println("插入数字3");
        circularArrayQueue.enqueue(3);
        System.out.println("front = " + circularArrayQueue.getFront());
        System.out.println("rear = " + circularArrayQueue.getRear());

        System.out.println("弹出第一个数字");
        circularArrayQueue.dequeue();
        System.out.println("front = " + circularArrayQueue.getFront());
        System.out.println("rear = " + circularArrayQueue.getRear());
    }
}

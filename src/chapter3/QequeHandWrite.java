package chapter3;


import java.util.NoSuchElementException;

/**
 * 双端队列(deque)是由一列项组成的数据结构，对该数据结构可以进行以下操作
 * push(x): 将项x插入到双端队列的前端。
 * pop(): 从双端队列中删除前端项并且返回。
 * inject(x): 将项x插入到双端队列的尾端。
 * eject(): 从双端队列中删除尾端并且返回。
 * 编写支持双端队列的例程，其中每种操作均花费O(1)时间
 *
 *
 * tips:
 * 1.考虑到时间花费为O(1)操作，并且为双端队列，双向链条为第一考虑解决方案，因为头部元素和尾部元素都有对应指针，符合查找最后一个并且插入前后的时间花费均为O(1)
 */
public class QequeHandWrite<E> {
    private Node head;
    private Node tail;
    private int size;

    public QequeHandWrite() {
        size = 0;
    }

    class Node {
        E element;
        Node next;
        Node previous;
    }

    public void push(E element) {
        if (head == null) {
            Node newHead = new Node();
            newHead.element = element;
            head = newHead;
            tail = newHead;
        } else {
            Node oldHead = head;
            Node newHead = new Node();
            newHead.element = element;
            head = newHead;
            newHead.next = oldHead;
            oldHead.previous = newHead;
        }
        size++;
    }

    public E pop() {
        E element;
        if (size == 0) throw new NoSuchElementException("双端队列已经为空");
        if (size == 1) {
            element = head.element;
            head = null;
            tail = null;
        } else {
            Node headNext = head.next;
            element = head.element;
            head = headNext;
            headNext.previous = null;
        }
        size--;
        return element;
    }

    public void inject(E element) {
        if (tail == null) {
            Node newTail = new Node();
            newTail.element = element;
            head = newTail;
            tail = newTail;
        } else {
            Node oldTail = tail;
            Node newTail = new Node();
            newTail.element = element;
            tail = newTail;
            tail.previous = oldTail;
            oldTail.next = tail;
        }
        size++;
    }

    public E eject() {
        E element;
        if (size == 0) throw new NoSuchElementException("双端队列已经为空");
        if (size == 1) {
            element = tail.element;
            head = null;
            tail = null;
        } else {
            Node tailPrevious = tail.previous;
            tailPrevious.next = null;
            element = tail.element;
            tail = tailPrevious;
        }
        size--;
        return element;
    }

    public void printQueue() {
        Node temp = head;
        int index = 0;
        while (index < size) {
            System.out.println(temp.element);
            temp = temp.next;
            index++;
        }
    }

    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        QequeHandWrite queue = new QequeHandWrite();
        queue.push("3");
        queue.push("4");
        queue.push("5");
        queue.push("6");
        queue.inject("2");
        queue.pop();
        queue.eject();
        queue.printQueue();
    }
}

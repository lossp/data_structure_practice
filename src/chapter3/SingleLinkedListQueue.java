package chapter3;

import java.util.NoSuchElementException;

/**
 * 使用单链表高效实现队列类，不使用头节点和尾节点
 * tip:
 * 1.就是直接用单链表来实现栈类，所谓头节点就是在链表最前端保存的一个节点，用于保存整个链表的相关信息，尾节点一般设置为一个空节点
 *  所以就一般方来实现，获取第一个元素，作为一个伪的头节点，获取最后元素，作为一个伪的尾节点，相当于两个指针的作用
 *  enqueue相当于从尾部插入, dequeue相当于从头部弹出
 *
 *  enqueue以及dequeue操作时间复杂度均为O(1)的复杂度
 */
public class SingleLinkedListQueue<E> {
    private Node head;
    private Node tail;
    private int size;

    class Node {
        E element;
        Node next;
    }

    public SingleLinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(E element) {
        if (head == null) {
            Node newHead = new Node();
            newHead.element = element;
            head = newHead;
            tail = newHead;
        } else {
            Node newTail = new Node();
            Node oldTail = tail;
            newTail.element = element;
            oldTail.next = newTail;
            tail = newTail;
        }
        size++;
    }
    public E dequeue() {
        if (head == null) throw new NoSuchElementException();
        E element = head.element;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
        }
        return element;
    }


    public void printQueue() {
        Node temp = head;
        while (temp.next != null) {
            System.out.println(temp.element);
            temp = temp.next;
        }
        System.out.println(temp.element);
    }

    public static void main(String[] args) {
        SingleLinkedListQueue singleLinkedListQueue = new SingleLinkedListQueue();
        singleLinkedListQueue.enqueue("1");
        singleLinkedListQueue.enqueue("2");
        singleLinkedListQueue.enqueue("3");
        singleLinkedListQueue.enqueue("4");
        singleLinkedListQueue.enqueue("5");

        System.out.println("dequeue :" + singleLinkedListQueue.dequeue());
        System.out.println("dequeue :" + singleLinkedListQueue.dequeue());
        singleLinkedListQueue.printQueue();
    }

}

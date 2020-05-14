package chapter3;

import java.util.NoSuchElementException;

/**
 * 使用单链表高效实现栈类，不使用头节点和尾节点
 * tip:
 * 1.就是直接用单链表来实现栈类，所谓头节点就是在链表最前端保存的一个节点，用于保存整个链表的相关信息，尾节点一般设置为一个空节点
 *  所以就一般方来实现，获取第一个元素，作为一个伪的头节点
 *  pop以及push操作时间复杂度均为O(1)的复杂度
 */
public class SingleLinkedListStack<E> {
    private Node head;
    private int size;

    class Node {
        E element;
        Node next;
    }

    public SingleLinkedListStack () {
        head = null;
        size = 0;
    }

    public void push(E element) {
        if (head == null) {
            head = new Node();
            head.element = element;
        } else {
            Node newHead = new Node();
            Node oldHead = head;
            newHead.element = element;
            newHead.next = oldHead;
            head = newHead;
        }
        size++;
    }

    public E pop() {
        if (head == null) throw new NoSuchElementException();
        E element = head.element;
        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
        }
        size--;
        return element;
    }


    public static void main(String[] args) {
        SingleLinkedListStack singleLinkedListStack = new SingleLinkedListStack();
        singleLinkedListStack.push(3);
        singleLinkedListStack.push("4");
        singleLinkedListStack.push("abc");
        singleLinkedListStack.push(0);
        System.out.println(singleLinkedListStack.pop());
        System.out.println(singleLinkedListStack.pop());
        System.out.println(singleLinkedListStack.pop());
        System.out.println(singleLinkedListStack.pop());
    }
}

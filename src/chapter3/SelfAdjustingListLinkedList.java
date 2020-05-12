package chapter3;

import java.util.Iterator;

/**
 * 写出自调整表的链表实现
 *
 * tip:
 * 1.如果仅仅用单链来实现，在调换位置的时候，需要考虑到当前节点的前置节点，并且Iterator遍历时候，需要随时记录当前节点的前置节点 - previous
 * 2.在调整节点时候，需要生成一个新的头节点，将current值复制到其中去，然后previous直接连接current.next元素，直接完成位置调整
 */
public class SelfAdjustingListLinkedList<E> {
    private Node head;
    private int size;

    class Node {
        E element;
        Node next;
    }

    public SelfAdjustingListLinkedList() {
        head = null;
        size = 0;
    }

    public void add(E element) {
        if (head == null) {
            this.head = new Node();
            this.head.element = element;
        } else {
            Node newHead = new Node();
            Node oldHead = head;
            newHead.element = element;
            newHead.next = oldHead;
            head = newHead;
        }
        size++;
    }

    public boolean find(E item) {
        Iterator<Node> iterator = getIterator();
        boolean isFound = false;
        Node current;
        Node previous;
        Node temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            previous = temp;
            current = previous.next;
            // 判断头部
            if (head.element.equals(item)) {
                isFound = true;
                break;
            }
            if (current != null && current.element.equals(item)) {
                isFound = true;
                // 不在首位的情况下
                if (!current.element.equals(head.element)) {
                    Node newHead = new Node();
                    Node oldHead = head;
                    newHead.element = current.element;
                    newHead.next = oldHead;
                    head = newHead;
                    previous.next = current.next;

                }
                break;
            }
        }
        return isFound;
    }


    public Iterator<Node> getIterator() {
        return new LinkedListIterator();
    }

    public int getSize() { return this.size; }

    private class LinkedListIterator implements Iterator<Node> {
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node next() {
            Node temp = current;
            current = current.next;
            return temp;
        }
    }

    public void printLinkedList() {
        Iterator<Node> iterator = getIterator();
        Node current;
        while (iterator.hasNext()) {
            current = iterator.next();
            System.out.println(current.element);
        }
    }

    public static void main(String[] args) {
        SelfAdjustingListLinkedList<String> selfAdjustingListLinkedList = new SelfAdjustingListLinkedList<>();
        selfAdjustingListLinkedList.add("第5个");
        selfAdjustingListLinkedList.add("第4个");
        selfAdjustingListLinkedList.add("第3个");
        selfAdjustingListLinkedList.add("第2个");
        selfAdjustingListLinkedList.add("第1个");
        System.out.println(selfAdjustingListLinkedList.find("第2个"));
        System.out.println(selfAdjustingListLinkedList.find("第6个"));
        selfAdjustingListLinkedList.printLinkedList();
        System.out.println("===============");
        System.out.println(selfAdjustingListLinkedList.find("第3个"));
        System.out.println(selfAdjustingListLinkedList.find("第3个"));
        selfAdjustingListLinkedList.printLinkedList();
    }

}

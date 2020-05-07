package chapter3;

import java.util.Iterator;


/**
 * 假设一个单链表使用一个头节点实现，但是无尾节点，并且假设它只保留对该头节点的引用
 * 编写一个类，包含
 * a. 返回链表大小的方法
 * b. 打印链表的方法
 * c. 测试值x是否含于链表的方法
 * d. 如果值x尚未含于链表，添加值x到该链表的方法
 * e.如果值x包含于链表，将x从该链表中删除的方法
 */
public class HeadSingleLinkedList<E> {
    private Node head;
    private int size;

    class Node {
        E element;
        Node next;
    }

    public HeadSingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(E element) {
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

    private Node node(int index) {
        Node current = head;
        for (int position = 0; position < size; position++) {
            if(position == index) {
                break;
            } else {
                current = current.next;
            }
        }
        return current;
    }



    public int getSize() {
        return size;
    }

    public void printList() {
        Iterator<E> iterator = getIterator();
        int index = 0;
        while (iterator.hasNext()) {
            System.out.println("位置为" + index + "的值为 :" + iterator.next());
            index++;
        }
    }

    public void printReverseList() {
        Iterator<E> reverseIterator = getReverseIterator(this.size);
        int reverseIndex = size - 1;
        while (reverseIterator.hasNext()) {
            System.out.println("位置为" + reverseIndex + "的值为 :" + reverseIterator.next());
            reverseIndex--;
        }
    }

    public boolean contains(E element) {
        Iterator<E> iterator = getIterator();
        boolean contains = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public void addIfNotExists(E element) {
        boolean contains = false;
        if (head == null) {
            addFirst(element);
        } else {
            Node temp = head;
            while (temp != null) {
                if (temp.element.equals(element)) {
                    contains = true;
                    break;
                }
                temp = temp.next;
            }
            if (!contains) {
                addFirst(element);
            }
        }
    }

    public void deleteIfExists(E element) {
        if (head == null) return;
        // 判断等于的节点是否为头节点
        if (head.element.equals(element)) {
            // 直接将头节点设置为其next的节点
            head = head.next;
            size--;
        } else {
            Node temp = head;
            Node previous = null;
            Node next = null;
            Node current = null;
            while (temp.next != null) {
                previous = temp;
                current = previous.next;
                next = current.next;
                if (current.element.equals(element)) {
                    previous.next = next;
                    // 如果next = null表示current已经到达链表末尾，直接跳出循环
                    if (next == null) {
                        size--;
                        break;
                    }
                    size--;
                }
                temp = temp.next;
            }
        }
    }


    public Iterator<E> getIterator() {
        return new HeadSingleLinkedListIterator();
    }

    public Iterator<E> getReverseIterator(int size) {
        return new HeadSingleLinkedListReverseIterator(size);
    }

    private class HeadSingleLinkedListReverseIterator implements Iterator<E> {
        private int reverseIndex;
        public HeadSingleLinkedListReverseIterator(int size) {
            reverseIndex = size - 1;
        }

        // 获取最后一个
        @Override
        public boolean hasNext() {
            return reverseIndex != -1;
        }

        @Override
        public E next() {
            Node current = node(reverseIndex);
            reverseIndex--;
            return current.element;
        }
    }

    private class HeadSingleLinkedListIterator implements Iterator<E> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }
    }

    public static void main(String[] args) {
        HeadSingleLinkedList headSingleLinkedList = new HeadSingleLinkedList();
        headSingleLinkedList.addFirst(5);
        headSingleLinkedList.addFirst(4);
        headSingleLinkedList.addFirst(3);
        headSingleLinkedList.addFirst(2);
        headSingleLinkedList.addFirst(1);
        System.out.println("包含的情况 : " + headSingleLinkedList.contains(5));
        headSingleLinkedList.addIfNotExists(6);
        headSingleLinkedList.deleteIfExists(5);
        headSingleLinkedList.printList();
        System.out.println("=================");
        headSingleLinkedList.printReverseList();

    }


}

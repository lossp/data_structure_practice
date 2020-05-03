package chapter3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 通过只调整链(而不是数据)来交换两个相邻的元素, 使用双链表
 *
 * 双链表相比与单链而言，就多了一个指向与前一个元素的指针，在作出相应处理时候处理就行。
 */
public class SwapNeighbourDoubleLinkedList<E> {
    private int size;
    private Node first;
    private Node last;

    class Node {
        E item;
        Node previous;
        Node next;
    }

    public SwapNeighbourDoubleLinkedList() {
        this.first = new Node();
        this.last = new Node();
        this.size = 0;
    }

    public void printMap() {
        Iterator iterator = getIterator();
        int index = 0;
        while (iterator.hasNext()) {
            System.out.println("index = " + index + " , node = " + iterator.next());
            index++;
        }
    }

    public void addFirst(E element) {
        if (size == 0) {
            Node newNode = new Node();
            newNode.item = element;
            first = newNode;
            last = newNode;
        } else {
            Node newFirst = new Node();
            newFirst.item = element;
            Node oldFirst = first;
            first = newFirst;
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(E element) {
        if (size == 0) {
            Node newNode = new Node();
            newNode.item = element;
            first = newNode;
            last = newNode;
        } else {
            Node newLast = new Node();
            newLast.item = element;
            Node oldLast = last;
            last = newLast;
            last.previous = oldLast;
            oldLast.next = last;
        }
        size++;
    }

    public Iterator<E> getIterator() {
        return new DoubleLinkedIterator();
    }

    private class DoubleLinkedIterator implements Iterator<E> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E element = current.item;
            current = current.next;
            return element;
        }

    }

    public void swap(int position) {
        if (position > size) throw new IndexOutOfBoundsException();
        Node previous = new Node();
        Node current = new Node();
        Node temp = first;
        int index = 0;
        while (temp.next != null) {
            // 在首部
            if (position == 0) {
                Node nextOne = first.next;
                Node oldFirst = first;
                first = nextOne;
                oldFirst.next = nextOne.next;
                nextOne.next.previous = oldFirst;
                oldFirst.previous = nextOne;
                nextOne.next = oldFirst;
                nextOne.previous = null;
                break;
            }
            // 定位previous节点
            if (index == position - 1) {
                previous = temp;
            }
            if (index == position && position != size - 1) {
                current = temp;
                Node currentNext = current.next;
                current.next = currentNext.next;
                previous.next = currentNext;
                currentNext.previous = previous;
                currentNext.next = current;
                current.previous = currentNext;
                break;
            }

            // 交换位置在末尾
            if (position == size - 1) {
                break;
            } else {
                temp = temp.next;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        SwapNeighbourDoubleLinkedList swapNeighbourDoubleLinkedList = new SwapNeighbourDoubleLinkedList();
        swapNeighbourDoubleLinkedList.addFirst("2");
        swapNeighbourDoubleLinkedList.addFirst("1");
        swapNeighbourDoubleLinkedList.addLast("3");
        swapNeighbourDoubleLinkedList.addLast("4");
        swapNeighbourDoubleLinkedList.addLast("5");
        swapNeighbourDoubleLinkedList.swap(2);
        swapNeighbourDoubleLinkedList.printMap();
    }
}

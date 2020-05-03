package chapter3;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 通过只调整链(而不是数据)来交换两个相邻的元素, 使用单链表
 *
 * tips: 需要找到当前元素的前一个元素(不是头元素情况)，因为不考虑此情况时候，会造成元素丢失情况，即第一个元素丢失
 */
public class SwapNeighbourSingleLinkedList<E> {
    private Node first;
    private int size;

    public SwapNeighbourSingleLinkedList() {
        this.size = 0;
        this.first = null;
    }

    private class Node {
        E item;
        Node next;
    }

    public void addFirst(E element) {
        if (first == null) {
            first = new Node();
            first.item = element;
            first.next = null;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = element;
            first.next = oldFirst;
        }
        size++;
    }

    public int getSize() {
        return this.size;
    }

    public boolean contains(E element) {
        Iterator iterator = getIterator();
        boolean contains = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public void swap(int position) {
        if (position > size) throw new IndexOutOfBoundsException();
        int index = 0;
        Node temp = first;
        Node previous = new Node();
        Node current = new Node();
        while (first.next != null) {
            // 交换位置在第一个时
            if (position == 0) {
                Node rightNode = first.next;
                Node tempNode = first;
                tempNode.next = rightNode.next;
                first = rightNode;
                first.next = tempNode;
                break;
            }

            // 交换位置不在第一位
            if (index == position - 1) {
                previous = temp;
            }
            if (index == position && position != size -1) {
                current = temp;
                Node currentNext = current.next;
                current.next = currentNext.next;
                currentNext.next = current;
                previous.next = currentNext;
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

    public void printList() {
        Iterator iterator = getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public Iterator<E> getIterator() {
        return new SingleLinkedListIterator();
    }

    private class SingleLinkedListIterator implements Iterator<E> {
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

    public static void main(String[] args) {
        SwapNeighbourSingleLinkedList swapNeighbourSingleLinkedList = new SwapNeighbourSingleLinkedList();
        swapNeighbourSingleLinkedList.addFirst("位置5");
        swapNeighbourSingleLinkedList.addFirst("位置4");
        swapNeighbourSingleLinkedList.addFirst("位置3");
        swapNeighbourSingleLinkedList.addFirst("位置2");
        swapNeighbourSingleLinkedList.addFirst("位置1");
        swapNeighbourSingleLinkedList.swap(2 );
        swapNeighbourSingleLinkedList.printList();

    }



}

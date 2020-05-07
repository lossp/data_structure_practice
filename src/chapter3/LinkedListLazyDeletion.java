package chapter3;

import java.util.Iterator;

/**
 * 不同于我们已经给出的删除方法，另一种是使用懒惰删除的删除方法。要删除一个元素，我们只是标记上该元素被删除(使用一个附加的位(bit)域)。
 * 表中被删除和非被删除元素的个数作为数据结构的一部分被保留。如果被删除元素和非被删除元素一样多，则遍历整个表，对所有标记的节点执行标准的删除算法
 * a. 列出懒惰删除的优点和缺点
 * b. 编写使用懒惰删除实现标准链表操作的相应例程
 *
 */
public class LinkedListLazyDeletion<E> {
    private Node head;
    private int size;
    private int deleteNumber;

    public LinkedListLazyDeletion() {
        this.head = null;
        this.size = 0;
        this.deleteNumber = 0;
    }

    class Node {
        E element;
        Node next;
        boolean delete = false;
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

    public void delete(int position) {
        Node node = node(position);
        if (node != null) {
            node.delete = true;
            deleteNumber++;
        }
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            Node currentNode = node(i);
            System.out.println("位置为 " + i + " 的值为:" + currentNode.element + " 删除状态为: " + currentNode.delete);
        }
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

    // 清除状态为delete = true的节点
    public void clean() {
        if (size - deleteNumber < deleteNumber) {
            if (head != null) {
                // 如果头节点为删除节点，一直往下重复查看，直到头节点不为删除节点为止
                while (head.delete) {
                    head = head.next;
                }
                Node temp = head;
                Node previous = temp;
                Node current = null;
                Node next = null;
                while (temp.next != null) {
                    current = previous.next;
                    next = current.next;
                    if (current.delete) {
                        previous.next = next;
                        current = next;
                        // 判断current是否为最后一个元素
                        if (current.next == null) {
                            if (current.delete) {
                                previous.next = null;
                            }
                            break;
                        }
                    } else {
                        if (current.next != null) {
                            previous = current;
                            current = next;
                            next = next.next;
                        // 只有两个元素情况，并且均为不删除元素，跳出循环
                        } else {
                            break;
                        }
                    }

                }



                // 判断头节点是否被删除

            } else {
            }
        } else {
            // do noting
        }
    }

    public static void main(String[] args) {
        LinkedListLazyDeletion linkedListLazyDeletion = new LinkedListLazyDeletion();
        linkedListLazyDeletion.addFirst(5);
        linkedListLazyDeletion.addFirst(4);
        linkedListLazyDeletion.addFirst(3);
        linkedListLazyDeletion.addFirst(2);
        linkedListLazyDeletion.addFirst(1);
        linkedListLazyDeletion.delete(3);
        linkedListLazyDeletion.delete(0);
        linkedListLazyDeletion.delete(4);
        linkedListLazyDeletion.printList();
        linkedListLazyDeletion.clean();
        System.out.println("--------------------");
        linkedListLazyDeletion.printList();
    }
}

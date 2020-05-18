package concurrent;


import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞链表，在CAS(compare and set)基础上构建， 出自java Concurrent 一书
 */
public class ConcurrentLinkedList<E> {
    private final Node dummy = new Node(null, null);
    private final AtomicReference<Node> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node> tail = new AtomicReference<>(dummy);

    private class Node {
        E element;
        // Node 指向为原子引用类，AtomicReference实际作用与volatile相同
        AtomicReference<Node> next;
        public Node(E element, Node next) {
            this.element = element;
            this.next = new AtomicReference<>(next);
        }
    }


    public boolean put (E element) {
        Node newNode = new Node(element, null);
        while (true) {
            Node currentTail = tail.get();
            Node tailNext = currentTail.next.get();
            // 保存currentTail遍历，保证是在操作此节点时候，整个链表没有发生改变
            if (currentTail == tail.get()) {
                // 此时队列处于静止状态
                if (tailNext != null) {
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}

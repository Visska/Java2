package java_2_homework_8;

import java_2_homework_8.impl.SingleLinkedList;

public class Node {
    public String val;
    public Node next;
    public Node prev;

    public Node(String val) {
        this(val, null, null);
    }

    public Node(String val, Node prev) {
        this(val, null, prev);
    }

    public Node(String val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) { this.prev = prev; }

    @Override
    public String toString() {
        return "Node{" +
                "val='" + val + '\'' +
                ", next=" + next +
                ", prev=" + (prev != null ? prev.val : null) +
                '}';
    }
}

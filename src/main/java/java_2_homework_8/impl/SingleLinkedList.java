package java_2_homework_8.impl;

import java_2_homework_8.GBIterator;
import java_2_homework_8.GBList;
import java_2_homework_8.Node;

public class SingleLinkedList implements GBList {
    private Node first;
    private int size = 0;

    @Override
    public void add(String val) {
        if (first == null) {
            first = new Node(val);
        } else {
            add(first, val);
        }
        size++;
    }

    private void add(Node current, String val) {
        if (current.next == null) {
            current.next = new Node(val, current);
            return;
        }
        add(current.next, val);
    }

    @Override
    public Node get(Integer index) {
        if ( index >= size || index < 0) {
            return null;
        }

        Node current = first;
        for (int i=0; i<size; i++) {
            if (i == index) {
                break;
            }
            current = current.next;
        }

        return current;
    }

    @Override
    public boolean remove(String val) {
        if (first.val.equals(val)) {
            first.next.setPrev(null);
            first = first.next;
            size--;
            return true;
        }

        Node prev = first;
        Node current = first.next;
        while(current != null) {
            if (current.val.equals(val)) {
                prev.setNext(current.next);
                current.next.setPrev(prev);
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
//        int i = 0;
//        GBIterator iterator = iterator();
//        while (iterator.hasNext()) {
//            i++;
//        }
//        return i;
        return size;
    }

    @Override
    public GBIterator iterator() {
        return new StraightForwardIterator(first);
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "first=" + first +
                '}';
    }

    public static class StraightForwardIterator implements GBIterator {
        private Node current;

        public StraightForwardIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {
            String val = current.val;
            current = current.next;
            return val;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class LinkedList<Item> implements Iterable<Item> {
    Node tail;
    Node head;
    int n;

    private class Node {
        Node prev;
        Node next;
        Item data;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Node oldTail = tail;
        tail = new Node();
        tail.data = data;
        tail.prev = oldTail;

        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }

        n++;

    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldTail = tail;

        if (size() > 1) {
            tail = tail.next;
            tail.next = null;
        } else {
            tail = null;
            head = null;
        }
        n--;
        return oldTail.data;

    }

    public void reverseLinkedList() {

        Node next = null;
        Node prev = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }

        head = prev;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Item item : this) {
            sb.append(item);
        }

        return sb.toString();

    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.data;
            current = current.next;
            return item;

        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> s = new LinkedList<>();
        s.push(2);
        s.push(4);
        s.push(5);
        s.push(6);

        System.out.println(s);
        s.reverseLinkedList();
        System.out.println(s);
        System.out.println(s.head.next.next.next.prev.data);

    }

}

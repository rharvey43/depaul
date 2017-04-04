package project.model;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    private int N;
    private Node first;
    private Node last;
    
    public Queue() {
        this.first = null;
        this.last = null;
    }
    
    public boolean isEmpty() {
        return this.first == null;
    }
    
    public int length() {
        return this.N;
    }
    
    public int size() {
        return this.N;
    }
    
    public void enqueue(final Item item) {
        final Node x = new Node();
        x.item = item;
        if (this.isEmpty()) {
            this.first = x;
            this.last = x;
        }
        else {
            this.last.next = x;
            this.last = x;
        }
        ++this.N;
    }
    
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        final Item item = this.first.item;
        this.first = this.first.next;
        --this.N;
        return item;
    }
    
    public Item peek() {
        return this.first.item;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (Node x = this.first; x != null; x = x.next) {
            s = s + x.item + " ";
        }
        return s;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class Node
    {
        private Item item;
        private Node next;
    }
    
    private class QueueIterator implements Iterator<Item>
    {
        private Node current;
        
        private QueueIterator() {
            this.current = Queue.this.first;
        }
        
        @Override
        public boolean hasNext() {
            return this.current != null;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = this.current.item;
            this.current = this.current.next;
            return item;
        }
    }
}
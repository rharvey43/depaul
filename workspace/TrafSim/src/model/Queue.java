package model;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    private int N;
    private Node first;
    private Node last;
    
    public Queue() 
    {
        first = null;
        last = null;
    }
    
    public boolean isEmpty() 
    {
        return first == null;
    }
    
    public int length() 
    {
        return N;
    }
    
    public int size() 
    {
        return N;
    }
    
    public void enqueue(final Item item) 
    {
        final Node x = new Node();
        x.item = item;
        if (isEmpty()) 
        {
            first = x;
            last = x;
        }
        else 
        {
            last.next = x;
            last = x;
        }
        ++N;
    }
    
    public Item dequeue() 
    {
        if (isEmpty()) 
        {
            throw new RuntimeException("Queue underflow");
        }
        final Item item = first.item;
        this.first = first.next;
        --N;
        return item;
    }
    
    public Item peek() 
    {
        return first.item;
    }
    
    @Override
    public String toString() 
    {
        String s = "";
        for (Node x = first; x != null; x = x.next) 
        {
            s = s + x.item + " ";
        }
        return s;
    }
    
    @Override
    public Iterator<Item> iterator() 
    {
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
        
        private QueueIterator() 
        {
            current = Queue.this.first;
        }
        
        @Override
        public boolean hasNext() 
        {
            return current != null;
        }
        
        @Override
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Item next() 
        {
            if (!hasNext()) 
            {
                throw new NoSuchElementException();
            }
            final Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
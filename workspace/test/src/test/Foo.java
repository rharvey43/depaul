package test;

public class Foo {
static class Node {
	Object item;
	Node next;
	Node (Object item, Node next){
		this.item = item;
		this.next = next;
		}
	}
public static void main (String[] args) {
	Node data = null;
	data = new Node ("hello",data);
	data = new Node (5, data);
	while (data != null) {
		String s = (String) data.item;
		System.out.println(s);;
		}
	}
}

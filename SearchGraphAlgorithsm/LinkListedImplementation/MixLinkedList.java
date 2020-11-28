package LinkListedImplementation;

import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class MixLinkedList<T> {
	
	private Node<T> first;
	private Node<T> end;
	private int size;

	public void setSize(int size) {
		this.size = size;
	}

	public MixLinkedList() {
		first = null;
		end = null;
		size = 0;
	}
	
	
	
	public T get(int index) {
		int count = 0;
		boolean loop = true;
		Node<T> curNode = first;
		T value = curNode.getValue();
		
		while(loop) {
			if(count == index) {
				return value;
			}
			else {
				curNode = curNode.getFollowNode();
				value = curNode.getValue();
				count ++;
				if(count >= size) {
					loop = false;
				}
			}
		}
		return null;
	}
	
	public Node<T> getNode(int index) {
		int count = 0;
		boolean loop = true;
		Node<T> curNode = first;

		
		while(loop) {
			if(count == index) {
				return curNode;
			}
			else {
				curNode = curNode.getFollowNode();
				count ++;
			}
		}
		return null;
	}
	
	

	// add to end poiter
	public void addToEnd(Node new_node) {
		if ((first == null) || (end == null)) {
			first = new_node;
			end = new_node;
		} else {
			Node.insertToBehind(new_node, end);
			end = new_node;
		}
		size++;
	}

	// add to end poiter
	public void addToFirst(Node new_node) {
		if (first == null) {
			first = new_node;
			end = new_node;
		} else {
			Node.insertToBefore(new_node, first);
			first = new_node;
		}
		size++;
	}

	public Node dequeue() {
		if (first == null) {
			return null;
		} else {
			Node result = new Node(first);
			first = first.getFollowNode();
			if (size == 1) {
				this.end = null;
				this.first = null;
			}
			if (first != null) {
				first.setPreNodeNull();
			}
			size--;
			return result;
		}
	}

	public Node pop() {
		if (end == null) {
			return null;
		} else {
			Node result = new Node(end);
			end = end.getPreviousNode();
			if (size == 1) {
				this.first = null;
				this.end = null;
			}
			if (end != null) {
				end.setFolNodeNull();
			}
			size--;
			return result;
		}
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public Node first() {
		if (first == null)
			return null;
		else
			return first;
	}

	public boolean isInList(int v) {
		MixLinkedList list = this;
		Node<GraphValue> n1 = list.first;
		do {
			int vertex = n1.getValue().getVertex();
			if (vertex == v)
				return true;
			n1 = n1.getFollowNode();
		} while (n1 != null);
		return false;
	}

	public Node end() {

		if (end == null)
			return null;
		else
			return end;
	}

	public static void printList(MixLinkedList<GraphValue> list) {
		Node<GraphValue> curNode = list.first;
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size - 1)) {
				System.out.print("(" + curNode.getValue().getVertex() + "," + curNode.getValue().getWeight() + ")");
			} else {
				System.out.print("(" + curNode.getValue().getVertex() + "," + curNode.getValue().getWeight() + ")" + " , ");
			
			}
			curNode = curNode.getFollowNode();
		}
	}
	
	

}

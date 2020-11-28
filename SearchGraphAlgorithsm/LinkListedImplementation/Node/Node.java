package LinkListedImplementation.Node;

public class Node<T> {

	private T value;
	private Node previousNode;
	private Node followNode;

	public Node(Node n1) {
		this.value = (T) n1.getValue();
		this.previousNode = null;
		this.followNode = null;
	}
	
	public Node(T value){
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}


	public void setPreNodeNull() {
		this.previousNode = null;
	}

	public void setFolNodeNull() {
		this.followNode = null;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		try {
			if (previousNode == null) {
				throw new IllegalArgumentException("Null argument !");
			}
			this.previousNode = previousNode;
		} catch (IllegalArgumentException ex) {
			this.previousNode = null;
		}
	}

	public Node<T> getFollowNode() {
		return followNode;
	}

	public void setFollowNode(Node followNode) {
		try {
			if (previousNode == null) {
				throw new IllegalArgumentException("Null argument !");
			}
			this.followNode = followNode;
		} catch (IllegalArgumentException ex) {
			this.followNode = null;
		}
	}

	public static void insertToBefore(Node n1, Node n2) {
		Node oldNode = n2.previousNode;
		if (oldNode == null) {
			n1.followNode = n2;
			n2.previousNode = n1;
		} else {
			n1.previousNode = oldNode;
			oldNode.followNode = n1;

			n1.followNode = n2;
			n2.previousNode = n1;
		}

	}

	public static void insertToBehind(Node n1, Node n2) {
		Node oldNode = n2.followNode;
		if (oldNode == null) {
			n1.previousNode = n2;
			n2.followNode = n1;
		} else {
			n1.followNode = oldNode;
			oldNode.previousNode = n1;
			n2.followNode = n1;
			n1.previousNode = n2;
		}

	}

}

package LinkListedImplementation;


import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class DFSearch extends SearchAbstract {

	public DFSearch(Graph graph) {
		super(graph);
	}

	@Override
	void addToCloseList(Node new_node) {
		this.closeList.addToEnd(new_node);

	}

	@Override
	Node<GraphValue> getFromCloseList() {
		return (Node<GraphValue>)this.closeList.first();

	}

	@Override
	void addToOpenList(Node new_node) {
		this.openList.addToEnd(new_node);

	}

	@Override
	Node<GraphValue> getFromOpenList() {

		return (Node<GraphValue>)this.openList.pop();

	}

	@Override
	void search(int src, int des) {
		this.openList = new MixLinkedList<GraphValue>();
		this.closeList = new MixLinkedList<GraphValue>();
		this.reusultPath = new MixLinkedList<GraphValue>();
		Node<GraphValue> S = new Node<GraphValue>(new GraphValue(src, 0));
		addToOpenList(S);
		loop1: while (!this.openList.isEmpty()) {
			S = getFromOpenList();
			addToCloseList(new Node<GraphValue>(S));
			Node<GraphValue> resultNode = new Node(S);
			this.reusultPath.addToEnd(resultNode);
			int vertexS = S.getValue().getVertex();
			if (vertexS == des) {
				System.out.print(vertexS);
				break loop1;
			} else {
				System.out.print(vertexS + "=>");
				MixLinkedList<GraphValue> listChild = this.graph.adj_list.get(vertexS);
				if (!listChild.isEmpty()) {
					for (int i = 0; i < listChild.size(); i++) {
						Node<GraphValue> child = (Node<GraphValue>) listChild.getNode(i);
						int vertex = child.getValue().getVertex();
						if (!this.isInClose(vertex)) {
							addToOpenList(new Node<GraphValue>(child));
						}
					}
				} else {
					buildResultPath();
				}
			}
		}
		System.out.println("");
	}

	public void buildResultPath() {
		Node<GraphValue> dropNode = new Node<GraphValue>(this.reusultPath.pop());
		int valueDropNode = dropNode.getValue().getVertex();
		Node<GraphValue> endNode = new Node<GraphValue>(this.reusultPath.end());
		int curValue = endNode.getValue().getVertex();
		boolean loop = true;
		loop2: while (loop) {
			MixLinkedList<GraphValue> childListTemp = graph.adj_list.get(curValue);
			for (int i = 0; i < childListTemp.size(); i++) {
				Node<GraphValue> child =  childListTemp.getNode(i);
				int vertex = child.getValue().getVertex();
				if ((vertex != valueDropNode) && (this.isInOpen(vertex))) {
					break loop2;
				}
			}
			dropNode = new Node<GraphValue>(this.reusultPath.pop());
			valueDropNode = dropNode.getValue().getVertex();
			endNode = new Node<GraphValue>(this.reusultPath.end());
			curValue = endNode.getValue().getVertex();
		}
	}
	
	public void printVertexPath() {
		Node<GraphValue> curNode = this.reusultPath.first();
		for (int i = 0; i < this.reusultPath.size(); i++) {
			if (i == (this.reusultPath.size() - 1)) {
				System.out.print(curNode.getValue().getVertex());
			} else {
				System.out.print(curNode.getValue().getVertex() + "=>");
			}
			curNode = curNode.getFollowNode();
		}
	}

	public static void main(String[] args) {
		// Test graph
		MixLinkedList<Edge> edges = new MixLinkedList<Edge>();
		edges.addToEnd(new Node<Edge>(new Edge(0, 1, 1)));
		edges.addToEnd(new Node<Edge>(new Edge(0, 2, 3)));
		edges.addToEnd(new Node<Edge>(new Edge(0, 3, 6)));
		
		edges.addToEnd(new Node<Edge>(new Edge(2, 4, 5)));
		edges.addToEnd(new Node<Edge>(new Edge(2, 5, 2)));
		
		edges.addToEnd(new Node<Edge>(new Edge(3, 6, 1)));
		edges.addToEnd(new Node<Edge>(new Edge(3, 7, 7)));
		edges.addToEnd(new Node<Edge>(new Edge(3, 11, 2)));
		
		edges.addToEnd(new Node<Edge>(new Edge(6, 8, 3)));
		edges.addToEnd(new Node<Edge>(new Edge(7, 9, 5)));
		edges.addToEnd(new Node<Edge>(new Edge(9, 10, 7)));
		
		// init graph
		Graph g1 = new Graph(edges, 12);
		// print graph
		System.out.println("Graph information: ");
		Graph.printGraph(g1);
		// create search class
		DFSearch s1 = new DFSearch(g1);
		System.out.println();
		// List vertexes visited to find the destination
		int src = 0;
		int des = 1;
		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
		s1.search(src, des);
		System.out.println("The path from " + src + " to " + des + " is:");
		s1.printVertexPath();
		System.out.println();

		
	}

}

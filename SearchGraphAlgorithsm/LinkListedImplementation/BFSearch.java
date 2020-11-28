package LinkListedImplementation;

import java.util.Arrays;
import java.util.List;

import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class BFSearch extends SearchAbstract {

	public BFSearch(Graph graph) {
		super(graph);

	}

	public BFSearch() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void addToCloseList(Node new_node) {
		this.closeList.addToEnd(new_node);

	}

	@Override
	Node getFromCloseList() {
		return this.closeList.first();
	}

	@Override
	void addToOpenList(Node new_node) {
		this.openList.addToEnd(new_node);

	}

	@Override
	Node getFromOpenList() {
		return this.openList.dequeue();
	}

	@Override
	void search(int src, int des) {
		this.openList = new MixLinkedList<GraphValue>();
		this.closeList = new MixLinkedList<GraphValue>();
		Node<GraphValue> S = new Node<GraphValue>(new GraphValue(src, 0));
		addToOpenList(S);
		loop1: while (!this.openList.isEmpty()) {
			S = getFromOpenList();
			addToCloseList(new Node<GraphValue>(S));
			int vertexS = S.getValue().getVertex();
			if (vertexS == des) {
				System.out.print(vertexS);
				break loop1;
			}
			System.out.print(vertexS + "=>");
			MixLinkedList<GraphValue> listChild = this.graph.adj_list.get(vertexS);
			if (listChild != null) {
				for (int i = 0; i < listChild.size(); i++) {
					Node<GraphValue> child = (Node) listChild.getNode(i);
					int vertex = child.getValue().getVertex();
					if (!this.isInClose(vertex)) {
						addToOpenList(new Node<GraphValue>(child));
					}
				}
			}
		}
		System.out.println("");

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
		// create graph
		Graph g1 = new Graph(edges, 12);
		// print graph
		System.out.println("Graph information: ");
		Graph.printGraph(g1);
		// create search class
		BFSearch s1 = new BFSearch(g1);
		System.out.println();
		// List vertexes visited to find the destination
		int src = 0;
		int des = 5;
		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
		s1.search(src, des);
	}

	@Override
	void printVertexPath() {
		// TODO Auto-generated method stub
		
	}

}

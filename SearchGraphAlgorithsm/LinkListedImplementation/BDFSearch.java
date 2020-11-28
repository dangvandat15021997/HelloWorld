package LinkListedImplementation;

import java.util.Arrays;
import java.util.List;

import LinkListedImplementation.Node.BDFSValue;
import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class BDFSearch extends SearchAbstract {
	int height;

	public BDFSearch(Graph graph, int height) {
		super(graph);
		this.height = height;
	}

	public BDFSearch() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void addToCloseList(Node new_node) {
		this.closeList.addToEnd(new_node);

	}

	@Override
	Node getFromCloseList() {
		return this.closeList.end();
	}

	@Override
	void addToOpenList(Node new_node) {
		Node<BDFSValue> newNode = new_node;
		if (newNode.getValue().isAddToEnd()) {
			this.openList.addToEnd(newNode);
		} else {
			this.openList.addToFirst(newNode);
		}

	}

	@Override
	Node getFromOpenList() {
		return this.openList.dequeue();
	}

	@Override
	void search(int src, int des) {
		this.openList = new MixLinkedList<BDFSValue>();
		this.closeList = new MixLinkedList<BDFSValue>();
		int curheight = 0;
		Node<BDFSValue> S = new Node(new BDFSValue(src, 0, curheight, true));
		addToOpenList(S);
		int ds = this.height;
		loop1: while (!this.openList.isEmpty()) {
			S = getFromOpenList();
			addToCloseList(new Node<BDFSValue>(S));
			int vertexS = S.getValue().getVertex();
			curheight = S.getValue().getHeight();
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
					int newNodeHeight = curheight + 1;
					if (!this.isInClose(vertex)) {
						if (curheight <= (ds - 1)) {
							addToOpenList(new Node<BDFSValue>(new BDFSValue(child.getValue(), newNodeHeight, false)));
						} else if (curheight == ds) {
							addToOpenList(new Node<BDFSValue>(new BDFSValue(child.getValue(), newNodeHeight, true)));
						} else {
							ds += this.height;
							if (height == 1) {
								addToOpenList(new Node<BDFSValue>(new BDFSValue(child.getValue(), newNodeHeight, true)));
							} else {
								addToOpenList(new Node<BDFSValue>(new BDFSValue(child.getValue(), newNodeHeight, false)));
							}
						}

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
		// init graph
		Graph g1 = new Graph(edges, 12);
		// print graph
		System.out.println("Graph information: ");
		Graph.printGraph(g1);
		// create search class
		BDFSearch s1 = new BDFSearch(g1, 2);
		System.out.println();
		// List vertexes visited to find the destination
		int src = 0;
		int des = 10;
		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
		s1.search(src, des);

	}

	@Override
	void printVertexPath() {
		// TODO Auto-generated method stub

	}

}

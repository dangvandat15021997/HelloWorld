package LinkListedImplementation;

import java.util.*;

import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

//class to store edges of the weighted graph
// Graph class
class Graph {
	// define adjacency list

	MixLinkedList<MixLinkedList<GraphValue>> adj_list = new MixLinkedList();

	public MixLinkedList<MixLinkedList<GraphValue>> getAdj_list() {
		return adj_list;
	}

	// Graph Constructor
	public Graph(MixLinkedList<Edge> edges, int numVertex) {
		// adjacency list memory allocation
		for (int i = 0; i < numVertex; i++)
			adj_list.addToEnd(new Node<MixLinkedList<GraphValue>>(new MixLinkedList()));

		// add edges to the graph
		
		for(int i = 0; i< edges.size(); i++) {
			Edge e = edges.get(i);
			adj_list.get(e.getSrc()).addToEnd(new Node<GraphValue>(new GraphValue(e.getDest(), e.getWeight())));
		}
	}

// print adjacency list for the graph
	public static void printGraph(Graph graph) {
		int list_size = graph.adj_list.size();
		System.out.println("The contents of the graph:");
		for (int i = 0; i < list_size; i++) {
			MixLinkedList<GraphValue> edges = graph.adj_list.get(i);
			for (int j = 0; j < edges.size(); j++) {
				System.out.println(
						"Vertex:" + i + " ==> " + edges.get(j).getVertex() + " (" + edges.get(j).getWeight() + ")");
			}

		}
	}

	public static void main(String[] args) {

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
	}

}

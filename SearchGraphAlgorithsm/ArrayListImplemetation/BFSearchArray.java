package ArrayListImplemetation;

import LinkListedImplementation.MixLinkedList;
import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class BFSearchArray extends SearchAbstractArray {

	public BFSearchArray(GraphArray graph) {
		super(graph);
		// TODO Auto-generated constructor stub
	}

	@Override
	void addToCloseList(GraphValue new_value) {
		this.closeList.addToEnd(new_value);
	}

	@Override
	GraphValue getFromCloseList() {
		return (GraphValue) this.closeList.first();
	}

	@Override
	void addToOpenList(GraphValue new_value) {
		this.openList.addToEnd(new_value);
	}

	@Override
	GraphValue getFromOpenList() {
		return (GraphValue) this.openList.dequeue();
	}

	@Override
	void search(int src, int des) {
		this.openList = new MixArrayList<GraphValue>(GraphValue.class, this.graph.getNumOfVertex());
		this.closeList = new MixArrayList<GraphValue>(GraphValue.class, this.graph.getNumOfVertex());
		GraphValue S = new GraphValue(src, 0);
		addToOpenList(S);
		loop1: while (!this.openList.isEmpty()) {
			S = getFromOpenList();
			addToCloseList(new GraphValue(S));
			int vertexS = S.getVertex();
			if (vertexS == des) {
				System.out.print(vertexS);
				break loop1;
			}
			System.out.print(vertexS + "=>");
			MixArrayList<GraphValue> listChild = this.graph.getChildValues(vertexS);
			if (listChild != null) {
				for (int i = 0; i < listChild.size(); i++) {
					GraphValue child = (GraphValue) listChild.get(i);
					int vertex = child.getVertex();
					if (!this.isInClose(vertex)) {
						addToOpenList(new GraphValue(child));
					}
				}
			}
		}
		System.out.println("");
	}

	@Override
	void printVertexPath() {
		// TODO Auto-generated method stub

	}
	
//	public static void main(String[] args) {
//		Edge[] edges = { new Edge(0, 1, 1), new Edge(0, 2, 3), new Edge(0, 3, 6),
//
//				new Edge(2, 4, 5), new Edge(2, 5, 2),
//
//				new Edge(3, 6, 1), new Edge(3, 7, 7), new Edge(3, 11, 2),
//
//				new Edge(6, 8, 3), new Edge(7, 9, 5), new Edge(9, 10, 7), };
//
//		// init graph
//		GraphArray g1 = new GraphArray(edges, 12);
//		// print graph
//		System.out.println("Graph information: ");
//		GraphArray.printGraph(g1);
//		// create search class
//		BFSearchArray s1 = new BFSearchArray(g1);
//		System.out.println();
//		// List vertexes visited to find the destination
//		int src = 0;
//		int des = 5;
//		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
//		s1.search(src, des);
////		System.out.println("The path from " + src + " to " + des + " is:");
////		s1.printVertexPath();
////		System.out.println();
//	}

}

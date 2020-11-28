package ArrayListImplemetation;

import LinkListedImplementation.DFSearch;
import LinkListedImplementation.MixLinkedList;
import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;


public class DFSearchArray extends SearchAbstractArray {

	public DFSearchArray(GraphArray graph) {
		super(graph);
		// TODO Auto-generated constructor stub
	}

	@Override
	void addToCloseList(GraphValue new_value) {
		this.closeList.addToEnd(new_value);
	}

	@Override
	GraphValue getFromCloseList() {
		return (GraphValue) this.closeList.end();
	}

	@Override
	void addToOpenList(GraphValue new_value) {
		this.openList.addToEnd(new_value);

	}

	@Override
	GraphValue getFromOpenList() {
		return (GraphValue) this.openList.pop();
	}

	@Override
	void search(int src, int des) {
		this.openList = new MixArrayList<GraphValue>(GraphValue.class, this.graph.getNumOfVertex());
		this.closeList = new MixArrayList<GraphValue>(GraphValue.class, this.graph.getNumOfVertex());
		this.reusultPath = new MixArrayList<GraphValue>(GraphValue.class, this.graph.getNumOfVertex());
		GraphValue S = new GraphValue(src, 0);
		addToOpenList(S);
		loop1: while (!this.openList.isEmpty()) {
			S = getFromOpenList();
			addToCloseList(new GraphValue(S));
			this.reusultPath.addToEnd(new GraphValue(S));
			int vertexS = S.getVertex();
			if (vertexS == des) {
				System.out.print(vertexS);
				break loop1;
			} else {
				System.out.print(vertexS + "=>");
				MixArrayList<GraphValue> listChild = (MixArrayList<GraphValue>) this.graph.getChildValues(vertexS);
				if (!listChild.isEmpty()) {
					for (int i = 0; i < listChild.size(); i++) {
						GraphValue child = listChild.get(i);
						int vertex = child.getVertex();
						if (!this.isInClose(vertex)) {
							addToOpenList(new GraphValue(child));
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
		GraphValue dropValue = (GraphValue) this.reusultPath.pop();
		int vertexDrop = dropValue.getVertex();
		GraphValue endValue = new GraphValue((GraphValue) this.reusultPath.end());
		int curVertex = endValue.getVertex();
		boolean loop = true;
		loop2: while (loop) {
			MixArrayList<GraphValue> childListTemp = (MixArrayList<GraphValue>) graph.getChildValues(curVertex);
			for (int i = 0; i < childListTemp.size(); i++) {
				GraphValue child = childListTemp.get(i);
				int vertex = child.getVertex();
				if ((vertex != vertexDrop) && (this.isInOpen(vertex))) {
					break loop2;
				}
			}
			dropValue = new GraphValue((GraphValue) this.reusultPath.pop());
			vertexDrop = dropValue.getVertex();
			endValue = new GraphValue((GraphValue) this.reusultPath.end());
			curVertex = endValue.getVertex();
		}
	}

	@Override
	void printVertexPath() {
		for (int i = 0; i < this.reusultPath.size(); i++) {
			GraphValue curValue = (GraphValue) this.reusultPath.get(i);
			if (i == (this.reusultPath.size() - 1)) {
				System.out.print(curValue.getVertex());
			} else {
				System.out.print(curValue.getVertex() + "=>");
			}
		}

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
//		DFSearchArray s1 = new DFSearchArray(g1);
//		System.out.println();
//		// List vertexes visited to find the destination
//		int src = 0;
//		int des = 10;
//		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
//		s1.search(src, des);
//		System.out.println("The path from " + src + " to " + des + " is:");
//		s1.printVertexPath();
//		System.out.println();
//	}


}

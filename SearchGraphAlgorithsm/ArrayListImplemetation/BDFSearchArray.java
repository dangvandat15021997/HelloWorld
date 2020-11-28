package ArrayListImplemetation;

import LinkListedImplementation.MixLinkedList;
import LinkListedImplementation.Node.BDFSValue;
import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class BDFSearchArray extends SearchAbstractArray {
	int height;

	public BDFSearchArray(GraphArray graph, int height) {
		super(graph);
		this.height = height;
	}

	@Override
	void addToCloseList(GraphValue new_value) {
		this.closeList.addToEnd(new_value);

	}

	@Override
	GraphValue getFromCloseList() {
		return (BDFSValue) this.closeList.end();
	}

	@Override
	void addToOpenList(GraphValue new_value) {
		BDFSValue newValue = (BDFSValue) new_value;
		if (newValue.isAddToEnd()) {
			this.openList.addToEnd(newValue);
		} else {
			this.openList.addToFirst(newValue);
		}
	}

	@Override
	GraphValue getFromOpenList() {
		return (BDFSValue) this.openList.dequeue();
	}

	@Override
	void search(int src, int des) {
		this.openList = new MixArrayList<BDFSValue>(BDFSValue.class, this.graph.getNumOfVertex());
		this.closeList = new MixArrayList<BDFSValue>(BDFSValue.class, this.graph.getNumOfVertex());
		int curheight = 0;
		BDFSValue S = new BDFSValue(src, 0, curheight, true);
		addToOpenList(S);
		int ds = this.height;
		loop1: while (!this.openList.isEmpty()) {
			S = (BDFSValue) getFromOpenList();
			addToCloseList(new BDFSValue(S));
			int vertexS = S.getVertex();
			curheight = S.getHeight();
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
					int newNodeHeight = curheight + 1;
					if (!this.isInClose(vertex)) {
						if (curheight <= (ds - 1)) {
							addToOpenList(new BDFSValue(new BDFSValue(child, newNodeHeight, false)));
						} else if (curheight == ds) {
							addToOpenList(new BDFSValue(new BDFSValue(child, newNodeHeight, true)));
						} else {
							ds += this.height;
							if (height == 1) {
								addToOpenList(
										new BDFSValue(new BDFSValue(child, newNodeHeight, true)));
							} else {
								addToOpenList(
										new BDFSValue(new BDFSValue(child, newNodeHeight, false)));
							}
						}

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
//		BDFSearchArray s1 = new BDFSearchArray(g1, 2);
//		System.out.println();
//		// List vertexes visited to find the destination
//		int src = 0;
//		int des = 10;
//		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
//		s1.search(src, des);
////		System.out.println("The path from " + src + " to " + des + " is:");
////		s1.printVertexPath();
////		System.out.println();
//	}

}

package ArrayListImplemetation;

import LinkListedImplementation.MixLinkedList;
import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class UCSearchArray extends SearchAbstractArray {

	public UCSearchArray(GraphArray graph) {
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
		int weightNewValue = new_value.getWeight();
		int size = this.openList.size();
		GraphValue[] tempList = (GraphValue[]) this.openList.getArr();
		if (size == 0) {
			this.openList.addToFirst(new_value);
		} else {
			for (int i = 0; i < size; i++) {
				GraphValue curValue = (GraphValue) this.openList.get(i);
				int weightCurNode = curValue.getWeight();
				if ((i == 0) && (weightCurNode >= weightNewValue)) {
					this.openList.addToFirst(new_value);
					break;
				}
				if (weightCurNode >= weightNewValue) {
					for (int j = (size - 1); j >= i; j--) {
						tempList[j + 1] = tempList[j];
					}
					tempList[i] = new_value;
					this.openList.setSize(++size);
					break;
				}
				if (i == (size - 1)) {
					if (weightCurNode <= weightNewValue) {
						this.openList.addToEnd(new_value);
						break;
					} else {
						for (int j = (size - 1); j >= i; j--) {
							tempList[j + 1] = tempList[j];
						}
						tempList[i] = new_value;
						this.openList.setSize(++size);
						break;
					}
				}
			}
		}

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
			printOpenList();
			S = getFromOpenList();
			addToCloseList(new GraphValue(S));
			int vertexS = S.getVertex();
			int weightS = S.getWeight();
			System.out.println(": => " + "(" + vertexS + "," + weightS + ")");
			if (vertexS == des) {

				break loop1;
			}
			MixArrayList<GraphValue> listChild = this.graph.getChildValues(vertexS);
			if (listChild != null) {
				for (int i = 0; i < listChild.size(); i++) {
					GraphValue child = (GraphValue) listChild.get(i);
					int vertex = child.getVertex();
					int weight = child.getWeight();
					weight += weightS;
					GraphValue tempValue = new GraphValue(vertex, weight);
					if (!this.isInClose(vertex)) {
						addToOpenList(new GraphValue(tempValue));
					}
				}
			}
		}
		System.out.println("");

	}

	private void printOpenList() {
		for (int i = 0; i < this.openList.size(); i++) {
			GraphValue curValue = (GraphValue) this.openList.get(i);
			if (i == (this.openList.size() - 1)) {
				System.out.print("(" + curValue.getVertex() + "," + curValue.getWeight() + ")");
			} else {
				System.out.print("(" + curValue.getVertex() + "," + curValue.getWeight() + ")" + " , ");

			}

		}
	}

	@Override
	void printVertexPath() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Edge[] edges = { new Edge(0, 1, 1), new Edge(0, 2, 3), new Edge(0, 3, 6),

				new Edge(2, 4, 5), new Edge(2, 5, 2),

				new Edge(3, 6, 1), new Edge(3, 7, 7), new Edge(3, 11, 2),

				new Edge(6, 8, 3), new Edge(7, 9, 5), new Edge(9, 10, 7), };

		// init graph
		GraphArray g1 = new GraphArray(edges, 12);
		// print graph
		System.out.println("Graph information: ");
		GraphArray.printGraph(g1);
		// create search class
		UCSearchArray s1 = new UCSearchArray(g1);
		System.out.println();
		// List vertexes visited to find the destination
		int src = 0;
		int des = 10;
		System.out.println("List vertexes visited from " + src + " to " + des + " are:");
		s1.search(src, des);

	}

}

package ArrayListImplemetation;

import LinkListedImplementation.Node.GraphValue;

public abstract class SearchAbstractArray {
	GraphArray graph;
	MixArrayList openList;
	MixArrayList closeList;
	MixArrayList reusultPath;

	public MixArrayList getReusultPath() {
		return reusultPath;
	}

	public SearchAbstractArray(GraphArray graph) {
		this.graph = graph;
	}

	abstract void addToCloseList(GraphValue new_value);

	abstract GraphValue getFromCloseList();

	abstract void addToOpenList(GraphValue new_value);

	abstract GraphValue getFromOpenList();

	abstract void search(int src, int des);

	abstract void printVertexPath();

	boolean isInOpen(int v) {
		return this.openList.isInList(v);
	}

	boolean isInClose(int v) {
		return this.closeList.isInList(v);
	}

}

package LinkListedImplementation;

import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public abstract class SearchAbstract {
	Graph graph;
	MixLinkedList openList;
	MixLinkedList closeList;
	MixLinkedList reusultPath;

	public MixLinkedList getReusultPath() {
		return reusultPath;
	}

	public SearchAbstract(Graph graph) {
		this.graph = graph;
	}

	public SearchAbstract() {
		;
	}

	abstract void addToCloseList(Node new_node);

	abstract Node getFromCloseList();

	abstract void addToOpenList(Node new_node);

	abstract Node getFromOpenList();

	abstract void search(int src, int des);

	abstract void printVertexPath();

	boolean isInOpen(int v) {
		return this.openList.isInList(v);
	}

	boolean isInClose(int v) {
		return this.closeList.isInList(v);
	}

}

package ArrayListImplemetation;

import LinkListedImplementation.Node.Edge;
import LinkListedImplementation.Node.GraphValue;

public class GraphArray {
	private int[][] adj_matrix;
	private int numOfVertex;

	public int getNumOfVertex() {
		return numOfVertex;
	}

	public void setNumOfVertex(int numOfVertex) {
		this.numOfVertex = numOfVertex;
	}

	public int[][] getAdj_matrix() {
		return adj_matrix;
	}

	public void setAdj_matrix(int[][] adj_matrix) {
		this.adj_matrix = adj_matrix;
	}

	public GraphArray(Edge[] edges, int numOfVertex) {
		this.adj_matrix = new int[numOfVertex][numOfVertex];
		this.numOfVertex = numOfVertex;
		// fullfill with 0
		for (int i = 0; i < numOfVertex; i++) {
			for (int j = 0; j < numOfVertex; j++) {
				this.adj_matrix[i][j] = 0;
			}
		}
		// assign weight for edge
		for (int i = 0; i < edges.length; i++) {
			Edge edge = edges[i];
			adj_matrix[edge.getSrc()][edge.getDest()] = edge.getWeight();
		}
	}

	public static void printGraph(GraphArray graph) {
		int[][] adj_matrix = graph.getAdj_matrix();
		System.out.println("The contents of the graph:");
		for (int i = 0; i < adj_matrix.length; i++) {
			for (int j = 0; j < adj_matrix.length; j++) {
				if (adj_matrix[i][j] != 0) {
					System.out.println("Vertex:" + i + " ==> " + j + " (" + adj_matrix[i][j] + ")");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Edge[] edges = {
				new Edge(0, 1, 1),
				new Edge(0, 2, 3),
				new Edge(0, 3, 6),
				
				new Edge(2, 4, 5),
				new Edge(2, 5, 2),
				
				new Edge(3, 6, 1),
				new Edge(3, 7, 7),
				new Edge(3, 11, 2),
				
				new Edge(6, 8, 3),
				new Edge(7, 9, 5),
				new Edge(9, 10, 7),
		};
		
		GraphArray g1 = new GraphArray(edges, 12);
		GraphArray.printGraph(g1);
	}
	
	public MixArrayList<GraphValue> getChildValues(int vertex) {
		MixArrayList<GraphValue> result_arr =  new MixArrayList<GraphValue>(GraphValue.class, this.numOfVertex);
		for(int i = 0; i < this.adj_matrix[vertex].length; i++) {
			int des = this.adj_matrix[vertex][i];
			if(des != 0) {
				result_arr.addToEnd(new GraphValue(i, des));
			}
		}		
		return result_arr;
	}

}

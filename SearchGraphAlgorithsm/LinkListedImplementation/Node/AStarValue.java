package LinkListedImplementation.Node;

public class AStarValue extends GraphValue {
	private int heuristic;
	
	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public AStarValue(int vertex, int weight, int heuristic) {
		super(vertex, weight);
		// TODO Auto-generated constructor stub
	}

	public AStarValue(GraphValue v1, int heuristic) {
		super(v1);
		this.heuristic = heuristic;
	}	
	
	

}

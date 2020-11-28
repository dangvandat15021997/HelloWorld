package LinkListedImplementation.Node;

public class GraphValue {
	 int vertex, weight;
	
	public GraphValue(int vertex, int weight) {	
		this.vertex = vertex;
		this.weight = weight;
	}
	
	public GraphValue(GraphValue v1) {
		this.vertex = v1.getVertex();
		this.weight = v1.getWeight();
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {		
		return (this.vertex + " " + this.weight);
	}
	

}

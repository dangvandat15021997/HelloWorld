package LinkListedImplementation.Node;

public class TreeValue extends GraphValue{
	 int height;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TreeValue(int vertex, int weight, int height) {
		super(vertex,  weight);
		this.height = height;
	}
	
	public TreeValue(TreeValue v1) {
		super(v1);
		this.height = v1.getHeight();
	}
	
	

}

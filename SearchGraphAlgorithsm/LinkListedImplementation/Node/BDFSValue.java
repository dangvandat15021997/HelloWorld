package LinkListedImplementation.Node;

public class BDFSValue extends GraphValue {
	boolean addToEnd;
	int height;
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isAddToEnd() {
		return addToEnd;
	}

	public void setAddToEnd(boolean addToEnd) {
		this.addToEnd = addToEnd;
	}

	public BDFSValue(int vertex, int weight, int height, boolean addToEnd) {
		super(vertex, weight);
		this.height = height;
		this.addToEnd = addToEnd;
	}

	
	public BDFSValue(GraphValue v1, int height, boolean addToEnd) {
		super(v1);
		this.addToEnd = addToEnd;
		this.height = height;
	}
	
	public BDFSValue(BDFSValue v1) {
		super(v1.getVertex(), v1.getHeight());
		this.addToEnd = v1.isAddToEnd();
		this.height = v1.getHeight();
	}

}

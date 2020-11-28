package ArrayListImplemetation;

import LinkListedImplementation.MixLinkedList;
import LinkListedImplementation.Node.GraphValue;
import LinkListedImplementation.Node.Node;

public class MixArrayList<T> {
	private T[] arr;
	private int size;

	public T[] getArr() {
		return arr;
	}

	public void setArr(T[] arr) {
		this.arr = arr;
	}


	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MixArrayList() {
		size = 0;
		arr = null;
	}

	public MixArrayList(Class<T>dataType, int length) {
		arr = (T[]) java.lang.reflect.Array.newInstance(dataType, length);
		size = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = null;
		}
	}

	public T get(int index) {
		T result = null;
		for (int i = 0; i < arr.length; i++) {
			if (i == index) {
				return arr[i];
			}
		}
		return result;
	}

	// add to end poiter
	public void addToEnd(T value) {		
			if (arr[size] == null) {
				arr[size] = value;
				size++;
			}
	}

	// add to end poiter
	public void addToFirst(T value) {
		for (int i = this.size - 1; i >= 0; i--) {
			arr[i + 1] = arr[i];
		}

		arr[0] = value;
		size++;
	}

	public T dequeue() {
		T result = null;
		if (size >= 1) {
			result = arr[0];
			for (int i = 0; i < size; i++) {
				arr[i] = arr[i + 1];
			}
			size--;
		}
		return result;
	}

	public T pop() {
		T result = null;
		if (size >= 1) {
			result = arr[size - 1];
			arr[size - 1] = null;
			size--;
		}
		return result;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public T first() {
		return arr[0];
	}

	public T end() {
		return arr[size - 1];
	}
	
	public boolean isInList(int v) {
		for(int i = 0; i < size; i++) {
			GraphValue curValue = (GraphValue) arr[i];
			int curVertex = curValue.getVertex();
			if(curVertex == v) {
				return true;
			}
		}
		return false;
	}

	public static void printArr(MixArrayList<GraphValue> arr) {
		for (int i = 0; i < arr.getSize(); i++) {
			int vertex = arr.getArr()[i].getVertex();
			int weight = arr.getArr()[i].getWeight();
			System.out.println(vertex + " " + weight);
		}
	}

//	public static void main(String[] args) {
//		//create array
//		MixArrayList<GraphValue> arr1 = new MixArrayList<GraphValue>(GraphValue.class, 12);
//		arr1.addToEnd(new GraphValue(0, 1));
//		arr1.addToEnd(new GraphValue(1, 1));
//		arr1.addToEnd(new GraphValue(2, 1));
//		arr1.addToEnd(new GraphValue(3, 1));
//		System.out.println("OG array");
//		MixArrayList.printArr(arr1);
//		// Get first
//		System.out.println("Get First");
//		int vertex = arr1.first().getVertex();
//		int weight = arr1.first().getWeight();
//		System.out.println(vertex + " " + weight);
//		// get end
//		System.out.println("Get End");
//		 vertex = arr1.end().getVertex();
//		 weight = arr1.end().getWeight();
//		System.out.println(vertex + " " + weight);
//		
//		// addtoEnd
//		arr1.addToEnd(new GraphValue(4, 1));
//		System.out.println();
//		System.out.println("add to end");
//		MixArrayList.printArr(arr1);
//		// Dequeue
//		System.out.println();
//		System.out.println("Dequeue");
//		System.out.println("Pick up value: " + arr1.dequeue());
//		System.out.println();
//		MixArrayList.printArr(arr1);
//		System.out.println();
//		// pop 
//		System.out.println(arr1.getSize());
//		System.out.println("Pick up value: " + arr1.pop());
//		System.out.println();
//		System.out.println(arr1.getSize());
//		System.out.println("Pop");
//		MixArrayList.printArr(arr1);
//	}

}

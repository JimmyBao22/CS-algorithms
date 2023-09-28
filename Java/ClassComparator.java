
import java.util.*;
import java.io.*;

public class ClassComparator {
	public static void main(String[] args) {
		ExampleClass[] arr = new ExampleClass[2];
		arr[0] = new ExampleClass(1);
		arr[1] = new ExampleClass(2);

		// method 2: create new comparator passed into sort function
		Arrays.sort(arr, new Comparator<ExampleClass>() {
			public int compare(ExampleClass a, ExampleClass b) {
				return a.x - b.x;
		    }
		});
		

		Arrays.sort(arr, new Compare());
	}
	
	// method 3: implements Comparator<>
	public static class Compare implements Comparator<ExampleClass> {
		public int compare(ExampleClass a, ExampleClass b) {
			return a.x - b.x;
		}
	}

	// method 1: implements Comparable<>
	public static class ExampleClass implements Comparable<ExampleClass> {
		int x;
		
		ExampleClass (int x) {
			this.x = x;
		}
		
		public int compareTo(ExampleClass other) {
			return this.x - other.x;
		}
	}
}
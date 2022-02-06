
import java.util.*;
import java.io.*;

public class ClassComparator {
	public static void main(String[] args) {
		A[] arr = new A[2];
		arr[0] = new A(1);
		arr[1] = new A(2);
		Arrays.sort(arr, new Comparator<A>() {
			public int compare(A a, A b) {
				return a.x - b.x;
		    }
		});
		
		Arrays.sort(arr, new C());
		
	}
	
	public static class C implements Comparator<A> {
		public int compare(A a, A b) {
			return a.x - b.x;
		}
	}
	
	public static class A implements Comparable<A> {
		int x;
		
		A(int x) {
			this.x = x;
		}
		
		public int compareTo(A other) {
			return this.x - other.x;
		}
	}
}

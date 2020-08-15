
import java.util.*;
import java.io.*;

public class priorityqueue {
	public static void main(String[] args) {
		
		PriorityQueue<int[]> a = new PriorityQueue<>(
	    		new Comparator<int[]>() {
	    			public int compare(int[] x, int[] y) {
	    				return x[0]-y[0];
	    			}
	    });
		
	}
}

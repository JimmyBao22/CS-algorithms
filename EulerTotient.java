
import java.util.*;
import java.io.*;

public class EulerTotient {
	
	private static int max = (int)1e5;
	private static int[] arr = new int[max];
	
	public static void totient() {
		for (int i=1; i<max; i++) {
			arr[i] = i;
		}
		for (int i=2; i<max; i+=2) {
			arr[i] = arr[i]/2;
		}
		for (int i=3; i<max; i+=2) {
			if (arr[i] == i) {
				for (int j= i; j < max; j+= i) {
					arr[j] /= i; arr[j] *= (i-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		totient();
		
		for (int i=0; i<100; i++) System.out.println(arr[i]);
		
	}
}

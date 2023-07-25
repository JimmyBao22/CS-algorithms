
import java.util.*;
import java.io.*;

public class CountingSort {

	public static void main(String[] args) {

	}
	
	// O(n+maxval)
	public static void sort(int[] arr, int maxval) {
		int n = arr.length;
		int[] output = new int[n];
		int[] count = new int[maxval+1];
		
		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}
		for (int i = 1; i <= maxval; i++) {
			count[i] += count[i-1];
		}
		for (int i = n-1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}
}
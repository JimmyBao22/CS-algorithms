
import java.util.*;
import java.io.*;

public class CountingSort {

	public static void main(String[] args) {

	}
	
	// O(n + maxVal)
	public static void countingSort(int[] arr, int maxVal) {
		int n = arr.length;
		int[] sortedArr = new int[n];
		int[] count = new int[maxVal + 1];
		
		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}
		for (int i = 1; i <= maxVal; i++) {
			count[i] += count[i-1];
		}
		for (int i = n-1; i >= 0; i--) {
			sortedArr[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}
		for (int i = 0; i < n; i++) {
			arr[i] = sortedArr[i];
		}
	}
}
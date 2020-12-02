
import java.util.*;
import java.io.*;

public class Permutation {

	public static void main(String[] args) {
		combination_size("ABC".toCharArray(), new ArrayList<>(), 0, 2);
	}
		
	// ABC --> [A, B, C], [A, C, B], [B, A, C], [B, C, A], [C, B, A], [C, A, B]
	public static void permutation(char[] arr, int i) {
		if (i == arr.length) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int j=i; j<arr.length; j++) {
			swap(arr, i, j);
			permutation(arr, i+1);
			swap(arr, i, j);		// undo
		}
	}
	
	public static void swap(char[] arr, int a, int b) {
		char temp = arr[a]; arr[a] = arr[b]; arr[b] = temp; 
	}
	
	// ABC --> 	[], [A], [B], [C], [A, B], [A, C], [B, C], [A, B, C]
	public static void combination(char[] arr, ArrayList<Character> include, int i) {
		if (i == arr.length) {
			System.out.println(include);
			return;
		}
		combination(arr, include, i+1);		// don't include
		include.add(arr[i]); 	// include
		combination(arr, include, i+1);
		include.remove(include.size()-1);		// undo
	}
	
	// ABC (length_needed = 2) --> [A, B], [A, C], [B, C]
	public static void combination_size(char[] arr, ArrayList<Character> include, int i, int length_needed) {
		if (include.size() == length_needed) {
			System.out.println(include);
			return;
		}
		if (i >= arr.length || arr.length - i + include.size() < length_needed) return;
		combination_size(arr, include, i+1, length_needed);		// don't include
		include.add(arr[i]); 	// include
		combination_size(arr, include, i+1, length_needed);
		include.remove(include.size()-1);		// undo
	}
}
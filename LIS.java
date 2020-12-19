
import java.util.*;
import java.io.*;

public class LIS {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Badge"));

		int n = Integer.parseInt(in.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=0; i<n; i++) {
			Integer higher = set.ceiling(arr[i]);
			if (higher != null) set.remove(higher);
			set.add(arr[i]);
		}
		
		// LIS = set.size();
		
	}
}

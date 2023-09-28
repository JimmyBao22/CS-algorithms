
import java.util.*;
import java.io.*;

public class LargestRectangleinHistogram {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("LargestRectangle"));

		int n = Integer.parseInt(in.readLine());
		int[] heights = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayDeque<Integer> s = new ArrayDeque<>();
		int maxArea = 0;
		int i = 0;
		while (i < n) {
			if (s.isEmpty() || heights[i] > heights[s.peek()]) {
				s.push(i++);
			} 
			else {
				int cur = s.pop();
				int left = 0;
				if (!s.isEmpty()) {
					left = s.peek() + 1;
				}
				int right = i - 1;
				maxArea = Math.max(maxArea, heights[cur] * (right - left + 1));
			}
		}
		
		while (!s.isEmpty()) {
			int cur = s.pop();
			int left = 0;
			if (!s.isEmpty()) {
				left = s.peek() + 1;
			}
			int right = n - 1;
			maxArea = Math.max(maxArea, heights[cur] * (right - left + 1));
		}
        
	}
}

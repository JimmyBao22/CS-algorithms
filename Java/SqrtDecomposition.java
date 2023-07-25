
import java.util.*;
import java.io.*;

public class SqrtDecomposition {

	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SqrtDecomposition"));
		
		n = Integer.parseInt(in.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}	
		
	}
	
	static class SqrtDecomp {
		
		int len;				// size of blocks and # of blocks
		int[] b;
		
		public SqrtDecomp() {
			len = (int) (Math.sqrt(n) + 1);
			b = new int[len];
			for (int i = 0; i < n; i++) b[i/len] += arr[i];
		}
		
		// set index i
		public void update(int i, int x) {
			int block = i / len;
			b[block] += x - arr[i];
			arr[i] = x;
		}
		
		// query l to r
		public int query(int l, int r) {
			int sum=0;
			int left = l / len; 
			int right = r / len;
			if (left == right) {
				for (int i = l; i <= r; i++) sum += arr[i];
			}
			else {
				for (int i = l; i <= (left + 1) * len - 1; i++) sum += arr[i];
				for (int i = left + 1; i <= right - 1; i++) sum += b[i];
				for (int i = right * len; i <= r; i++) sum += arr[i];
			}
			return sum;
		}
		
		// update l to r
		public void update_seg(int l, int r, int x) {
			int left = l / len; int right = r / len;
			if (left == right) {
				for (int i = l; i <= r; i++) arr[i] += x;
			}
			else {
				for (int i = l; i <= (left + 1) * len - 1; i++) arr[i] += x;
				for (int i = left + 1; i <= right - 1; i++) b[i] += x;
				for (int i = right * len; i <= r; i++) arr[i] += x;
			}
		}
		
		// query index i
		public int query_index(int i) {
			return arr[i] + b[i/len];
		}
	}
	
	public static void MO(A[] queries, int q, int len) {
		Arrays.parallelSort(queries, new Comparator<A>() {
			public int compare(A a, A b) {
				if (a.left/len == b.left/len) {
					return a.right - b.right;
				}
				else {
					return a.left/len - b.left/len;
				}
			}
		});
		
		int[] answer = new int[q];
		int ans = 0;
		int left = 0;
		int right = 0;
		int[] count = new int[(int)(1e6+1)];
		ans = add(arr[0], count, ans);
		for (int i = 0; i < q; i++) {
			while (left < queries[i].left) {
				ans = remove(arr[left], count, ans);
				left++;
			}
			while (left > queries[i].left) {
				left--;
				ans = add(arr[left], count, ans);
			}
			while (right < queries[i].right) {
				right++;
				ans = add(arr[right], count, ans);
			}
			while (right > queries[i].right) {
				ans = remove(arr[right], count, ans);
				right--;
			}
			answer[queries[i].index]= ans;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			sb.append(answer[i] + "\n");
		}
		System.out.print(sb);
	}
	
	public static int add(int index, int[] arr, int ans) {
		arr[index]++;
		if (arr[index] == 1) ans++;
		return ans;
	}
	
	public static int remove(int index, int[] arr, int ans) {
		arr[index]--;
		if (arr[index] == 0) ans--;
		return ans;
	}
	
	static class A {
		int left, right, index;
		A (int a, int b, int c) {
			left = a; right = b; index = c;
		}
	}
}

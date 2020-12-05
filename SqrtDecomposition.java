
import java.util.*;
import java.io.*;

public class SqrtDecomposition {

	static int n;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SqrtDecomposition"));
		
		n = Integer.parseInt(in.readLine());
		arr = new long[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}	
		
	}
	
	static class SqrtDecomp {
		
		int len;				// size of blocks and # of blocks
		long[] b;
		
		public SqrtDecomp() {
			len = (int) (Math.sqrt(n) + 1);
			b = new long[len];
			for (int i=0; i<n; i++) b[i/len] += arr[i];
		}
		
		public void update(int i, long x) {
			int block = i/len;
			b[block] += x - arr[i];
			arr[i] = x;
		}
		
		public long query(int l, int r) {
			long sum=0;
			int left = l/len; int right = r/len;
			if (left == right) {
				for (int i=l; i<=r; i++) sum += arr[i];
			}
			else {
				for (int i=l; i<=(left + 1)*len - 1; i++) sum += arr[i];
				for (int i=left+1; i<=right-1; i++) sum += b[i];
				for (int i=right*len; i<=r; i++) sum += arr[i];
			}
			return sum;
		}
		
		public void update_seg(int l, int r, long x) {
			int left = l/len; int right = r/len;
			if (left == right) {
				for (int i=l; i<=r; i++) arr[i] += x;
			}
			else {
				for (int i=l; i<=(left + 1)*len - 1; i++) arr[i] += x;
				for (int i=left+1; i<=right-1; i++) b[i] += x;
				for (int i=right*len; i<=r; i++) arr[i] += x;
			}
		}
		
		public long query_index(int i) {
			return arr[i] + b[i/len];
		}
	}
}

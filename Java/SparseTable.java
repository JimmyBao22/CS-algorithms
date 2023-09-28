
import java.util.*;
import java.io.*;

public class SparseTable {

	static int n;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SparseTable"));
		
		n = Integer.parseInt(in.readLine());
		arr = new long[n];
		

	}
	
	static class SparsetableRMQ {
		
		int len;
		long[][] st;
		
		public SparsetableRMQ() {
			len = 32;
			st = new long[len][n];
			
			for (int i=0; i<n; i++) {
				st[0][i] = arr[i];
			}
			
			for (int log = 1; (1 << log) <= n; log++) {
				for (int i = 0; i + (1 << log) < n+1; i++) {
					st[log][i] = Math.min(st[log-1][i], st[log-1][i + (1 << (log-1))]);
				}
			}
		}
		
		// query l to r
		public long query(int l, int r) {
			int j = log2(r - l + 1);
			return Math.min(st[j][l], st[j][r - (1 << j) + 1]);
		}
		
		public int log2(int n) {
			return 31 - Integer.numberOfLeadingZeros(n);
		}
	}
	
	static class Sparsetable {
		int len;
		long[][] st;
		
		public Sparsetable() {
			len = 32;
			st = new long[len][n];

			for (int i = 0; i < n; i++) {
				st[0][i] = arr[i];
			}
			
			for (int log = 1; (1 << log) <= n; log++) {
				for (int i = 0; i + (1 << log) < n+1; i++) {
					st[log][i] = st[log-1][i] + st[log-1][i + (1 << (log-1))];
				}
			}
		}
		
		// query l to r
		public long query(int l, int r) {
			long sum=0;
			for (int log = len; log >= 0; log--) {
				if ((1 << log) <= r - l + 1) {
					sum += st[log][l];
					l += (1 << log);
				}
			}
			return sum;
		}
	}
}
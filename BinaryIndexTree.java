
import java.util.*;
import java.io.*;

public class BinaryIndexTree {
	
	public static void main(String[] args) {

	}
	
	
	static class BIT {
		int n;
		long[] f;	// 1 base indexing
		BIT (int n) {
			this.n = n; f = new long[n+1];
		}
		
		// sum from i to 0
		long sum (int i) {		
			i++;
			long ret=0;
			while (i>0) {
				ret += f[i];
				i -= i&-i;
			}
			return ret;
		}

		// sum from l to r
		long sum (int l, int r) {	
			return sum(r) - sum(l-1);
		}
		
		// add value to index i
		void add(int i, long value) {	
			i++;
			while (i<=n) {
				f[i] += value;
				i += i&-i;
			}
		}

		// add value to indices l to r --> arr[i] = sum(i);
		void range_add(int l, int r, long value) {	
			add(l,value); add(r+1,-value);
		}
	}
	
	static class BIT2D {
		int n,m;
		long[][] f;		// 1 base indexing
		BIT2D(int n, int m) {
			this.n = n; this.m = m; f = new long[n+1][m+1];
		}
		
		// sum from (i,j) to (0,0)
		long sum (int i, int j) {
			i++; j++;
			long ret=0;
			while (i>0) {
				int y=j;
				while (y>0) {
					ret += f[i][y];
					y -= y&-y;
				}
				i -= i&-i;
			}
			return ret;
		}
		
		// sum from (i1, j1) to (i2, j2)
		long sum(int i1, int j1, int i2, int j2) {
			return sum(i2, j2) - sum(i1-1, j2) - sum(i2, j1-1) + sum(i1-1, j1-1);
		}
		
		// add value to (i,j)
		void add(int i, int j, long val) {
			i++; j++;
			while (i<=n) {
				int y=j;
				while (y<=m) {
					f[i][y] += val;
					y += y&-y;
				}
				i += i&-i;
			}
		}
		
		// add value from (i1, j1) to (i2, j2) --> arr[i][j] = sum(i, j);
		void range_add(int i1, int j1, int i2, int j2, long val) {
			add(i1, j1, val);
			add(i1, j2+1, -val);
			add(i2+1, j1, -val);
			add(i2+1, j2+1, val);
		}
	}
}

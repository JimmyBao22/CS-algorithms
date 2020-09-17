
import java.util.*;
import java.io.*;

public class BinaryIndexTree {

	// https://cp-algorithms.com/data_structures/fenwick.html
	
	public static void main(String[] args) {

	}
	
	
	static class BIT {
		int n;
		long[] f;	// 1 base indexing
		BIT (int n) {
			this.n = n; f = new long[n+1];
		}
		
		long sum (int i) {		// sum from 0 to i
			long ret=0; i++;
			while (i>0) {
				ret += f[i];
				i -= i&-i;
			}
			return ret;
		}
		
		long sum (int l, int r) {	// sum from l to r
			l++; r++;
			return sum(r) - sum(l-1);
		}
		
		void add(int i, long value) {	// add value to index i
			i++;
			while (i<=n) {
				f[i] += value;
				i += i&-i;
			}
		}
		
		void add(int l, int r, long value) {	// add value to indices l to r
			l++; r++;
			add(l,value); add(r+1,-value);
		}
	}
}

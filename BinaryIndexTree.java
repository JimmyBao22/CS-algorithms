
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
		
		// sum from l to r
		long sum (int i) {		
			long ret=0; i++;
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
		void set(int i, long value) {	
			i++;
			while (i<=n) {
				f[i] += value;
				i += i&-i;
			}
		}

		// add value to indices l to r
		void set(int l, int r, long value) {	
			set(l,value); set(r+1,-value);
		}
	}
}

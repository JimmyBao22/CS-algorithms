import java.util.*;
import java.io.*;

public class SegmentTree {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SegmentTree"));
		
		int n = Integer.parseInt(in.readLine());
		SegTree segTree = new SegTree(n); 
		
	}
	
	static class SegTree {
		int size = 1;
		long[] tree;
		
		public SegTree(int n) {			
			while (size < n) size <<= 1;
			tree = new long[size << 1];
		}
		
		// random computation on segment (l to r-1)
		public long compSeg(int l, int r) { return compSeg(l, r, 0, 0, size); }
		
		public long compSeg(int l, int r, int x, int lx, int rx) {
			if (lx >= r || rx <= l) return 0;									// do not intersect this segment
			if (l <= lx && rx <= r) return tree[x];								// inside whole segment
			int m = (lx + rx) >> 1;
			long one = compSeg(l, r, (x<<1)+1, lx, m); 
			long two = compSeg(l, r, (x<<1)+2, m, rx);
			return one + two;
		}
		
		public void set(int i, long v) { set(i, v, 0, 0, size); }			// arr[i] = v;
		
		public void set(int i, long v, int x, int lx, int rx) {
			if (rx - lx == 1) {													// in leaf node aka bottom level
				tree[x] = v; return;
			}
			int m = (lx + rx) >> 1;
			if (i < m) set(i, v, (x<<1)+1, lx, m); 							// go to left subtree
			else set(i, v, (x<<1)+2, m, rx);									// go to right subtree
			tree[x] = tree[(x<<1)+1] + tree[(x<<1)+2];
		}
		
		// random computation on index
		public long compIndex(int i) { return compIndex(i, 0, 0, size); }
		
		public long compIndex(int i, int x, int lx, int rx) {
			if (rx - lx == 1) return tree[x];									// in leaf node aka bottom level
			// propogate(x, lx, rx);
			int m = (lx + rx) >> 1;	
			long result = 0;
			if (i < m) result = compIndex(i, (x<<1)+1, lx, m);						// go to left subtree
			else result = compIndex(i, (x<<1)+2, m, rx);							// go to right subtree
			return result + tree[x];
		}
				
		// change segment (l to r-1)
		public void modify(int l, int r, long v) { modify(l, r, v, 0, 0, size); }
		
		public void modify(int l, int r, long v, int x, int lx, int rx) {
			if (lx >= r || rx <= l) return;										// do not intersect this segment
			// propogate(x, lx, rx);
			if (l <= lx && rx <= r) {											// inside whole segment
				tree[x] += v;
				return;
			}
			int m = (lx + rx) >> 1;
			modify(l, r, v, (x<<1)+1, lx, m);
			modify(l, r, v, (x<<1)+2, m, rx);
		}
		
		static long NONE = (long)(1e18);	// use a value that will never be used
				// if all values can be used, use a boolean array to see if tree[x]
				// has a value stored or not
		public void propogate(int x, int lx, int rx) {
			if (rx - lx == 1) return;		// leaf
			if (tree[x] != NONE) {
				tree[(x<<1)+1] = tree[x];
				tree[(x<<1)+2] = tree[x];
				tree[x] = NONE;
			}
		}
		
		public void build(long[] arr) {	build(arr, 0, 0, size); }			// arr is the orig arr
		
		public void build(long[] arr, int x, int lx, int rx) {
			if (rx - lx == 1) {													// in leaf node aka bottom level
				if (lx < arr.length) tree[x] = arr[lx];
				return;
			}
			int m = (lx + rx) >> 1;
			build(arr, (x<<1)+1, lx, m);
			build(arr, (x<<1)+2, m, rx);
			tree[x] = tree[(x<<1)+1] + tree[(x<<1)+2];
		}
		
		public void print() {
			System.out.println(Arrays.toString(tree));
			System.out.println();
		}
	}
}

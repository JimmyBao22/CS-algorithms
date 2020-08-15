
import java.util.*;
import java.io.*;

public class SegmentTree {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SegmentTree"));
		
		// StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(in.readLine());	// size of array
		SegTree s = new SegTree(n); 
		
	}
		
	static class SegTree {
		int size=1;
		long[] tree;
		
		public SegTree(int n) {			
			while (size < n) size *= 2;
			tree = new long[2*size];
			for (int i=0; i<2*size; i++) tree[i] = 0;
		}
		
		// random computation on segment
		public long comp_seg(int l, int r) { return comp_seg(l, r, 0, 0, size); }
		
		public long comp_seg(int l, int r, int x, int lx, int rx) {
			if (lx >= r || rx <= l) return 0;	// do not intersect this segment
			if (l <= lx && rx <= r) return tree[x];	// inside whole segment
			int m = (lx + rx)/2;
			long one = comp_seg(l, r, 2*x+1, lx, m); 
			long two = comp_seg(l, r, 2*x+2, m, rx);
			return one + two;
		}
		
		public void set(int i, long v) { set(i, v, 0, 0, size); }	// arr[i] = v;
		
		public void set(int i, long v, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				tree[x] = v; return;
			}
			int m = (lx + rx)/2;
			if (i < m) set(i, v, 2*x+1, lx, m); 	// go to left subtree
			else set(i, v, 2*x+2, m, rx);			// go to right subtree
			tree[x] = tree[2*x+1] + tree[2*x+2];
		}
		
		// random computation on index
		public long comp_index(int i) { return comp_index(i, 0, 0, size); }
		
		public long comp_index(int i, int x, int lx, int rx) {
			if (rx - lx == 1) return tree[x];	// in leaf node aka bottom level
			// propogate(x, lx, rx);
			int m = (lx + rx)/2;	long result = 0;
			if (i < m) result = comp_index(i, 2*x+1, lx, m);	// go to left subtree
			else result = comp_index(i, 2*x+2, m, rx);		// go to right subtree
			return result + tree[x];
		}
				
		// change segments
		public void modify(int l, int r, long v) { modify(l, r, v, 0, 0, size); }
		
		public void modify(int l, int r, long v, int x, int lx, int rx) {
			if (lx >= r || rx <= l) return;		// do not intersect this segment
			// propagate(x, lx, rx);
			if (l <= lx && rx <= r) {		// inside whole segment
				tree[x] += v;
				return;
			}
			int m = (lx + rx)/2;
			modify(l, r, v, 2*x+1, lx, m);	modify(l, r, v, 2*x+2, m, rx);
		}
		
		static long NONE = (long)(1e18);	// use a value that will never be used
				// if all values can be used, use a boolean array to see if tree[x]
				// has a value stored or not
		public void propogate(int x, int lx, int rx) {
			if (rx - lx == 1) return;		// leaf
			if (tree[x] != NONE) {
				tree[2*x+1] = tree[x];	tree[2*x+2] = tree[x];
				tree[x] = NONE;
			}
		}
		
		public void build(long[] arr) {	build(arr, 0, 0, size); }	// arr is the orig arr
		
		public void build(long[] arr, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				if (lx < arr.length) tree[x] = arr[lx];
				return;
			}
			int m = (lx + rx)/2;
			build(arr, 2*x+1, lx, m);	build(arr, 2*x+2, m, rx);
			tree[x] = tree[2*x+1] + tree[2*x+2];
		}
		
		public void print() {
			for (int i=0; i<tree.length; i++) System.out.print(tree[i] + " ");
			System.out.println();
		}
	}
}

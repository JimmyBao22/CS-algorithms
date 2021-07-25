
import java.util.*;
import java.io.*;

public class SegmentTree2D {

	public static void main(String[] args) {

	}
	
	static class SegTree2D {
		int sizex=1; int sizey=1;
		long[][] tree;
		
		public SegTree2D(int n, int m) {		
			while (sizex < n) sizex <<= 1;
			while (sizey < m) sizey <<= 1;
			tree = new long[2*sizex][2*sizey];
		}
		
		// random computation from (l1, r1) to (l2-1, r2-1)
		public long comp_seg(int l1, int r1, int l2, int r2) { return comp_segx(l1, l2, r1, r2, 0, 0, sizex); }
		
		public long comp_segx(int l1, int l2, int r1, int r2, int x, int lx, int rx) {
			if (lx >= l2 || rx <= l1) return 0;	// do not intersect this segment
			if (rx - lx == 1) {
				return comp_segy(l1, l2, r1, r2, x, 0, 0, sizey);	
			}
			int m = (lx + rx) >> 1;
			long one = comp_segx(l1, l2, r1, r2, 2*x+1, lx, m); 
			long two = comp_segx(l1, l2, r1, r2, 2*x+2, m, rx);
			return one + two;
		}
		
		public long comp_segy(int l1, int l2, int r1, int r2, int x, int y, int ly, int ry) {
			if (ly >= r2 || ry <= r1) return 0;	// do not intersect this segment
			if (r1 <= ly && ry <= r2) {
				return tree[x][y];				// inside whole segment
			}
			int m = (ly + ry) >> 1;
			long one = comp_segy(l1, l2, r1, r2, x, 2*y+1, ly, m); 
			long two = comp_segy(l1, l2, r1, r2, x, 2*y+2, m, ry);
			return one + two;
		}
		
		// arr[i][j] = v;
		public void set(int i, int j, long v) { setx(i, j, v, 0, 0, sizex); }
		
		public void setx(int i, int j, long v, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				sety(i, j, v, x, 0, 0, sizey); return;
			}
			int m = (lx + rx) >> 1;
			if (i < m) setx(i, j, v, 2*x+1, lx, m); 	// go to left subtree
			else setx(i, j, v, 2*x+2, m, rx);			// go to right subtree
		}
		
		public void sety(int i, int j, long v, int x, int y, int ly, int ry) {
			if (ry - ly == 1) {		// in leaf node aka bottom level
				tree[x][y] = v; return;
			}
			int m = (ly + ry) >> 1;
			if (j < m) sety(i, j, v, x, 2*y+1, ly, m); 	// go to left subtree
			else sety(i, j, v, x, 2*y+2, m, ry);			// go to right subtree
			tree[x][y] = tree[x][2*y+1] + tree[x][2*y+2];
		}
		
		public void build(long[][] arr) { buildx(arr, 0, 0, sizex); }	// arr is the orig arr
		
		public void buildx(long[][] arr, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				if (lx < arr.length) {
					buildy(arr, x, 0, lx, 0, sizey);
				}
				return;
			}
			int m = (lx + rx) >> 1;
			buildx(arr, 2*x+1, lx, m);	buildx(arr, 2*x+2, m, rx);
		}
		
		public void buildy(long[][] arr, int x, int y, int lx, int ly, int ry) {
			if (ry - ly == 1) {		// in leaf node aka bottom level
				if (ly < arr.length) tree[x][y] = arr[lx][ly];
				return;
			}
			int m = (ly + ry) >> 1;
			buildy(arr, x, 2*y+1, lx, ly, m);	buildy(arr, x, 2*y+2, lx, m, ry);
			tree[x][y] = tree[x][2*y+1] + tree[x][2*y+2];
		} 
		
		public void print() {
			for (int i=0; i<tree.length; i++) {
				System.out.println(Arrays.toString(tree[i]));
			}
			System.out.println();
		}
	}
}

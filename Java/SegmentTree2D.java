
import java.util.*;
import java.io.*;

public class SegmentTree2D {

	public static void main(String[] args) {

	}
	
	static class SegTree2D {
		int sizeX = 1; int sizeY = 1;
		long[][] tree;
		
		public SegTree2D(int n, int m) {		
			while (sizeX < n) sizeX <<= 1;
			while (sizeY < m) sizeY <<= 1;
			tree = new long[sizeX << 1][sizeY << 1];
		}
		
		// random computation from (l1, r1) to (l2-1, r2-1)
		public long compSeg(int l1, int r1, int l2, int r2) { return compSegX(l1, l2, r1, r2, 0, 0, sizeX); }
		
		public long compSegX(int l1, int l2, int r1, int r2, int x, int lx, int rx) {
			if (lx >= l2 || rx <= l1) return 0;								// do not intersect this segment
			if (rx - lx == 1) {
				return compSegY(l1, l2, r1, r2, x, 0, 0, sizeY);	
			}
			int m = (lx + rx) >> 1;
			long one = compSegX(l1, l2, r1, r2, (x<<1)+1, lx, m); 
			long two = compSegX(l1, l2, r1, r2, (x<<1)+2, m, rx);
			return one + two;
		}
		
		public long compSegY(int l1, int l2, int r1, int r2, int x, int y, int ly, int ry) {
			if (ly >= r2 || ry <= r1) return 0;								// do not intersect this segment
			if (r1 <= ly && ry <= r2) {
				return tree[x][y];											// inside whole segment
			}
			int m = (ly + ry) >> 1;
			long one = compSegY(l1, l2, r1, r2, x, (y<<1)+1, ly, m); 
			long two = compSegY(l1, l2, r1, r2, x, (y<<1)+2, m, ry);
			return one + two;
		}
		
		// arr[i][j] = v;
		public void set(int i, int j, long v) { setX(i, j, v, 0, 0, sizeX); }
		
		public void setX(int i, int j, long v, int x, int lx, int rx) {
			if (rx - lx == 1) {												// in leaf node aka bottom level
				setY(i, j, v, x, 0, 0, sizeY); return;
			}
			int m = (lx + rx) >> 1;
			if (i < m) setX(i, j, v, (x<<1)+1, lx, m); 						// go to left subtree
			else setX(i, j, v, (x<<1)+2, m, rx);								// go to right subtree
		}
		
		public void setY(int i, int j, long v, int x, int y, int ly, int ry) {
			if (ry - ly == 1) {												// in leaf node aka bottom level
				tree[x][y] = v; return;
			}
			int m = (ly + ry) >> 1;
			if (j < m) setY(i, j, v, x, (y<<1)+1, ly, m); 						// go to left subtree
			else setY(i, j, v, x, (y<<1)+2, m, ry);							// go to right subtree
			tree[x][y] = tree[x][(y<<1)+1] + tree[x][(y<<1)+2];
		}
		
		public void build(long[][] arr) { buildX(arr, 0, 0, sizeX); }	// arr is the orig arr
		
		public void buildX(long[][] arr, int x, int lx, int rx) {
			if (rx - lx == 1) {												// in leaf node aka bottom level
				if (lx < arr.length) {
					buildY(arr, x, 0, lx, 0, sizeY);
				}
				return;
			}
			int m = (lx + rx) >> 1;
			buildX(arr, (x<<1)+1, lx, m);
			buildX(arr, (x<<1)+2, m, rx);
		}
		
		public void buildY(long[][] arr, int x, int y, int lx, int ly, int ry) {
			if (ry - ly == 1) {												// in leaf node aka bottom level
				if (ly < arr.length) tree[x][y] = arr[lx][ly];
				return;
			}
			int m = (ly + ry) >> 1;
			buildY(arr, x, (y<<1)+1, lx, ly, m);
			buildY(arr, x, (y<<1)+2, lx, m, ry);
			tree[x][y] = tree[x][(y<<1)+1] + tree[x][(y<<1)+2];
		} 
		
		public void print() {
			for (int i = 0; i < tree.length; i++) {
				System.out.println(Arrays.toString(tree[i]));
			}
			System.out.println();
		}
	}
}

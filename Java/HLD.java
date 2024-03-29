
import java.util.*;
import java.io.*;

public class HLD {
	
	static int n, curPos;
	static ArrayList<Integer>[] g;
	static Node[] arr;
	static SegTree segTree;
	static long[] segTreeArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("HLD"));
		
		n = Integer.parseInt(in.readLine());
		g = new ArrayList[n];
		arr = new Node[n];
		curPos = 0;
		segTree = new SegTree(n);
		segTreeArr = new long[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = new Node(Integer.parseInt(st.nextToken()));
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken()) - 1;
			int two = Integer.parseInt(st.nextToken()) - 1;
			g[one].add(two); 
			g[two].add(one);
		}
		
		dfs(0, 0, 0);
		hld(0, 0);
		
		segTree.build(segTreeArr);
		
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(in.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken());
			if (one == 0) {			// update
				int two = Integer.parseInt(st.nextToken()) - 1;		// node
				long three = Long.parseLong(st.nextToken());		// value
				arr[two].val = three;
				segTree.set(arr[two].stpos, arr[two].val);
			}
			else {													// query
				int two = Integer.parseInt(st.nextToken()) - 1;		
				int three = Integer.parseInt(st.nextToken()) - 1;	
				long ans = query(two, three);
				sb.append(ans);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static long query(int a, int b) {
		long ans = 0;
		while (arr[a].head != arr[b].head) {						// while part of different chains
			if (arr[arr[a].head].depth > arr[arr[b].head].depth) {
				int temp = a; a = b; b = temp;						// swap so now b greater depth
			}
			int current_head = arr[b].head;
			ans = Math.max(ans, segTree.compSeg(arr[current_head].stpos, arr[b].stpos + 1));
			b = arr[current_head].parent;							// move b to parent of head so you are on a new chain
		}
		if (arr[a].depth > arr[b].depth) {
			int temp = a; a = b; b = temp;
		}

		// now a and b on same chain
		ans = Math.max(ans, segTree.compSeg(arr[a].stpos, arr[b].stpos + 1));
		return ans;
	}
	
	public static void hld(int node, int head) {
		arr[node].head = head;
		arr[node].stpos = curPos;
		segTreeArr[curPos] = arr[node].val;
		curPos++;
		if (arr[node].heavy != -1) {	
			hld(arr[node].heavy, head);
		}
		for (Integer i : g[node]) {
			if (i == arr[node].parent || i == arr[node].heavy) continue;
			hld(i, i);					// start a new chain
		}
	}
	
	public static int dfs(int node, int p, int d) {
		arr[node].size = 1;
		arr[node].parent = p;
		arr[node].depth = d;
		
		int maxSubtreeSize = 0;
		for (Integer i : g[node]) {
			if (i == p) continue;
			int curSize = dfs(i, node, d+1);
			arr[node].size += curSize;
			if (curSize > maxSubtreeSize) {
				maxSubtreeSize = curSize;
				arr[node].heavy = i;
			}
		}
		return arr[node].size;
	}
	
	static class Node {
		int size;
		int heavy = -1; 	// child at other end of heavy edge from this node
		int head;			// head of heavy path that this node is in
		int stpos;			// position in segment tree
		long val = 0;
		int parent;
		int depth;
		Node (long val) { this.val = val; }
	}
	
	static class SegTree {
		int size = 1;
		long[] tree;
		
		public SegTree(int n) {			
			while (size < n) size <<= 1;
			tree = new long[size << 1];
		}
		
		// random computation on segment
		public long compSeg(int l, int r) { return compSeg(l, r, 0, 0, size); }
		
		public long compSeg(int l, int r, int x, int lx, int rx) {
			if (lx >= r || rx <= l) return 0;	// do not intersect this segment
			if (l <= lx && rx <= r) return tree[x];	// inside whole segment
			int m = (lx + rx) >> 1;
			long one = compSeg(l, r, (x<<1)+1, lx, m); 
			long two = compSeg(l, r, (x<<1)+2, m, rx);
			return Math.max(one, two);
		}
		
		public void set(int i, long v) { set(i, v, 0, 0, size); }	// arr[i] = v;
		
		public void set(int i, long v, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				tree[x] = v; return;
			}
			int m = (lx + rx) >> 1;
			if (i < m) set(i, v, (x<<1)+1, lx, m); 	// go to left subtree
			else set(i, v, (x<<1)+2, m, rx);			// go to right subtree
			tree[x] = Math.max(tree[(x<<1)+1], tree[(x<<1)+2]);
		}
		
		public void build(long[] arr) {	build(arr, 0, 0, size); }	// arr is the orig arr
		
		public void build(long[] arr, int x, int lx, int rx) {
			if (rx - lx == 1) {		// in leaf node aka bottom level
				if (lx < arr.length) tree[x] = arr[lx];
				return;
			}
			int m = (lx + rx) >> 1;
			build(arr, (x<<1)+1, lx, m);
			build(arr, (x<<1)+2, m, rx);
			tree[x] = Math.max(tree[(x<<1)+1], tree[(x<<1)+2]);
		}
		
		public void print() {
			System.out.println(Arrays.toString(tree));
			System.out.println();
		}
	}
}

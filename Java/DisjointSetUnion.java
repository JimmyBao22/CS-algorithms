import java.util.*;
import java.io.*;

public class DisjointSetUnion {
	
	public static void main(String[] args) throws IOException {
		
	}
	
	static class DSU {
		int n;
		int[] parent;
		int[] size;
		
		DSU (int n) {
			this.n = n;
			parent = new int[n];
			size = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public int findSet(int a) {
			if (a == parent[a]) return a;
			return parent[a] = findSet(parent[a]);
		}
		
		public void union(int a, int b) {
			a = findSet(a);
			b = findSet(b);
			if (a == b) return;
			
			if (size[a] < size[b]) {
				parent[a] = b;
				size[b] += size[a];
			}
			else {
				parent[b] = a;
				size[a] += size[b];
			}
		}
	}
	
	static class DSUArrayList {
		int n;
		ArrayList<Integer> parent;
		ArrayList<Integer> size;
		
		DSUArrayList (int n) {
			this.n = n;
			parent = new ArrayList<>();
			size = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				parent.add(i);
				size.add(1);
			}
		}
		
		public void makeSet(int a) {
			n++;
			parent.add(a);
			size.add(1);
		}
		
		public int findSet(int a) {
			if (a == parent.get(a)) return a;
			parent.set(a, findSet(parent.get(a)));
			return parent.get(a);
		}
		
		public void union(int a, int b) {
			a = findSet(a);
			b = findSet(b);
			if (a == b) return;
			
			if (size.get(a) < size.get(b)) {
				parent.set(a, b);
				size.set(b, size.get(b) + size.get(a));
			}
			else {
				parent.set(b, a);
				size.set(a, size.get(b) + size.get(a));
			}
		}
	}
	
	static class DSU2D {
		int n, m;
		int mult = (int)1e4;
		int[][] parent;
		int[][] size;
		
		DSU2D (int n, int m) {
			this.n = n;
			this.m = m;
			parent = new int[n][m];
			size = new int[n][m];
			for (int i= 0 ; i < n; i++) {
				for (int j = 0; j < m; j++) {
					parent[i][j] = i * mult + j;
					size[i][j] = 1;
				}	
			}
		}

		public int FindSet(int i, int j) {
			if (i * mult + j == parent[i][j]) return i * mult + j;
			return parent[i][j] = FindSet(parent[i][j] / mult, parent[i][j] % mult);
		}
		
		public boolean Union(int i1, int j1, int i2, int j2) {
			int a = FindSet(i1,j1);
			int b = FindSet(i2,j2);
			if (a == b) { 			// already grouped
				return false;
			}
			i1 = a / mult;
			j1 = a % mult;
			i2 = b / mult;
			j2 = b % mult;
			
			if (size[i1][j1] < size[i2][j2]) {
				parent[i1][j1] = b;
				size[i2][j2] += size[i1][j1];
			}
			else {
				parent[i2][j2] = a;
				size[i1][j1] += size[i2][j2];
			}
			return true;
		}
	}
}

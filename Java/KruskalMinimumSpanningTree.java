
import java.util.*;
import java.io.*;

public class KruskalMinimumSpanningTree {

	static int n, m;
	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("KruskalMinimumSpanningTree"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		edges = new Edge[m];
				
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; 	
			int b = Integer.parseInt(st.nextToken()) - 1; 	
			long c = Long.parseLong(st.nextToken()); 
			edges[i] = new Edge(a,b,c);
		}
		Arrays.parallelSort(edges);
		DSU dsu = new DSU(n);
		
		mst(dsu);
	}
	
	public static long mst(DSU dsu) {
		long ans = 0;
		for (int i = 0; i < m; i++) {
			if (dsu.union(edges[i].from, edges[i].dest)) {
				ans += edges[i].length;
			}
		}
		return ans;
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int dest;
		long length;
		Edge(int from , int dest, long len) {
			this.from = from;
			this.dest = dest;
			length = len;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(length, o.length);
		}
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
		
		public boolean union(int a, int b) {
			a = findSet(a);
			b = findSet(b);
			if (a == b) return false;
			
			if (size[a] < size[b]) {
				parent[a] = b;
				size[b] += size[a];
			}
			else {
				parent[b] = a;
				size[a] += size[b];
			}
			return true;
		}
	}
}

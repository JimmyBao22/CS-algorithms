import java.util.*;
import java.io.*;

public class PrimMinimumSpanningTree {
	
	static ArrayList<Edge>[] g;
	static int n, m;
	static final long INF = (long)(1e18);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("PrimMinimumSpanningTree"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		g = new ArrayList[n];
		
		for (int i=0; i<n; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; 	
			int b = Integer.parseInt(st.nextToken()) - 1; 	
			long c = Long.parseLong(st.nextToken()); 
			g[a].add(new Edge(b, c));
			g[b].add(new Edge(a, c));
		}
		
	}
	
	public static long mst(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		pq.add(new Edge(start, 0));
		long minlength = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.dest]) continue;
			visited[cur.dest] = true;
			minlength += cur.length;
			
			for (Edge i : g[cur.dest]) {
				if (visited[i.dest]) continue;
				pq.add(i);
			}
		}
		return minlength;
	}
	
	public static long mst2(int start) {
		boolean[] visited = new boolean[n];
		long[] dist = new long[n];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		long minlength = 0;
		
		while (true) {
			// find the smallest one
			int smallest = -1;
			long minval = INF;
			for (int i=0; i<n; i++) {
				if (!visited[i] && dist[i] < minval) {
					minval = dist[i];
					smallest = i;
				}
			}
			if (smallest == -1) break;
			minlength += minval;
			
			for (Edge a : g[smallest]) {
				if (!visited[a.dest] && dist[a.dest] > a.length) {
					dist[a.dest] = a.length;
				}
			}
			visited[smallest] = true;
		}
		return minlength;
	}
	
	static class Edge implements Comparable<Edge> {
		int dest;
		long length;
		Edge(int dest , long len) {
			this.dest = dest;
			length = len;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(length, o.length);
		}
	}
}

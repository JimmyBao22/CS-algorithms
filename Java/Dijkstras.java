import java.util.*;
import java.io.*;

public class Dijkstras {
	
	static ArrayList<Edge>[] g;
	static long[] dist;
	static int[] parent;
	static int n, m;
	static final long INF = (long)(1e18);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Dijkstras"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n];
		parent = new int[n];
		g = new ArrayList[n];
		
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; 	
			int b = Integer.parseInt(st.nextToken()) - 1; 	
			long c = Long.parseLong(st.nextToken()); 
			g[a].add(new Edge(b, c));
			g[b].add(new Edge(a, c));
		}
		
	}
	
		// O((N+M)logN)
	public static void dijkstras(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		parent[start] = -1;
		
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int node = cur.dest;
			if (visited[node]) continue;
			visited[node] = true;
			
			for (Edge i : g[node]) {
				if (visited[i.dest]) continue;
				
				if (cur.length + i.length < dist[i.dest]) {
					dist[i.dest] = cur.length + i.length;
					parent[i.dest] = node;
					pq.add(new Edge(i.dest, dist[i.dest]));
				}
			}
		}
	}
	
		// O(N^2)
	public static void dijkstras2(int start) {
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		parent[start] = -1;
		
		while (true) {
			// find the smallest one
			int smallest = -1;
			long minval = INF;
			for (int j=0; j<n; j++) {
				if (!visited[j] && dist[j] < minval) {
					minval = dist[j];
					smallest = j;
				}
			}
			if (smallest == -1) break;
			
			for (Edge a : g[smallest]) {
				if (!visited[a.dest] && dist[a.dest] > minval + a.length) {
					dist[a.dest] = minval + a.length;
					parent[a.dest] = smallest;
				}
			}
			visited[smallest] = true;
		}
	}
	
	public static ArrayList<Integer> Backtrack(int dest) {
		ArrayList<Integer> path = new ArrayList<>();
		if (dist[dest] == INF) {
			return path;
		}
		while (dest != -1) {
			path.add(dest);
			dest = parent[dest];
		}
		return path;
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

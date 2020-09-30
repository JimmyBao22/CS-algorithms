import java.util.*;
import java.io.*;

public class Dijkstras {
	
	static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
	static long[] dist; 	// dist[i] = shortest distance from src to i
	static int[] parent;	// parent[i] = parent of i that gives shortest dist
	static int n, m;
	static long INF = (long)(1e18);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Dijkstras"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n];
		parent = new int[n];
		
		for (int i=0; i<n; i++) g.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 	
			long c = Long.parseLong(st.nextToken()); 
			g.get(a).add(new Edge(b, c));
			// g.get(b).add(new Edge(a, c)); 		// if undirected
		}
		
	}
	
		// O((N+M)logM)
	public static void dijkstras(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		parent[start] = -1;
		
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int node = cur.destination;
			if (visited[node]) continue;
			visited[node] = true;
			
			for (Edge i : g.get(node)) {
				if (visited[i.destination]) continue;
				
				if (cur.length + i.length < dist[i.destination]) {
					dist[i.destination] = cur.length + i.length;
					parent[i.destination] = node;
					pq.add(new Edge(i.destination, dist[i.destination]));
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
			int smallest=-1;
			long minval=INF;
			for (int j=0; j<n; j++) {
				if (!visited[j] && dist[j]<minval) {
					minval = dist[j];
					smallest = j;
				}
			}
			if (smallest == -1) break;
			
			for (Edge a : g.get(smallest)) {
				if (!visited[a.destination] && dist[a.destination] > minval + a.length) {
					dist[a.destination] = minval + a.length;
					parent[a.destination] = smallest;
				}
			}
			visited[smallest] = true;
		}
	}
	
	public static ArrayList<Integer> Backtrack(int dest) {
		ArrayList<Integer> path = new ArrayList<>();
		if (dist[dest] == INF) {
			path.add(-1);
			return path;
		}
		while (dest!= -1) {
			path.add(dest);
			dest = parent[dest];
		}
		return path;
	}
	
	static class Edge implements Comparable<Edge> {
		int destination;
		long length;
		Edge(int a , long b) {
			destination = a;
			length = b;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(length, o.length);
		}
	}
}

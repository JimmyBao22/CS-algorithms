
import java.util.*;
import java.io.*;

public class Dijkstras {
	
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
	static long[] dist; 	// dist[i] = shortest distance from src to i
	static int[] parent;	// parent[i] = parent of i that gives shortest dist
	static int n, m;
	static long INF = (long)(1e18);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Dijkstras"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		dist = new long[n];
		parent = new int[n];
		
		for (int i=0; i<n; i++) adj.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 	
			long c = Long.parseLong(st.nextToken()); 
			adj.get(a).add(new Edge(b, c));
			// adj.get(b).add(new Edge(a, c)); 		// if undirected
		}
		
		int start = Integer.parseInt(in.readLine());
		dijkstras(start);
		
	}
	
	public static void dijkstras(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		parent[start] = -1;
		
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.destination]) continue;
			visited[cur.destination] = true;
			
			for (Edge i : adj.get(cur.destination)) {
				if (visited[i.destination]) continue;
				
				if (cur.length + i.length < dist[i.destination]) {
					dist[i.destination] = cur.length + i.length;
					parent[i.destination] = cur.destination;
					pq.add(new Edge(i.destination, dist[i.destination]));
				}
				
			}
		}
	}
	
	public static void PrintPath(int dest) {
		if (dist[dest] == INF) {
			System.out.println("NO PATH FOUND");
			return;
		}
		
		ArrayList<Integer> path = new ArrayList<>();
		while (dest!= -1) {
			path.add(dest);
			dest = parent[dest];
		}
		for (int i=path.size()-1; i>=0; i--) {
			System.out.print(path.get(i) + " ");
		}
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

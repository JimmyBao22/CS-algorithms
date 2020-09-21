import java.util.*;
import java.io.*;

public class PrimMinimumSpanningTree {
	
	static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
	static int n, m;
	static long INF = (long)(1e18);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Dijkstras"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		
		for (int i=0; i<n; i++) g.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 	
			long c = Long.parseLong(st.nextToken()); 
			g.get(a).add(new Edge(b, c));
			g.get(b).add(new Edge(a, c));
		}

		int start = Integer.parseInt(in.readLine());
		int minlength = MST(start);
		
	}
	
	public static int MST(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		pq.add(new Edge(start, 0));
		int minlength = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.destination]) continue;
			visited[cur.destination] = true;
			minlength += cur.length;
			
			for (Edge i : g.get(cur.destination)) {
				if (visited[i.destination]) continue;
				pq.add(i);
			}
		}
		return minlength;
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

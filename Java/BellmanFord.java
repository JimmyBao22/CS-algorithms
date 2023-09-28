
import java.util.*;
import java.io.*;

public class BellmanFord {

	static int n, m;
	static long INF = (long)(1e18);
	static Edge[] edges;
	static long[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("BellmanFord"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n];
		edges = new Edge[m]; // 2*m if undirected
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken()) - 1;
			int two = Integer.parseInt(st.nextToken()) - 1;
			long weight = Long.parseLong(st.nextToken());
			edges[i] = new Edge(one, two, weight);
			// edges[i+m] = new Edge(two, one, weight);		if undirected
		}
		
		int start = Integer.parseInt(in.readLine())-1;
		bellmanFord(start);
		
	}
	
	public static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < m; j++) {
				int from = edges[j].start;
				int to = edges[j].dest;
				long weight = edges[j].weight;
				dist[to] = Math.min(dist[to], dist[from] + weight);
			}
		}
		
		// check for negative cycles
		for (int j = 0; j < m; j++) {
			int from = edges[j].start;
			int to = edges[j].dest;
			long weight = edges[j].weight;
			if (dist[from] + weight < dist[to]) return false;	// negative cycle
		}
		return true;
	}
	
	static class Edge {
		int start, dest;
		long weight;
		Edge (int start, int dest, long weight) {
			this.start = start;
			this.dest = dest;
			this.weight = weight;
		}
	}
}
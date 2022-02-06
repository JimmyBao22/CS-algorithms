import java.util.*;
import java.io.*;

public class TopoLogicalSortBfs {
	
	static ArrayList<Integer>[] g;
	static ArrayDeque<Integer> queue = new ArrayDeque<>();
	static ArrayList<Integer> fs = new ArrayList<>();
	static int[] indegree;
	static boolean[] visited;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("StronglyConnectedComponentsKosaraju"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		g = new ArrayList[n];
		
		visited = new boolean[n];
		indegree = new int[n];
		for (int i=0; i<n; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			g[a].add(b);
			indegree[b]++;
		}
		
		toposort();
	}
	
	public static void toposort() {
		for (int i=0; i<n; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
				visited[i] = true;
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (Integer a : g[cur]) {
				indegree[a]--;
				if (indegree[a] == 0 && !visited[a]) {
					queue.add(a);
					visited[a] = true;
				}
			}
			fs.add(cur);
		}
	}
}

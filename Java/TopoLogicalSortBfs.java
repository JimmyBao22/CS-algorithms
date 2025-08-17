import java.util.*;
import java.io.*;

public class TopoLogicalSortBfs {
	
	static ArrayList<Integer>[] g;
	static ArrayDeque<Integer> queue;
	static ArrayList<Integer> result;
	static int[] inDegree;
	static boolean[] visited;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("StronglyConnectedComponentsKosaraju"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		g = new ArrayList[n];
		queue = new ArrayDeque<>();
		result = new ArrayList<>();
		
		visited = new boolean[n];
		inDegree = new int[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			g[a].add(b);
			inDegree[b]++;
		}
		
		topoSortBFS();
	}
	
	public static void topoSortBFS() {
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
				visited[i] = true;
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			result.add(cur);
			for (Integer a : g[cur]) {
				inDegree[a]--;
				if (inDegree[a] == 0 && !visited[a]) {
					queue.add(a);
					visited[a] = true;
				}
			}
		}
	}
}
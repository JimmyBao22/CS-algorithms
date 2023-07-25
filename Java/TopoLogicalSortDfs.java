import java.util.*;
import java.io.*;

public class TopoLogicalSortDfs {
	
	static ArrayList<Integer>[] g;
	static ArrayDeque<Integer> stack;
	static ArrayList<Integer> fs;
	static boolean[] visited;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("StronglyConnectedComponentsKosaraju"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		g = new ArrayList[n];
		stack = new ArrayDeque<>();
		fs = new ArrayList<>();
		
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			g[a].add(b);
		}
		
		topoSortDFS();
	}
	
	public static void topoSortDFS() {
		for (int i = 0; i < n; i++) {
			if (!visited[i]) fillStack(i);
		}
		
		while (!stack.isEmpty()) {
			fs.add(stack.pop());
		}
	}
	
	public static void fillStack(int cur) {
		visited[cur] = true;
		for (Integer i : g[cur]) {
			if (!visited[i]) {
				fillStack(i);
			}
		}
		stack.push(cur);
	}
}
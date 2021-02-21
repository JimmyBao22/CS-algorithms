import java.util.*;
import java.io.*;

public class TopoLogicalSortDfs {
	
	static ArrayList<Integer>[] g;
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	static ArrayList<Integer> finalsequence = new ArrayList<>();
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
		for (int i=0; i<n; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			g[a].add(b);
		}
		
		toposort();
	}
	
	public static void toposort() {
		for (int i=0; i<n; i++) {
			if (!visited[i]) fillstack(i);
		}
		
		while (!stack.isEmpty()) {
			finalsequence.add(stack.pop());
		}
	}
	
	public static void fillstack(int cur) {
		visited[cur] = true;
		for (Integer i : g[cur]) {
			if (!visited[i]) fillstack(i);
		}
		stack.push(cur);
	}
}
import java.util.*;
import java.io.*;

public class StronglyConnectedComponentsKosaraju {
	
	static ArrayList<Integer>[] g, reverse;
	static boolean[] visited;
	static ArrayDeque<Integer> stack;
	static ArrayList<ArrayList<Integer>> SCC;
					// stores all of the components
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("StronglyConnectedComponentsKosaraju"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); 	// number of vertices
		m = Integer.parseInt(st.nextToken()); 	// number of edges
		g = new ArrayList[n];
		reverse = new ArrayList[n];
		stack = new ArrayDeque<>();
		SCC = new ArrayList<>();
		
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			g[a].add(b);
			reverse[b].add(a);
		}
		
		SCC();
	}
	
	public static void SCC() {
		for (int i = 0; i < n; i++) {
			if (!visited[i]) fillStack(i);
		}
		
		Arrays.fill(visited, false);
		
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			if (!visited[cur]) {
				ArrayList<Integer> curArr = new ArrayList<>();
				dfsRev(cur, curArr);
				SCC.add(curArr);
			}
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
	
	public static void dfsRev(int cur, ArrayList<Integer> curArr) {
		visited[cur] = true;
		curArr.add(cur);
		for (Integer i : reverse[cur]) {
			if (!visited[i]) {
				dfsRev(i, curArr);
			}
		}
	}
}

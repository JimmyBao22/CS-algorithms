
import java.util.*;
import java.io.*;

public class LCA {

	static int n, m, log;
	static ArrayList<ArrayList<Integer>> g = new ArrayList<>();
	static int[][] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("LCA"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		log = log(n)+1;
		parent = new int[n][log];
		depth = new int[n];
		for (int i=0; i<n; i++) g.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 	
			g.get(a).add(b);
			g.get(b).add(a);
		}
		
		dfs(0, 0);	// start from root node
		precomp();
		
	}
	
	public static int lca(int u, int v) {	// lca of u and v
		if (depth[u] < depth[v]) {
			int temp = u; u = v; v = temp;	// swap u and v
		}
		// depth[u] >= depth[v];
		int diff = depth[u] - depth[v];
		for (int i=0; i<log; i++) {
			if (((1 << i) & diff) > 0) {
				u = parent[u][i];
			}
		}
		if (u == v) return u;
		for (int i=log-1; i>=0; i--) {
			if (parent[u][i] != parent[v][i]) {
				u = parent[u][i]; v = parent[v][i];
			}
		}
		return parent[u][0];
	}
	
	public static void precomp() {
		for (int i=1; i<log; i++) {
			for (int j=0; j<n; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	
	public static void dfs(int node, int p) {
		parent[node][0] = p;
		for (int i=0; i<g.get(node).size(); i++) {
			if (g.get(node).get(i) == p) continue;
			depth[g.get(node).get(i)] = depth[node]+1;
			dfs(g.get(node).get(i), node);
		}
	}
	
	public static int log(int n) {
		int x = 1;
		while ((1<<(x+1)) <= n) x++;
		return x;
	}
}

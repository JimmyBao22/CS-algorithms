
import java.util.*;
import java.io.*;

public class CycleDetection {

	static ArrayList<ArrayList<Integer>> g = new ArrayList<>();
	static int n, m;
	static boolean[] visited, open;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Dijkstras"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		open = new boolean[n];
		
		for (int i=0; i<n; i++) g.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 
			g.get(a).add(b);
			// g.get(b).add(a);			// if undirected
		}

		for (int i=0; i<n; i++) {
			if (!visited[i]) dfscheck(0);
		}
		
	}
	
		// true = cycle, false = no cycle
	public static boolean dfscheck(int node) { 
		if (open[node]) return true;
		if (visited[node]) return false;
		visited[node] = true;
		open[node] = true;
		for (Integer i : g.get(node)) {
			if (dfscheck(i)) return true;
		}
		open[node] = false;
		return false;
	}
}


import java.util.*;
import java.io.*;

public class CycleDetection {

	static ArrayList<Integer>[] g;
	static int n, m;
	static boolean[] visited, open;
	static ArrayList<Integer> cycle = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("CycleDetection"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		open = new boolean[n];
		g = new ArrayList[n];
		
		for (int i=0; i<n; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 
			g[a].add(b);
			// g.get(b).add(a);			// if undirected
		}

		for (int i=0; i<n; i++) {
			if (!visited[i]) {
				if (dfscheck(i)) {
					System.out.println("cycle");
				}
			}
		}
		
		for (int i=0; i<n; i++) {
			visited[i] = false;
			open[i] = false;
		}
		
		for (int i=0; i<n; i++) {
	        if (!visited[i]) {
	            findcycle(i);
	            if (!cycle.isEmpty()) break;
	        }
	    }
		
	    Collections.reverse(cycle);
		
	}
	
		// true = cycle, false = no cycle
	public static boolean dfscheck(int node) { 
		if (open[node]) return true;
		if (visited[node]) return false;
		visited[node] = true;
		open[node] = true;
		for (Integer i : g[node]) {
			if (dfscheck(i)) return true;
		}
		open[node] = false;
		return false;
	}
	
	public static boolean findcycle(int node) {
	    visited[node] = true;
	    open[node] = true;
	    for (Integer i : g[node]) {
	        if (open[i]) {
	            cycle.add(node);     // start cycle
	            open[node] = false;
	            open[i] = false;
	            return true;
	        }
	        else if (!visited[i]) {
	            if (findcycle(i)) {         // continue cycle
	                if (open[node]) {
	                    cycle.add(node);
	                    open[node] = false;
	                    return true;
	                }
	                else {
	                    cycle.add(node);     // end cycle
	                    return false;
	                }
	            }
	            if (!cycle.isEmpty()) return false;       // finished cycle
	        }
	    }
	    open[node] = false;
	    return false;
	}
}


import java.util.*;
import java.io.*;

public class CycleDetection {

	static ArrayList<Integer>[] g;
	static int n, m;
	static boolean[] visited, open;
	static ArrayList<Integer> cycle;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("CycleDetection"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		open = new boolean[n];
		g = new ArrayList[n];
		cycle = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; 	
			int b = Integer.parseInt(st.nextToken()) - 1; 
			g[a].add(b);
			// g[b].add(a);			// if undirected
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (dfsCheck(i)) {
					System.out.println("cycle");
				}
			}
		}
		
		Arrays.fill(visited, false);
		Arrays.fill(open, false);
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
			    findCycle(i);
			    if (!cycle.isEmpty()) break;
			}
		}
		
		Collections.reverse(cycle);
		
	}
	
		// true = cycle, false = no cycle
	public static boolean dfsCheck(int node) { 
		if (open[node]) return true;
		if (visited[node]) return false;
		visited[node] = true;
		open[node] = true;
		for (Integer i : g[node]) {
			if (dfsCheck(i)) return true;
		}
		open[node] = false;
		return false;
	}
	
	// builds cycle arraylist
	public static boolean findCycle(int node) {
	    visited[node] = true;
	    open[node] = true;
	    for (Integer i : g[node]) {
	        if (open[i]) {									// start cycle
	            cycle.add(node);     						
	            open[node] = false;
	            open[i] = false;
	            return true;
	        }
	        else if (!visited[i]) {
	            if (findCycle(i)) {         				// continue cycle
	                if (open[node]) {
	                    cycle.add(node);
	                    open[node] = false;
	                    return true;
	                }
	                else {
	                    cycle.add(node);     				// end cycle
	                    return false;
	                }
	            }
	            if (!cycle.isEmpty()) {						// finished cycle
					return false;       	
				}
	        }
	    }
	    open[node] = false;
	    return false;
	}
}


import java.util.*;
import java.io.*;

public class EulerTour {

	static int n, time;
	static ArrayList<Integer>[] g;
	static int[] start, stop;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("EulerTour"));

		n = Integer.parseInt(in.readLine());
		time = 0;
		g = new ArrayList[n];
		for (int i=0; i<n; i++) g[i] = new ArrayList<>();
		for (int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1;
			g[a].add(b);
			g[b].add(a);
		}
		
		start = new int[n]; stop = new int[n];
		dfs(0, -1);
		
	}
	
	public static void dfs(int node, int p) {
		start[node] = time; time++;
		for (Integer i : g[node]) {
			if (i != p) dfs(i, node);
		}
		stop[node] = time;
	}
}

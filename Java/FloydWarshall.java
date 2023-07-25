
import java.util.*;
import java.io.*;

public class FloydWarshall {
	
	static int n, m;
	static long INF = (long)(1e18);
	static ArrayList<Integer>[] g;
	static long[][] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("FloydWarshall"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n][n];
		g = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dist, INF);
			dist[i][i]=0;
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken()) - 1;
			int two = Integer.parseInt(st.nextToken()) - 1;
			long weight = Long.parseLong(st.nextToken());
			g[one].add(two);
			g[two].add(one);
			dist[one][two] = weight;
			dist[two][one] = weight;
		}
		
		floydWarshall();
		
	}
	
	public static void floydWarshall () {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}

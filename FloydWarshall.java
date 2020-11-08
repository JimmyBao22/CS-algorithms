
import java.util.*;
import java.io.*;

public class FloydWarshall {
	
	static int n, m;
	static long INF = (long)(1e18);
	static ArrayList<ArrayList<Integer>> g = new ArrayList<>();
	static long[][] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("FloydWarshall"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(dist, INF);
			dist[i][i]=0;
			g.add(new ArrayList<>());
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken())-1;
			int two = Integer.parseInt(st.nextToken())-1;
			long weight = Long.parseLong(st.nextToken());
			g.get(one).add(two);
			dist[one][two] = weight;
			g.get(two).add(one);
			dist[two][one] = weight;
		}
		
		FW();
		
	}
	
	public static void FW () {
		for (int k=0; k<n; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}

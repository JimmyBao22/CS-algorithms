import java.util.*;
import java.io.*;

public class CentroidDecomposition {
	
	static ArrayList<Integer>[] g;
	static HashSet<Integer>[] g2;
	static int[] parent, subtreeSize;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("CentroidDecomposition"));

		n = Integer.parseInt(in.readLine());
		parent = new int[n];
		subtreeSize = new int[n];
		g = new ArrayList[n];
		g2 = new HashSet[n];
		
		for (int i=0; i<n; i++) {
			g[i] = new ArrayList<>();
			g2[i] = new HashSet<>();
		}
		
		for (int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1; 	
			int b = Integer.parseInt(st.nextToken())-1; 	
			g[a].add(b);
			g2[a].add(b);
			g[b].add(a);
			g2[b].add(a);
		}
		
		CDbuild(0, -1);
		
	}
	
	public static void CDbuild(int node, int p) {
		int s = FindSizes(node, p); 						// find the size of each subtree
		int centroid = getCentroid(node, p, s); 			// find the centroid
		if (p == -1) p = centroid; 					// parent of root is the root itself
		parent[centroid] = p;

		// for each tree remove the centroid and build
		for (Integer i : g2[centroid]) {
			g2[i].remove(centroid);
			CDbuild(i, centroid);
		}
	}
	
	public static int FindSizes(int node, int p) {
		subtreeSize[node] = 1;
		for (Integer i : g2[node]) {
			if (i != p) {
				subtreeSize[node] += FindSizes(i, node);
			}
		}

		return subtreeSize[node];
	}

	public static int getCentroid(int node, int p, int s) {
		for (Integer i : g2[node]) {
			if (i != p && subtreeSize[i] > s/2) {
				return getCentroid(i, node, s);
			}
		}

		return node;
	}
}

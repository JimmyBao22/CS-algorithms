
import java.util.*;
import java.io.*;

public class SmalltoLarge {
	
	static int n,m;
	static ArrayList<Integer>[] g, queries;
	static int[] ans, value;
	static ArrayList<TreeMap<Integer, Integer>> vals = new ArrayList<>();
		// node --> set of lengths
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SmalltoLarge"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = new int[n];
		value = new int[n];
		g = new ArrayList[n];
		queries = new ArrayList[n];
		
		for (int i=0; i<n; i++) {
			g[i] = new ArrayList<>();
			vals.add(new TreeMap<>());
			queries[i] = new ArrayList<>();
		}
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken())-1;
			int two = Integer.parseInt(st.nextToken())-1;
			g[one].add(two);
			g[two].add(one);
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken())-1;
			int two = Integer.parseInt(st.nextToken())-1;
			queries[one].add(two);
		}
		
		smalltolarge(0,-1);
		
		
	}
	
	public static void smalltolarge(int node, int parent) {
		int biggest = node;			// node with biggest length
		for (Integer to : g[node]) {
			if (to == parent) continue;
			smalltolarge(to, node);
			if (vals.get(to).size() > vals.get(biggest).size()) {
				biggest = to;
			}
		}
		
			// swap over so don't need to copy
		TreeMap<Integer, Integer> c = vals.get(biggest);
		vals.set(biggest, vals.get(node));
		vals.set(node, c);
		
		for (Integer to : g[node]) {
			if (to == parent || to == biggest) continue;
			for (Integer a : vals.get(to).keySet()) {		// copy over
				vals.get(node).put(a, vals.get(node).getOrDefault(a, 0) + vals.get(to).get(a));
			}
		}
				
		// add/remove values at node
		vals.get(node).put(value[node], vals.get(node).getOrDefault(value[node], 0)+1);
		
		// answer queries
		for (Integer i : queries[node]) {
			ans[i] = vals.get(node).firstKey();
		}
	}
}

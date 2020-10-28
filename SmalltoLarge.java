
import java.util.*;
import java.io.*;

public class SmalltoLarge {

	// http://www.usaco.org/index.php?page=viewproblem2&cpid=842
	
	static int n,m;
	static ArrayList<ArrayList<Integer>> g = new ArrayList<>();
	static int[] ans, value;
	static HashMap<Integer, TreeMap<Integer, Integer>> vals = new HashMap<>();
		// node = set of lengths
	static ArrayList<ArrayList<Integer>> queries = new ArrayList<>();
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("SmalltoLarge"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = new int[n];
		value = new int[n];
		
		for (int i=0; i<n; i++) {
			g.add(new ArrayList<>());
			vals.put(i, new TreeMap<>());
		}
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken())-1;
			int two = Integer.parseInt(st.nextToken())-1;
			g.get(one).add(two);
			g.get(two).add(one);
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			int one = Integer.parseInt(st.nextToken())-1;
			int two = Integer.parseInt(st.nextToken())-1;
			queries.get(one).add(two);
		}
		
		smalltolarge(0,-1);
		
		
	}
	
	public static void smalltolarge(int node, int parent) {
		int biggest = node;			// node with biggest length
		for (int i=0; i<g.get(node).size(); i++) {
			int to = g.get(node).get(i);
			if (to == parent) continue;
			smalltolarge(to, node);
			if (vals.get(to).size() > vals.get(biggest).size()) {
				biggest = to;
			}
		}
		
			// swap over so don't need to copy
		TreeMap<Integer, Integer> c = vals.get(biggest);
		vals.put(biggest, vals.get(node));
		vals.put(node, c);
		
		for (int i=0; i<g.get(node).size(); i++) {
			int to = g.get(node).get(i);
			if (to == parent || to == biggest) continue;
			for (Integer a : vals.get(to).keySet()) {		// copy over
				vals.get(node).put(a, vals.get(node).getOrDefault(a, 0) + vals.get(to).get(a));
			}
		}
		
		// add/remove values at node
		vals.get(node).put(value[node], vals.get(node).getOrDefault(value[node], 0)+1);
		
		// answer queries
		if (parent == -1) return;
		for (int i=0; i<queries.get(node).size(); i++) {
			ans[queries.get(node).get(i)] = vals.get(node).firstKey();
		}
	}
}
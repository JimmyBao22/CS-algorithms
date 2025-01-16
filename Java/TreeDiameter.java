import java.util.*;
import java.io.*;
 
public class TreeDiameter {

    static int n;
    static  ArrayList<Integer>[] g;
    static int[] dist, parent, maxDist;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("TreeDiameter"));

		n = Integer.parseInt(in.readLine());
		g = new ArrayList[n];
        dist = new int[n];
        parent = new int[n];
        maxDist = new int[n];
		
		for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
		}

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            g[a].add(b);
            g[b].add(a);
        }
     
        List<Integer> sequence = getDiameterSequence();
        getMaxDistanceOfAllNodes(sequence);

        
    }

    static List<Integer> getDiameterSequence() {
        // first dfs - get node with maximum distance from node 0
        dfs(0, -1);
     
        int furthestNode = 0;
        int maxDistance = dist[0];
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDistance) {
                maxDistance = dist[i];
                furthestNode = i;
            }
            // reset distance array
            dist[i] = 0;
        }
     
        // second dfs - get the node at the end of the diameter
        dfs(furthestNode, -1);
    
        furthestNode = 0;
        maxDistance = dist[0];
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDistance) {
                furthestNode = i;
                maxDistance = dist[i];
            }
        }
    
        // sequence stores the vector of nodes representing the diameter
        // maxDistance = sequence.size() - 1
        List<Integer> sequence = new ArrayList<>();
        while (furthestNode != -1) {
            sequence.add(furthestNode);
            furthestNode = parent[furthestNode];
        }
    
        return sequence;
    }

    static void dfs(int node, int p) {
        parent[node] = p;
        for (int to : g[node]) {
            if (to != p) {
                dist[to] = dist[node]+1;
                dfs(to, node);
            }
        }
    }

    static void getMaxDistanceOfAllNodes(List<Integer> sequence) {
        int maxDistance = sequence.size() - 1;
     
        // maxDist[i] = max distance of node i to another other node
        maxDist[sequence.get(0)] = maxDistance;
        maxDist[sequence.get(sequence.size()-1)] = maxDistance;
        for (int i = 1; i < sequence.size()-1; i++) {
            getMaxDistances(sequence.get(i), sequence.get(i-1), sequence.get(i+1), Math.max(i, maxDistance-i));
        }
    }

    // gets max distance of node to all other nodes
    static void getMaxDistances(int node, int p1, int p2, int d) {
        maxDist[node] = d;
        for (int to : g[node]) {
            if (to != p1 && to != p2) {
                getMaxDistances(to, node, node, d+1);
            }
        }
    }
}

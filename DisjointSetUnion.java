import java.util.*;
import java.io.*;

public class DisjointSetUnion {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("DisjointSetUnion"));
		
		int n = Integer.parseInt(in.readLine());
		dsu d = new dsu(n);
		
	}
	
	static class dsu {
		int n;
		int[] parent;
		int[] size;
		
		dsu (int n) {
			this.n = n;
			parent = new int[n];
			size = new int[n];
			for (int i=0; i<n; i++) {parent[i] = i; size[i] = 1;}
		}

		public int FindSet(int a) {
			if (a == parent[a]) return a;
			return parent[a] = FindSet(parent[a]);
		}
		
		public void Union(int a, int b) {
			a = FindSet(a);
			b = FindSet(b);
			if (a == b) return;
			
			if (size[a] < size[b]) {
				parent[a] = b;
				size[b] += size[a];
			}
			else {
				parent[b] = a;
				size[a] += size[b];
			}
		}
	}
	
	static class dsu_c {
		int n;
		ArrayList<Integer> parent;
		ArrayList<Integer> size;
		
		dsu_c (int n) {
			this.n = n;
			parent = new ArrayList<>();
			size = new ArrayList<>();
			for (int i=0; i<n; i++) {parent.add(i); size.add(1);}
		}
		
		public void MakeSet(int a) {
			n++;
			parent.add(a);
			size.add(1);
		}
		
		public int FindSet(int a) {
			if (a == parent.get(a)) return a;
			parent.set(a, FindSet(parent.get(a)));
			return parent.get(a);
		}
		
		public void Union(int a, int b) {
			a = FindSet(a);
			b = FindSet(b);
			if (a == b) return;
			
			if (size.get(a) < size.get(b)) {
				parent.set(a, b);
				size.set(b, size.get(b) + size.get(a));
			}
			else {
				parent.set(b, a);
				size.set(a, size.get(b) + size.get(a));
			}
		}
	}
}

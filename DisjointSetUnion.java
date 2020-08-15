
import java.util.*;
import java.io.*;

public class DisjointSetUnion {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("DisjointSetUnion"));
		
		int n = Integer.parseInt(in.readLine());
		DSU d = new DSU(n);
		
	}
	
	static class DSU {
		int n;
		ArrayList<Integer> parent;
		ArrayList<Integer> size;
		
		DSU (int n) {
			this.n = n;
			parent = new ArrayList<>();
			size = new ArrayList<>();
			for (int i=0; i<n; i++) {parent.add(i); size.add(1);}
		}
		
		public void MakeSet(int a) {
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
			// if (a == b) { } // cycle found
			if (a != b) {
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
}

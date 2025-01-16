
import java.util.*;
import java.io.*;

public class LCS {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("LCS"));

		
		
	}

	// finds LCS - bottom up
	public static int LCS(String a, String b) {
		int n = a.length();
		int m = b.length();
		int[][] dp = new int[n][m];
		if (a.charAt(0) == b.charAt(0)) {
			dp[0][0] = 1;
		}
		for (int i = 1; i < n; i++) {
		    if (a.charAt(i) == b.charAt(0)) {
				dp[i][0] = 1;
			}
		    else dp[i][0] = dp[i-1][0];
		}
		for (int i = 1; i < m; i++) {
		    if (a.charAt(0) == b.charAt(i)) {
				dp[0][i] = 1;
			}
		    else dp[0][i] = dp[0][i-1];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (a.charAt(i) == b.charAt(j)) {
				    dp[i][j] = dp[i-1][j-1] + 1;
				}
				dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
			}
		}

		return dp[n-1][m-1];
	}
	
	static int[][] memo;
	static int n, m;
	
	// finds LCS - top down
	public static int dp(String a, String b, int posa, int posb) {
		if (posa == n || posb == m) return 0;
		if (memo[posa][posb] != -1) return memo[posa][posb];
		int ans=0;
		if (a.charAt(posa) == b.charAt(posb)) {
			ans = dp(a, b, posa + 1, posb + 1) + 1;
		}
		ans = Math.max(ans, Math.max(dp(a, b, posa + 1, posb), dp(a, b, posa, posb + 1)));
		return memo[posa][posb] = ans;
	}
	
	// finds the LCS string
	public static void find(String a, String b) {
		int n = a.length();
		int m = b.length();
		Node[][] dp = new Node[n][m];
		if (a.charAt(0) == b.charAt(0)) dp[0][0] = new Node(1, 0, 0);
		else dp[0][0] = new Node(0,0,0);

		for (int i = 1; i < n; i++) {
		    if (a.charAt(i) == b.charAt(0)) dp[i][0] = new Node(1, i, 0);
		    else dp[i][0] = new Node(dp[i-1][0].val, i-1, 0);
		}
		for (int i = 1; i < m; i++) {
		    if (a.charAt(0) == b.charAt(i)) dp[0][i] = new Node(1, 0, i);
		    else dp[0][i] = new Node(dp[0][i-1].val, 0, i-1);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (a.charAt(i) == b.charAt(j)) {
				    dp[i][j] = new Node(dp[i-1][j-1].val+1, i-1, j-1);
				}
				if (dp[i][j] == null || dp[i-1][j].val > dp[i][j].val) {
					dp[i][j] = new Node(dp[i-1][j].val, i-1, j);
				}
				if (dp[i][j-1].val > dp[i][j].val) {
					dp[i][j] = new Node(dp[i][j-1].val, i, j-1);
				}
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++ ) {
			for (int j = 0; j < m; j++) {
				max = Math.max(max, dp[i][j].val);
			}
		}

		if (max == 0) {
			System.out.println("");
			return;
		}

		// start the process to find the string
		Node cur = new Node(0,0,0);
		o: for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dp[i][j].val == max) {
					cur = new Node(dp[i][j].val, dp[i][j].x, dp[i][j].y, i, j);
					break o;
				}
			}
		}

		ArrayList<Character> ans = new ArrayList<>();
		while (true) {
			if (cur.x + 1 == cur.curx && cur.y + 1 == cur.cury) {
				// came from a diagonal
				ans.add(a.charAt(cur.curx));
			}

			if (dp[cur.curx][cur.cury].x == cur.curx && dp[cur.curx][cur.cury].y == cur.cury) {
				break;
			}
			cur = new Node(dp[cur.x][cur.y].val, dp[cur.x][cur.y].x, dp[cur.x][cur.y].y, cur.x, cur.y);
		}

		if (cur.val == 1) {
			ans.add(a.charAt(cur.curx));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = ans.size() - 1; i >= 0; i--) {
			sb.append(ans.get(i));
		}
		System.out.println(sb);
	}
	
	static class Node {
		int val;
		int x; int y;		// where you are coming from
		int curx; int cury;
		Node (int val, int x, int y) {
			this.val = val; this.x = x; this.y = y;
		}
		Node (int val, int x, int y, int curx, int cury) {
			this.val = val; this.x = x; this.y = y; this.curx = curx; this.cury = cury;
		}
		void print() {
			System.out.println(val + " " + x + " " + y);
		}
	}
}

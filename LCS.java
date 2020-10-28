
import java.util.*;
import java.io.*;

public class LCS {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("LCS"));

		String a = in.readLine();
		String b = in.readLine();
		int n = a.length();
        int m = b.length();
        int[][] dp = new int[n][m];
        if (a.charAt(0) == b.charAt(0)) dp[0][0] = 1;
        for (int i=1; i<n; i++) {
            if (a.charAt(i) == b.charAt(0)) dp[i][0] = 1;
            else dp[i][0] = dp[i-1][0];
        }
        for (int i=1; i<m; i++) {
            if (a.charAt(0) == b.charAt(i)) dp[0][i] = 1;
            else dp[0][i] = dp[0][i-1];
        }
        for (int i=1; i<n; i++) {
        	for (int j=1; j<m; j++) {
        		if (a.charAt(i) == b.charAt(j)) {
        		    dp[i][j] = dp[i-1][j-1]+1;
        		}
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
        	}
        }
        
	}
}

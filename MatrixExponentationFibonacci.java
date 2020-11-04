import java.util.*;
import java.io.*;

public class MatrixExponentationFibonacci {

	// finds the nth fibonacci number  -  O(logn)

	static long mod = (long)1e9 + 7;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("Fibonacci"));

		long n = Long.parseLong(in.readLine());
		long[][] arr = {{0,1}, {1,1}};
		long[][] ans = {{0,1}, {1,1}};
		while (n > 0) {
			if (n%2 == 1) {
				ans = multiply(2, ans, arr);
			}
			arr = multiply(2, arr, arr);
			n >>= 1;
		}
		System.out.println(ans[0][0]%mod);
	}
	
	public static long[][] multiply (int n, long[][] a, long[][] b) {
		long[][] product = new long[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<n; k++) {
					product[i][k] += a[i][j] * b[j][k];
					product[i][k] %= mod;
				}
			}
		}
		return product;
	}
}

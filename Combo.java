import java.util.*;
import java.io.*;

public class Combo {
	
	static int n;
	static int k;
	static long mod;
	static long[][] choose;
	
	public static void main(String[] args) {
		
		choose = new long[n+1][k+1];
		build();
		
	}
	
	public static void build() {
		for (int i=0; i<choose.length; i++) {
			for (int j=0; j<choose[0].length && j<=i; j++) {
				if (j == 0 || j == i) choose[i][j] = 1;
				else {
					choose[i][j] = choose[i-1][j-1] + choose[i-1][j];
					choose[i][j] %= mod;
				}
			}
		}
	}
	
}

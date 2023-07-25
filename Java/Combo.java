import java.util.*;
import java.io.*;

public class Combo {
	
	static int n, k;
	static long mod = (long)(1e9+7);
	static long[][] choose;
	static long[] fact, inv_fact;
	
	public static void main(String[] args) {
		
		choose = new long[n+1][k+1];
		build();
		
		fact = new long[n+1]; 
		inv_fact = new long[n+1];
		fact[0] = inv_fact[0] = 1;
		for (int i = 1; i < fact.length; i++) {
			fact[i] = fact[i-1] * i;
			fact[i] %= mod;
			inv_fact[i] = pow(fact[i], mod-2, mod);
		}
	}
	
	public static void build() {
		for (int i = 0; i < choose.length; i++) {
			for (int j = 0; j < choose[0].length && j <= i; j++) {
				if (j == 0 || j == i) choose[i][j] = 1;
				else {
					choose[i][j] = choose[i-1][j-1] + choose[i-1][j];
					choose[i][j] %= mod;
				}
			}
		}
	}
	
		// top! / bottom! (top - bottom)!
	public static long choose(int top, int bottom) {
		return fact[top] * inv_fact[bottom] % mod * inv_fact[top - bottom] % mod;
	}
	
    public static long pow(long a, long b, long m) {
    	long ans = 1;
    	while (b > 0) {
    		if ((b & 1) == 1) {
    			ans *= a;
    			ans %= m;
    		}
    		a *= a;
    		a %= m;
    		b >>= 1;
    	}
    	return ans;
    }
}

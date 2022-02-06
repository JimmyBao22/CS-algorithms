import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Mod {
	
	public static void main(String[] args) {

	}
	
	public static long modInverse(long a, long m) {
		return pow(a, m - 2, m);
	}

	public static long pow(long a, long b, long m) {
		long ans=1;
		while (b > 0) {
			if (b%2 == 1) {
				ans *= a;
				ans %= m;
			}
			a *= a;
			a %= m;
			b >>= 1;
		}
		return ans;
	}

	static long x,y;
	public static long modInverse1(long a, long m) {         
		x=y=1;
		long g = gcdExtended(a, m);
		if (g!=1) return -1; 	// if a and m are not coprime, there is not an inverse
		long inverse = (x%m + m)%m;
		return inverse;
	} 

	public static long gcdExtended(long a, long b) { 
		// Base Case 
		if (a == 0) { 
		    x = 0; 
		    y = 1;
		    return b; 
		}

		long gcd = gcdExtended(b%a, a); 
		long x1=x, y1=y;  
		// Update x and y 
		x = y1 - (b/a) * x1; 
		y = x1; 

		return gcd; 
	}
    
	public static long modInverse2(long a, long m) {
		BigInteger one = new BigInteger(a + "");
		BigInteger mod = new BigInteger(m + "");
		BigInteger res = one.modInverse(mod);
		return res.longValue();
	}
    
					    // x = remainders[0] mod mods[0]
						// x = remainders[1] mod mods[1]
						// x = remainders[2] mod mods[2] ... 
    	public static long CRT(long[] remainders, long[] mods) {
		int m = remainders.length;
		long modAll = 1;
		for (int i=0; i<m; i++) modAll *= mods[i];

		long ans=0;
		for (int i=0; i<m; i++) {
			long x = modAll/mods[i];
											// use better modinverse if mods[i] not prime
			ans += remainders[i] * x * modInverse(x, mods[i]);
		}

		return ans;			// all ans + modAll * k for some k work
	}
}

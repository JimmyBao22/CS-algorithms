
import java.util.*;
import java.io.*;

public class hashing {

	static long mod = (long)(1e9+7);
	static long p = 53;
	
	public static void main(String[] args) {

	}
	
	public static long fullHash(String s) {
		long hashval=0;
		long pow = 1;
		for (char c : s.toCharArray()) {
			hashval = (hashval + (c - 'a' + 1) * pow)%mod;
			pow *= p % mod;
		}
		return hashval;
	}
	
	public static long[] prefHash(String s) {
		long[] pref = new long[s.length()];
		long hashval=0;
		long pow=1;
		int i=0;
		for (char c : s.toCharArray()) {
			hashval = (hashval + (c - 'a' + 1) * pow)%mod;
			pow *= p % mod;
			pref[i] = hashval;
			i++;
		}
		return pref;
	}
	
		// hash of substring from i to j
		// pref is array calculated in prefhash. Therefore only use this if you need
			// to calculate hash of a lot of substrings
	public static long SubstringHash(String s, int i, int j, long[] pref) {
		if (i == 0) {
			return ((pref[j] - pref[i-1]) * modInversePrime(pow(p, i, mod), mod))%mod;
		}
		else {
			return (pref[j] * modInversePrime(pow(p, i, mod), mod))%mod;
		}
	}
	
	static long modInversePrime(long a, long m) {
        return pow(a, m - 2, m)%m;
    }
    
    static long pow(long a, long b, long m) {
    	long ans=1;
    	while (b > 0) {
    		if (b%2 == 1) {
    			ans *= a%m;
    			ans %= m;
    		}
    		a *= a % m;
    		a %= m;
    		b >>= 1;
    	}
    	return ans;
    }
}

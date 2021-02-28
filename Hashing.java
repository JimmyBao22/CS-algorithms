
import java.util.*;
import java.io.*;

public class Hashing {

	static long mod = (long)(1e9+7);
	static long p = 97;
	static int n;
	static long[] pref, power;

	
	public static void main(String[] args) {
		pref = new long[n];
		power = new long[n];
	}
	
		// hash of substring from i to j
		// pref is array calculated in prefhash. Therefore only use this if you need
			// to calculate hash of a lot of substrings
	public static long SubstringHash(int i, int j) {
	    if (i != 0) {
	        return (((pref[j] - (pref[i-1] * power[j - i + 1])%mod)%mod+mod)%mod);
	    }
	    else {
	        return pref[j];
	    }
	}
	
	public static void prefHash(String s) {
	    pref[0] = s.charAt(0) - 'a';
	    for (int i=1; i<s.length(); i++) {
	        pref[i] = ((pref[i-1]*p)%mod + (s.charAt(i) - 'a'))%mod;
	    }
	}
	
	public static void calc_power() {
	    power[0] = 1;
	    for (int i=1; i<n; i++) {
	        power[i] = power[i-1] * p;
	        power[i] %= mod;
	    }
	}
}

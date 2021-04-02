
import java.util.*;
import java.io.*;

public class Hashing {

	static long mod = (long)(1e9+7);
	static int n;
	static long[] pref, power, inv_power;
	static long p = 911382323, p1 = 972663749;
	
	public static void main(String[] args) {
		pref = new long[n];
		power = new long[n];
		inv_power = new long[n];
		
	    // use the right ones together; either all '1' or all '2'
				
	}
	
	public static long SubstringHash1(int i, int j) {
	    if (i != 0) {
	        return (((pref[j] - (pref[i-1] * power[j - i + 1])%mod)%mod+mod)%mod);
	    }
	    else {
	        return pref[j];
	    }
	}
	
	public static void prefHash1(String s) {
	    if (s.length() > 0) pref[0] = s.charAt(0) - 'a';
	    for (int i=1; i<s.length(); i++) {
	        pref[i] = (pref[i-1]*p + (s.charAt(i) - 'a'))%mod;
	    }
	}
	
	public static void calc_power1() {
	    power[0] = 1;
	    for (int i=1; i<n; i++) {
	        power[i] = power[i-1] * p;
	        power[i] %= mod;
	    }
	}
	
	
	public static long SubstringHash2(int i, int j) {
	    if (i != 0) {
	        return ((((pref[j] - pref[i-1])%mod+mod)%mod) * inv_power[i])%mod;
	    }
	    else {
	        return (pref[j] * inv_power[i])%mod;
	    }
	}

	public static void prefHash2(String s) {
	    if (s.length() > 0) pref[0] = s.charAt(0) - 'a';
	    long pow = p;
	    for (int i=1; i<(int)s.length(); i++) {
	        pref[i] = (pref[i-1] + (s.charAt(i) - 'a') * pow)%mod;
	        pow = (pow*p)%mod;
	    }
	}
	
	public static void calc_power2() {
	    power[0] = inv_power[0] = 1;
	    for (int i=1; i<n; i++) {
	        power[i] = power[i-1] * p;
	        power[i] %= mod;
	        inv_power[i] = pow(power[i], mod-2, mod);
	    }
	}
	
	public static long pow(long a, long b, long m) {
    	long ans = 1;
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
}

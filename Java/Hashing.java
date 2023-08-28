
import java.util.*;
import java.io.*;

public class Hashing {

	static long mod = (long)(1e9+7);
	static int n;
	static long[] pref, power, invPower;
	static long p = 911382323, p1 = 972663749;
	
	public static void main(String[] args) {
		pref = new long[n];
		power = new long[n];
		invPower = new long[n];
		
	    // use the right ones together; either all '0', '1', or '2'
				
	}

	public static long substringHash0(int i, int j) {
		if (i != 0) {
			return pref[j] - (pref[i-1] * power[j - i + 1]);
		}
		else {
			return pref[j];
		}
	}

	public static void prefHash0(String s) {
		if (s.length() > 0) {
			pref[0] = s.charAt(0) - 'a' + 1;
		}
		for (int i = 1; i < s.length(); i++) {
			pref[i] = pref[i-1] * p + (s.charAt(i) - 'a' + 1);
		}
	}

	public static void calcPower0() {
		power[0] = 1;
		for (int i = 1; i < n; i++) {
			power[i] = power[i-1] * p;
		}
	}
	
	public static long substringHash1(int i, int j) {
	    if (i != 0) {
	        return (((pref[j] - (pref[i-1] * power[j - i + 1]) % mod) % mod + mod) % mod);
	    }
	    else {
	        return pref[j];
	    }
	}
	
	public static void prefHash1(String s) {
	    if (s.length() > 0) {
			pref[0] = s.charAt(0) - 'a' + 1;
		}
	    for (int i = 1; i < s.length(); i++) {
	        pref[i] = (pref[i-1] * p + (s.charAt(i) - 'a' + 1)) % mod;
	    }
	}
	
	public static void calcPower1() {
	    power[0] = 1;
	    for (int i = 1; i < n; i++) {
	        power[i] = power[i-1] * p;
	        power[i] %= mod;
	    }
	}
	
	
	public static long substringHash2(int i, int j) {
	    if (i != 0) {
	        return ((((pref[j] - pref[i-1]) * invPower[i]) % mod + mod) % mod);
	    }
	    else {
	        return (pref[j] * invPower[i])%mod;
	    }
	}

	public static void prefHash2(String s) {
	    if (s.length() > 0) {
			pref[0] = s.charAt(0) - 'a' + 1;
		}
	    for (int i = 1; i < s.length(); i++) {
	        pref[i] = (pref[i-1] + (s.charAt(i) - 'a' + 1) * power[i]) % mod;
	    }
	}
	
	public static void calcPower2() {
	    power[0] = invPower[0] = 1;
	    for (int i = 1; i < n; i++) {
	        power[i] = power[i-1] * p;
	        power[i] %= mod;
	        invPower[i] = pow(power[i], mod-2, mod);
	    }
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
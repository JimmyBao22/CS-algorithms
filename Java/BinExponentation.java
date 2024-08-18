import java.util.*;
import java.io.*;

public class BinExponentation {
    
    public static void main(String[] args) {

    }

	// a ^ b
    public static long pow(long a, long b) {
    	long ans = 1;
    	while (b > 0) {
    		if ((b & 1) == 1) {
    			ans *= a;
    		}
    		a *= a;
    		b >>= 1;
    	}
    	return ans;
    }
	
	// (a ^ b) mod m
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

	// a ^ b, where b can be negative
	public static double negPow(double a, long b) {
		double ans = 1;
		
		if (b < 0) {
			b = -b;
			a = 1.0 / a;
		}
		
		while (b > 0) {
			if ((b & 1) == 1) {
				ans *= a;
			}
			a *= a;
			b >>>= 1;
		}
		return ans;
	}
}

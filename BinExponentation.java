import java.util.*;
import java.io.*;

public class binexponentation {
	public static void main(String[] args) {

	}
	
    // a^b
	public static long pow(long a, long b) {
    	long ans = 1;
    	while (b > 0) {
    		if (b%2 == 1) {
    			ans *= a;
    		}
    		a *= a;
    		b >>= 1;
    	}
    	return ans;
    }
	
    // a^b mod m
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

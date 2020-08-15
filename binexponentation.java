
import java.util.*;
import java.io.*;

public class binexponentation {
	public static void main(String[] args) {

	}
	
	static long pow(long a, long b) {
        // a^b
    	long ans=1;
    	while (b >0) {
    		if (b%2 == 1) {
    			ans *= a;
    		}
    		a *= a;
    		b >>=1;
    	}
    	return ans;
    }
	
	static long pow(long a, long b, long m) {
        // a^b mod m
    	long ans=1;
    	while (b >0) {
    		if (b%2 == 1) {
    			ans *= a%m;
    			ans %= m;
    		}
    		a *= a %m;
    		a%=m;
    		b >>=1;
    	}
    	return ans;
    }
	
}

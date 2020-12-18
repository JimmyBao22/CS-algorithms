import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class ModInverse {
	
	public static void main(String[] args) {

	}
	
	// If m is prime
    static long modInverse(long a, long m) {
        return pow(a, m - 2, m)%m;
    }
    
    static long pow(long a, long b, long m) {
        // a^b
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
    
    static long x,y;
    static long modInverse1(long a, long m) {         
    	x=y=1;
    	long g = gcdExtended(a, m);
    	if (g!=1) return -1; 	// if a and m are not coprime, there is not an inverse
    	long inverse = (x%m + m)%m;
    	return inverse;
    } 
    
    static long gcdExtended(long a, long b) { 
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
    
    static long modInverse2(long a, long m) {
    	BigInteger one = new BigInteger(a + "");
    	BigInteger mod = new BigInteger(m + "");
    	BigInteger res = one.modInverse(mod);
    	return res.longValue();
    }
}

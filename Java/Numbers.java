import java.util.*;
import java.io.*;

public class Numbers {
	
	public static void main(String[] args) {

	}
	
	public static long gcd(long a, long b) { 
        	if (b == 0) return a; 
        	return gcd(b, a % b); 
   	}
	
	public static long lcm(long a, long b) { 
        	return a / gcd(a, b) * b; 
    	} 
	
	public static ArrayList<Integer> getDivisors(int n) { 
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 1; i * i <= n; i++)  { 
		    if (n % i == 0) { 
				if (n / i == i) {
					ans.add(i);
				}
				else {
					ans.add(i);
					ans.add(n / i);
				}
			}
		} 
		return ans;
	}
	
	public static ArrayList<Integer> getDivisorsInOrder(int n) { 
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 1; i * i <= n; i++)  { 
		    if (n % i == 0) { 
				ans.add(i);
			} 
		} 
		for (int i = ans.size()-1; i >= 0; i--) {
			if (n / ans.get(i) != ans.get(i)) ans.add(n / ans.get(i));
		}
		return ans;
	}
	
	public static ArrayList<Integer> getPrimeDivisors(int n) { 
		ArrayList<Integer> ans = new ArrayList<>();
		if ((n & 1) == 0) {
		    ans.add(2);
		    while ((n & 1) == 0) n >>= 1;
		}
		for (int i = 3; i * i <= n; i += 2)  { 
		    if (n % i == 0) { 
				ans.add(i);
				while (n % i == 0) n /= i;
		    } 
		}
		if (n != 1) ans.add(n);
		return ans;
	}
	
	// true if n is prime; false otherwise
	public static boolean checkPrime(int n) {
		if (n <= 1) return false;
		if (n <= 3) return true;
		if (n % 2 == 0 || n % 3 == 0) return false;
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i+2) == 0) return false;
		}
		return true;
	}
}

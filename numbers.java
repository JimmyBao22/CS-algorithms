import java.util.*;
import java.io.*;

public class numbers {
	
	public static void main(String[] args) {

	}
	
	public static int gcd(int a, int b) { 
        	if (b == 0) return a; 
        	return gcd(b, a%b); 
    	} 
	
	public static int lcm(int a, int b) { 
        	return a/gcd(a, b) * b; 
    	} 
	
	public static ArrayList<Integer> getDivisors(int n) { 
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 1; i*i<=n; i++)  { 
		    if (n%i==0) { 
			if (n/i == i) a.add(i);
			else {
				a.add(i);
				a.add(n/i);
			}
		    } 
		} 
		return a;
	}
	
	public static ArrayList<Integer> getDivisorsinOrder(int n) { 
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 1; i*i<=n; i++)  { 
		    if (n%i==0) { 
			a.add(i);
		    } 
		} 
		for (int i = a.size()-1; i >= 0; i--) {
			if (n/a.get(i) != a.get(i)) a.add(n/a.get(i));
		}
		return a;
	}
	
	public static ArrayList<Integer> getprimeDivisors(int n) { 
		ArrayList<Integer> a = new ArrayList<>();
			if (n%2 == 0) {
				a.add(2);
				while (n%2 == 0) n >>=1;
			}
		for (int i = 3; i*i<=n; i+=2)  { 
		    if (n%i==0) { 
			a.add(i);
			while (n%i ==0) n/=i;
            	} 
		}
		if (n!=1) a.add(n);
		return a;
	}
	
	public static boolean prime(int n) {
		if (n<=1) return false;
		if (n<=3) return true;
		if (n%2==0 || n%3==0) return false;
		for (int i=5; i*i <= n; i+=6) {
			if (n%i==0 || n%(i+2)==0) return false;
		}
		return true;
	}
}

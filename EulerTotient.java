import java.util.*;
import java.io.*;

public class EulerTotient {
	
	static int MaxN = (int)1e5+2;
	static int[] arr = new int[MaxN];
	static ArrayList<Integer> primes = new ArrayList<>();
	
	public static void buildtotient() {
		for (int i=1; i<MaxN; i++) {
			arr[i] = i;
		}
		for (int i=2; i<MaxN; i+=2) {
			arr[i] = arr[i]/2;
		}
		for (int i=3; i<MaxN; i+=2) {
			if (arr[i] == i) {
				for (int j= i; j < MaxN; j+= i) {
					arr[j] /= i; arr[j] *= (i-1);
				}
			}
		}
	}
	
	public static void getprimes() {
		for (int i=1; i<MaxN; i++) {
			arr[i] = i;
		}		
		primes.add(2);
		for (int i=2; i<MaxN; i+=2) {
			arr[i] = arr[i]/2;
		}
		for (int i=3; i<MaxN; i+=2) {
			if (arr[i] == i) {
				primes.add(i);
				for (int j= i; j < MaxN; j+= i) {
					arr[j] /= i; arr[j] *= (i-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		buildtotient();
		
		for (int i=1; i<=10000; i++) {
			if (totient(i) != arr[i]) {
				System.out.println("FALSE");
				break;
			}
			System.out.println(arr[i]);
		}
		
	}
	
	public static long totient(long n) {
		long ret = 1;
		if (n%2 == 0) {
			n>>=1;
			while (n%2 == 0) {
				n >>=1;
				ret *= 2;
			}
		}
		for (long i = 3; i*i<=n; i+=2)  { 
		    if (n%i==0) { 
			ret *= (i-1);
			n /= i;
			while (n%i ==0) {
				ret *= i;
				n/=i;
			}
		    } 
		}
		if (n!=1) {
			ret *= (n-1);
		}
		return ret;
	}
}

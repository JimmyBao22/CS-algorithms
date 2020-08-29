import java.util.*;
import java.io.*;

public class EulerTotient {
	
	private static int max = (int)1e5;
	private static int[] arr = new int[max];
	
	public static void buildtotient() {
		for (int i=1; i<max; i++) {
			arr[i] = i;
		}
		for (int i=2; i<max; i+=2) {
			arr[i] = arr[i]/2;
		}
		for (int i=3; i<max; i+=2) {
			if (arr[i] == i) {
				for (int j= i; j < max; j+= i) {
					arr[j] /= i; arr[j] *= (i-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		buildtotient();
		
		for (int i=1; i<=100; i++) {
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

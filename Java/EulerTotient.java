import java.util.*;
import java.io.*;

public class EulerTotient {
	
	static int MaxN = (int)1e5+2;
	static int[] totient;
	static ArrayList<Integer> primes;
	
	public static void buildTotient() {
		for (int i = 1; i < MaxN; i++) {
			totient[i] = i;
		}
		for (int i = 2; i < MaxN; i += 2) {
			totient[i] = totient[i] / 2;
		}
		for (int i = 3; i < MaxN; i += 2) {
			if (totient[i] == i) {						// prime
				for (int j = i; j < MaxN; j += i) {
					totient[j] /= i;
					totient[j] *= (i-1);
				}
			}
		}
	}
	
	public static void getPrimes() {
		for (int i = 1; i < MaxN; i++) {
			totient[i] = i;
		}		
		primes.add(2);
		for (int i = 2; i < MaxN; i += 2) {
			totient[i] = totient[i] / 2;
		}
		for (int i = 3; i < MaxN; i += 2) {
			if (totient[i] == i) {				// prime
				primes.add(i);
				for (int j = i; j < MaxN; j += i) {
					totient[j] /= i;
					totient[j] *= (i-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		totient = new int[MaxN];
		primes = new ArrayList<>();

		buildTotient();

	}
	
	public static long totient(long n) {
		long ret = 1;
		if ((n & 1) == 0) {
			n >>= 1;
			while ((n & 1) == 0) {
				n >>= 1;
				ret <<= 1;
			}
		}
		for (long i = 3; i * i <= n; i += 2)  { 
		    if (n % i == 0) { 
			ret *= (i-1);
			n /= i;
			while (n % i == 0) {
				ret *= i;
				n /= i;
			}
		    } 
		}
		if (n != 1) {
			ret *= (n-1);
		}
		return ret;
	}
}

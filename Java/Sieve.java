import java.util.*;
import java.io.*;

public class Sieve {
	
	static int MaxN = (int)1e5+2;
	static int[] sieve;
	static ArrayList<Integer> primes;

	// calculates sieve array up to MaxN
	public static void sieve() {
		for (int i = 2; i < MaxN; i++) {
			sieve[i] = i;
		}
		for (int i = 4; i < MaxN; i += 2) {
			sieve[i] = 2;
		}
		for (int i= 3 ; i*i < MaxN; i += 2) {
			if (sieve[i] == i) {
				for (int j = i*i; j < MaxN; j += i) {
					sieve[j] = i;
				}
			}
		}
	}
	
	// get all primes up to max
	public static void getprimes(int max) {
		primes.add(2);
		for (int i = 2; i < max; i++) {
			sieve[i] = i;
		}
		for (int i = 4; i < max; i += 2) {
			sieve[i] = 2;
		}
		int i=3;
		for (; i * i < max; i += 2) {
			if (sieve[i] == i) {
				primes.add(i);
				for (int j = i*i; j < max; j += i) {
					sieve[j] = i;
				}
			}
		}
		for (; i < max; i += 2) {
			if (sieve[i] == i) primes.add(i);
		}
	}
	
	public static void main(String[] args) {
		sieve = new int[MaxN];
		primes = new ArrayList<>();

		sieve();
		
	}
}

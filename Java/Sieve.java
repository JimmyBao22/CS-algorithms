import java.util.*;
import java.io.*;

public class Sieve {
	
	static int MaxN = (int)1e5+2;
	static int[] sieve = new int[MaxN];
	static ArrayList<Integer> primes = new ArrayList<>();

	public static void sieve() {
		for (int i=2; i<MaxN; i++) {
			sieve[i] = i;
		}
		for (int i=4; i<MaxN; i+=2) {
			sieve[i] = 2;
		}
		for (int i=3; i*i <MaxN; i+=2) {
			if (sieve[i] == i) {
				for (int j= i*i; j < MaxN; j+= i) {
					sieve[j] = i;
				}
			}
		}
	}
	
	public static void getprimes() {
		primes.add(2);
		for (int i=2; i<MaxN; i++) {
			sieve[i] = i;
		}
		for (int i=4; i<MaxN; i+=2) {
			sieve[i] = 2;
		}
		int i=3;
		for (; i*i<MaxN; i+=2) {
			if (sieve[i] == i) {
				primes.add(i);
				for (int j= i*i; j < MaxN; j+= i) {
					sieve[j] = i;
				}
			}
		}
		for (; i<MaxN; i+=2) {
			if (sieve[i] == i) primes.add(i);
		}
	}
	
	public static void main(String[] args) {
		
		sieve();
		
	}
}

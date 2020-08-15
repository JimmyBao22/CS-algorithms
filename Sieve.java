
import java.util.*;
import java.io.*;

public class Sieve {
	
	private static int max = (int)1e5;
	private static int[] arr = new int[max];
	
	public static void sieve() {
		for (int i=2; i<max; i++) {
			arr[i] = i;
		}
		for (int i=4; i<max; i+=2) {
			arr[i] = 2;
		}
		for (int i=3; i*i <max; i+=2) {
			if (arr[i] == i) {
				for (int j= i*i; j < max; j+= i) {
					arr[j] = i;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		sieve();
		
		for (int i=0; i<max; i++) System.out.print(arr[i] + " ");
	}
}

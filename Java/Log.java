import java.util.*;
import java.io.*;

public class Log {
	public static void main(String[] args) {
		System.out.println(log2((long)2048));
		System.out.println(log2(2048));
		System.out.println(log(2048, 2));
		System.out.println(log((long)2048, 2));
	}
		
	public static int log2(int n) {
		return 31 - Integer.numberOfLeadingZeros(n);
	}
	
	public static long log2(long n) {
		return 63 - Long.numberOfLeadingZeros(n);
	}
	
	public static int log(int top, int base) {
		return (int)(Math.log(top)/Math.log(base));
	}
	
	public static long log(long top, long base) {
		return (long)(Math.log(top)/Math.log(base));
	}
	
	public static long slowlog2(long n) {
		long x = 1;
		while ((1l<<(x+1)) <= n) x++;
		return x;
	}
	
	public static int slowlog2(int n) {
		int x = 1;
		while ((1<<(x+1)) <= n) x++;
		return x;
	}
}

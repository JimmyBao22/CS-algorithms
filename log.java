
import java.util.*;
import java.io.*;

public class log {
	public static void main(String[] args) {
		System.out.println(log((long)2048));
		System.out.println(log(2048));
		System.out.println(builtinlog(2048, 2));
		System.out.println(builtinlog((long)2048, 2));
	}
	
	public static int builtinlog(int top, int base) {
		return (int)(Math.log(top)/Math.log(base));
	}
	
	public static long builtinlog(long top, long base) {
		return (long)(Math.log(top)/Math.log(base));
	}
	
	public static long log(long n) {
		long x = 1;
		while ((1l<<(x+1)) <= n) {
			x++;
		}
		return x;
	}
	
	public static int log(int n) {
		int x = 1;
		while ((1<<(x+1)) <= n) {
			x++;
		}
		return x;
	}
}

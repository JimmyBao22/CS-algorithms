
import java.util.*;
import java.io.*;

public class shuffle {
	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5,6,7};
		long[] b = new long[] {1l, 2l ,3l, 4l, 5l ,6l, 7l};
		shuffle(a);
		shuffle(b);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
	}
	
	public static void shuffle(int[] a) {
        Random get = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = get.nextInt(a.length);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
 
    public static void shuffle(long[] a) {
        Random get = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = get.nextInt(a.length);
            long temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    
    public static void shuffle(char[] a) {
        Random get = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = get.nextInt(a.length);
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}

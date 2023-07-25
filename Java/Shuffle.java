import java.util.*;
import java.io.*;

public class Shuffle {
	public static void main(String[] args) {

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
	
}

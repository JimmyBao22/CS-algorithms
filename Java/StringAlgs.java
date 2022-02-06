
import java.util.*;
import java.io.*;

public class StringAlgs {

	// In addition to "Hashing" and "Trie"
	
	public static void main(String[] args) {

	}
	
		// find lexicographically smaller in O(logn), with both hashes already calculated
			// when normal compareTo is O(n)
	static long[] pref1, pref2;				// pref1 is hash of "one", pref2 is hash of "two" 
	public static int smaller(String one, String two) {
		int min = -1;
		int max = Math.min(one.length(), two.length()) - 1;
		while (min < max) {
			int middle = (min + max + 1)/2;
			if (pref1[middle] == pref2[middle]) min = middle;
			else max = middle-1;
		}
		if (min == one.length()-1 && min == two.length()-1) return 0;		// equal
		else if (min == one.length()-1) return -1;							// one < two
		else if (min == two.length()-1) return 1;							// two < one
		else {
			if (one.charAt(min+1) < two.charAt(min+1)) return -1;			// one < two
			else return 1;													// two < one
		}
	}
	
		// constructs z-array in O(n)
		// z-array = for each index contains the length of the longest substring of s that
			// begins at that position and is a prefix of s
	public static ArrayList<Integer> Zalgorithm(String s) {
		int n = s.length();
		ArrayList<Integer> z = new ArrayList<>();
		for (int i=0; i<n; i++) z.add(0);
		int x = 0, y = 0;
		for (int i=1; i<n; i++) {
			z.set(i, Math.max(0, Math.min(z.get(i - x), y - i + 1)));
			while (i + z.get(i) < n && s.charAt(z.get(i)) == s.charAt(i + z.get(i))) {
				x = i; y = i+z.get(i); z.set(i, z.get(i)+1);
			}
		}
		return z;
	}
	
		// generate lowercase strings - https://www.baeldung.com/java-random-string
	public static String generateString(int targetStringLength) {
	    int leftLimit = 97; 			// letter 'a'
	    int rightLimit = 122; 			// letter 'z'
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
	        sb.append((char) randomLimitedInt);
	    }
	    return sb.toString();
	}
}

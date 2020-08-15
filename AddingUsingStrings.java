
import java.util.*;
import java.io.*;

public class AddingUsingStrings {
	public static void main(String[] args) {
		System.out.println(addition("0", "0"));
	}
	
	public static String addition(String a, String b) {
		String sum = "";
		int apointer = a.length()-1;
		int bpointer = b.length()-1;
		int carry = 0;
		while (apointer >= 0 || bpointer >= 0) {
			int cursum = apointer >= 0 ? (int)(a.charAt(apointer) - '0') : 0;
			apointer--;
			cursum += bpointer >= 0 ? (int)(b.charAt(bpointer) - '0') : 0;
			bpointer--;
			cursum += carry;
			carry = cursum / 10;
			cursum %= 10;
			sum += cursum;
		}
		if (carry > 0) sum += carry;
		
		// sum is backwards
		String f = "";
		for (int i = sum.length()-1; i >= 0; i--) f += sum.charAt(i);
		return f;
	}
	
		// this also works but is longer
	/* public static String addition (String a, String b) {
		String sum = "";
		int apointer = a.length()-1;
		int bpointer = b.length()-1;
		
		boolean carry = false;
		while (apointer >= 0 && bpointer >= 0) {
			int cursum = (int)(a.charAt(apointer) - '0');
			apointer--;
			cursum += (int)(b.charAt(bpointer) - '0');
			bpointer--;
			if (carry) cursum++;
			if (cursum >= 10) {
				carry = true;
				cursum %= 10;
			}
			else carry = false;
			sum += cursum;
		}
		while (apointer >= 0) {
			int cursum = (int)(a.charAt(apointer) - '0');
			apointer--;
			if (carry) cursum++;
			if (cursum >= 10) {
				carry = true;
				cursum %= 10;
			}
			else carry = false;
			sum += cursum;
		}
		while (bpointer >= 0) {
			int cursum = (int)(b.charAt(bpointer) - '0');
			bpointer--;
			if (carry) cursum++;
			if (cursum >= 10) {
				carry = true;
				cursum %= 10;
			}
			else carry = false;
			sum += cursum;
		}
		if (carry) sum += "1";
		
		// sum is backwards
		String f = "";
		for (int i = sum.length()-1; i >= 0; i--) f += sum.charAt(i);
		return f;
	} */
}

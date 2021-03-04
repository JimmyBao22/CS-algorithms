
import java.util.*;
import java.io.*;

public class TwoPointers {

	public static void main(String[] args) {

	}
	
	// O(n)
	public static long calculate(int n, long k, long[] arr) {
		long ans=0;
		
			// one = left one, two = right one
		ArrayDeque<Long> one = new ArrayDeque<>(), onemin = new ArrayDeque<>(), 
				onemax = new ArrayDeque<>(), two = new ArrayDeque<>(), 
				twomin = new ArrayDeque<>(), twomax = new ArrayDeque<>();		
		
		for (int i=0; i<n; i++) {
			two.push(arr[i]);
			if (twomin.size() > 0) twomin.push(Math.min(arr[i], twomin.peek()));
			else twomin.push(arr[i]);
			if (twomax.size() > 0) twomax.push(Math.max(arr[i], twomax.peek()));
			else twomax.push(arr[i]);
			
			if (one.isEmpty()) {
				// move everything over to one
				while (!two.isEmpty()) {
					one.push(two.pop());
					twomin.pop(); twomax.pop();
					if (onemin.size() > 0) onemin.push(Math.min(one.peek(), onemin.peek()));
					else onemin.push(one.peek());
					if (onemax.size() > 0) onemax.push(Math.max(one.peek(), onemax.peek()));
					else onemax.push(one.peek());
				}	
			}
			
			long min = onemin.peek();
			if (twomin.size() > 0) min = Math.min(min, twomin.peek());
			long max = onemax.peek();
			if (twomax.size() > 0) max = Math.max(max, twomax.peek());
			
			while (max - min > k) {
				one.pop();
				onemin.pop(); onemax.pop();
				
				if (one.isEmpty()) {
					// move everything over to one
					while (!two.isEmpty()) {
						one.push(two.pop());
						twomin.pop(); twomax.pop();
						if (onemin.size() > 0) onemin.push(Math.min(one.peek(), onemin.peek()));
						else onemin.push(one.peek());
						if (onemax.size() > 0) onemax.push(Math.max(one.peek(), onemax.peek()));
						else onemax.push(one.peek());
					}	
				}
				
				min = onemin.peek();
				if (twomin.size() > 0) min = Math.min(min, twomin.peek());
				max = onemax.peek();
				if (twomax.size() > 0) max = Math.max(max, twomax.peek());
			}
			
			ans = Math.max(ans, one.size() + two.size());
		}
		return ans;
	}
}
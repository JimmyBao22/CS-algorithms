
import java.util.*;
import java.io.*;

public class TwoPointers {

	public static void main(String[] args) {

	}
	
	// O(n)
	public static long calculate(int n, long k, long[] arr) {
		long ans = 0;
		
			// one = left one, two = right one
		ArrayDeque<Long> one = new ArrayDeque<>(), oneMin = new ArrayDeque<>(), 
				oneMax = new ArrayDeque<>(), two = new ArrayDeque<>(), 
				twoMin = new ArrayDeque<>(), twoMax = new ArrayDeque<>();		
		
		for (int i = 0; i < n; i++) {
			two.push(arr[i]);
			if (twoMin.size() > 0) twoMin.push(Math.min(arr[i], twoMin.peek()));
			else twoMin.push(arr[i]);
			if (twoMax.size() > 0) twoMax.push(Math.max(arr[i], twoMax.peek()));
			else twoMax.push(arr[i]);
			
			if (one.isEmpty()) {
				moveFromTwoToOne(one, oneMin, oneMax, two, twoMin, twoMax);
			}
			
			long min = oneMin.peek();
			if (twoMin.size() > 0) min = Math.min(min, twoMin.peek());
			long max = oneMax.peek();
			if (twoMax.size() > 0) max = Math.max(max, twoMax.peek());
			
			while (max - min > k) {
				one.pop();
				oneMin.pop(); oneMax.pop();
				
				if (one.isEmpty()) {
					moveFromTwoToOne(one, oneMin, oneMax, two, twoMin, twoMax);
				}
				
				min = oneMin.peek();
				if (twoMin.size() > 0) min = Math.min(min, twoMin.peek());
				max = oneMax.peek();
				if (twoMax.size() > 0) max = Math.max(max, twoMax.peek());
			}
			
			ans = Math.max(ans, one.size() + two.size());
		}
		return ans;
	}

	public static void moveFromTwoToOne(ArrayDeque<Long> one, ArrayDeque<Long> oneMin, ArrayDeque<Long> oneMax,
											ArrayDeque<Long> two, ArrayDeque<Long> twoMin, ArrayDeque<Long> twoMax) {
		// move everything over from two to one
		while (!two.isEmpty()) {
			one.push(two.pop());
			twoMin.pop(); twoMax.pop();
			if (oneMin.size() > 0) oneMin.push(Math.min(one.peek(), oneMin.peek()));
			else oneMin.push(one.peek());
			if (oneMax.size() > 0) oneMax.push(Math.max(one.peek(), oneMax.peek()));
			else oneMax.push(one.peek());
		}	
	}
}
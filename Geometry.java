
import java.util.*;
import java.io.*;

public class Geometry {
	public static void main(String[] args) {

	}
	
	// note: points need to be in clockwise/counterclockwise order beforehand
	public static double ShoelaceArea (long[][] points) {
		int n = points.length; 
		long firstsum=0;
		long secsum=0;
		for (int i=0; i<n; i++) {
			if (i == n-1) {
				firstsum += points[i][0] * points[0][1];
				secsum += points[i][1] * points[0][0];
			}
			else {
				firstsum += points[i][0] * points[i+1][1];
				secsum += points[i][1] * points[i+1][0];
			}
		}
		return (double)Math.abs(firstsum - secsum) / 2.0;
	}
	
	// counterclockwise --> x,y around a,b
	public static long[] Rotate(long x, long y, long a, long b, int degree) {
		long[] ans = new long[2];
		if (degree == 0) {
			ans[0] = x;
			ans[1] = y;
		}
		else if (degree == 90) {
			ans[0] = b - y + a;
			ans[1] = x - a + b;
		}
		else if (degree == 180) {
			ans[0] = 2*a - x;
			ans[1] = 2*b - y;
		}
		else if (degree == 270) {
			ans[0] = y - b + a;
			ans[1] = a - x + b;
		}
		return ans;
	}
	
	// point[] = {x, y}
	public static boolean IsSquare(long[] pointone, long[] pointtwo, long[] pointthree, long[] pointfour) {
		long d1 = DistSquared(pointone, pointtwo);
		long d2 = DistSquared(pointone, pointthree);
		long d3 = DistSquared(pointone, pointfour);
		
		if (d1 == 0 || d2 == 0 || d3 == 0) return false; 
		
		if (d1 == d2 && 2 * d1 == d3 && 2 * DistSquared(pointtwo, pointfour) == DistSquared(pointtwo, pointthree)) { 
	        return true; 
	    } 
		
		if (d1 == d3 && 2 * d1 == d2 && 2 * DistSquared(pointtwo, pointthree) == DistSquared(pointtwo, pointfour)) { 
	        return true; 
	    } 
		
		if (d2 == d3 && 2 * d2 == d1 && 2 * DistSquared(pointtwo, pointthree) == DistSquared(pointfour, pointthree)) { 
	        return true; 
	    } 
		
		return false;
	}
	
	public static long DistSquared(long[] pointone, long[] pointtwo) {
		return (pointone[0] - pointtwo[0])*(pointone[0] - pointtwo[0]) + (pointone[1] - pointtwo[1])*(pointone[1] - pointtwo[1]);
	}
	
		// takes in 3 points. returns +1 if a->b->c is a counterclockwise angle, 
		// -1 if a->b->c is a clockwise angle, and 0 if a->b->c are collinear
	public static double ccw(long[] A, long[] B, long[] C) {
	    return ((double)(B[0] - A[0]) * (C[1] - B[1]) - (B[1] - A[1]) * (C[0] - B[0])) * 0.5;
	}

}

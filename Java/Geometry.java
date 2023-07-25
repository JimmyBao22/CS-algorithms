import java.util.*;
import java.io.*;

public class Geometry {
	
	public static void main(String[] args) {

	}
	
	// note: points need to be in clockwise/counterclockwise order beforehand
	public static double shoelaceArea (long[][] points) {
		int n = points.length; 
		long firstsum = 0;
		long secsum = 0;
		for (int i = 0; i < n; i++) {
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
	
	// counterclockwise (x,y) around (a,b)
	public static double[] rotate(double x, double y, double a, double b, double degree) {
		double rad = toRadians(degree);
		double ansx = x * Math.cos(rad) - a * Math.cos(rad) - y * Math.sin(rad) + b * Math.sin(rad) + a;
		double ansy = x * Math.sin(rad) + y * Math.cos(rad) - a * Math.sin(rad) - b * Math.cos(rad) + b;
		return new double[] {ansx, ansy};
	}
	
	public static double toRadians(double degree) {
		return degree * Math.PI / 180;
	}
	
	// point[] = {x, y}
	public static boolean isSquare(long[] pointone, long[] pointtwo, long[] pointthree, long[] pointfour) {
		long d1 = distSquared(pointone, pointtwo);
		long d2 = distSquared(pointone, pointthree);
		long d3 = distSquared(pointone, pointfour);
		
		if (d1 == 0 || d2 == 0 || d3 == 0) {
			return false; 
		}
		
		if (d1 == d2 && 2 * d1 == d3 && 2 * distSquared(pointtwo, pointfour) == distSquared(pointtwo, pointthree)) { 
			return true; 
		} 
		
		if (d1 == d3 && 2 * d1 == d2 && 2 * distSquared(pointtwo, pointthree) == distSquared(pointtwo, pointfour)) { 
			return true; 
		} 
		
		if (d2 == d3 && 2 * d2 == d1 && 2 * distSquared(pointtwo, pointthree) == distSquared(pointfour, pointthree)) { 
			return true; 
		} 
		
		return false;
	}
	
	public static long distSquared(long[] pointone, long[] pointtwo) {
		return (pointone[0] - pointtwo[0]) * (pointone[0] - pointtwo[0]) + (pointone[1] - pointtwo[1]) * (pointone[1] - pointtwo[1]);
	}
	
		// takes in 3 points. returns +1 if a->b->c is a counterclockwise angle, 
		// -1 if a->b->c is a clockwise angle, and 0 if a->b->c are collinear
	public static double ccw(Point a, Point b, Point c) {
		double val = (double)(b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
		return val == 0 ? 0 : val < 0 ? -1 : 1;
	}
	
		// returns whether a1 - a2 line segment intersects b1 - b2 line segment
	public static boolean intersect(Point a1, Point a2, Point b1, Point b2) {
		double o1 = ccw(a1, a2, b1); 
		double o2 = ccw(a1, a2, b2); 
		double o3 = ccw(b1, b2, a1); 
		double o4 = ccw(b1, b2, a2); 
		
		if (o1 != o2 && o3 != o4) return true;
		
		if (o1 == 0 && onSegment(a1, b1, a2)) return true; 
		if (o2 == 0 && onSegment(a1, b2, a2)) return true; 	  
	   	if (o3 == 0 && onSegment(b1, a1, b2)) return true; 	  
	    	if (o4 == 0 && onSegment(b1, a2, b2)) return true; 
	    
	    return false;
	}
	
		// point c lies on segment a-b
	public static boolean onSegment(Point a, Point c, Point b) {
		if (c.x <= Math.max(a.x, b.x) && c.x >= Math.min(a.x, b.x) && 
		        c.y <= Math.max(a.y, b.y) && c.y >= Math.min(a.y, b.y)) return true; 
		  
		return false; 
	}
	
		// y = m1x+b1 and y = m2x+b2
	public static Point lineLineIntersection(double m1, double b1, double m2, double b2) { 
		m1 = -m1;
		m2 = -m2;
		double determinant = m1 - m2; 
       
		if (determinant == 0) { 
		    // parallel
		    return new Point(Double.MAX_VALUE, Double.MAX_VALUE); 
		} 
		else { 
		    double x = (b1 - b2) / determinant; 
		    double y = (m1 * b2 - m2 * b1) / determinant; 
		    return new Point(x, y); 
		} 
	}
	
	public static Point pointLineIntersection(Point a, Point b, Point c, Point d) { 
		// Line ab represented as a1x + b1y = c1 
		double a1 = b.y - a.y; 
		double b1 = a.x - b.x; 
		double c1 = a1 * (a.x) + b1 * (a.y); 

		// Line cd represented as a2x + b2y = c2 
		double a2 = d.y - c.y; 
		double b2 = c.x - d.x; 
		double c2 = a2 * (c.x)+ b2 * (c.y); 

		double determinant = a1 * b2 - a2 * b1; 

		if (determinant == 0) { 
		    // parallel 
		    return new Point(Double.MAX_VALUE, Double.MAX_VALUE); 
		} 
		else { 
		    double x = (b2*c1 - b1*c2) / determinant; 
		    double y = (a1*c2 - a2*c1) / determinant; 
		    return new Point(x, y); 
		} 
	} 

	static class Point {
		double x, y;
		Point(double a, double b) {
			x = a;
			y = b;
		}
		void print() {
			System.out.println(x + " " + y);
		}
	}
}

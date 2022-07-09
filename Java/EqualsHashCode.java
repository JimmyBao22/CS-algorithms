
import java.util.*;
import java.io.*;

public class EqualsHashCode {

	public static void main(String[] args) {

	}
	
	static class A {
		int a;
		
		@Override    
		public boolean equals(Object o) {        
		    A cur = (A) o;   
		    if (a != cur.a) return false;
		    return true;
		}    
		 
		@Override    
		public int hashCode() {        
			return a * 97 + 103;
		} 
	}
}

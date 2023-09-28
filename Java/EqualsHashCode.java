
import java.util.*;
import java.io.*;

public class EqualsHashCode {

	public static void main(String[] args) {

	}
	
	static class A {
		int element;
		
		@Override    
		public boolean equals(Object o) {  
			if (o == null || getClass() != o.getClass()) return false;     
		    A cur = (A) o;   
		    if (element != cur.element) return false;
		    return true;
		}    
		 
		@Override    
		public int hashCode() {        
			return element * 97 + 103;
		} 
	}
}

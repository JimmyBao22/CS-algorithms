import java.util.*;

public class Trie {
	
	public static void main(String[] args) {

	}
	
	static class trie {
		
		trie[] children = new trie[26];
		int wordCount = 0;					// number of words ending here
		
		void insert(String s) {
			trie curr = this;
			for(int i=0; i<s.length(); i++) {
				int at = s.charAt(i) - 'a';
				if(curr.children[at] == null) curr.children[at] = new trie();
				curr = curr.children[at];
			}
			curr.wordCount++;
		}
		
		boolean contains(String s) {
			trie curr = this;
			for(int i=0; i<s.length(); i++) {
				int at = s.charAt(i) - 'a';
				if(curr.children[at] == null) return false;
				curr = curr.children[at];
			}
			return curr.wordCount > 0;
		}
		
		void delete(String s) {
			delete(s, 0);
		}
		
		boolean delete(String s, int depth) {
			if(depth == s.length()) {
				wordCount--;
				if (wordCount != 0) return false;	// still has words ending here
				for(trie c : children) {
					if(c != null) return false;		// still has words going off here
				}
				return true;
			}
		
			int nextChar = s.charAt(depth) - 'a';
			if(children[nextChar] == null) {
				return false;
			}
			if(children[nextChar].delete(s, depth+1)) {
				children[nextChar] = null;
				return wordCount == 0;
			}
			return false;
		}
	}
}
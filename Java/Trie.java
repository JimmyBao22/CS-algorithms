import java.util.*;

public class Trie {
	
	Trie[] children;
	int wordCount;											// number of words ending here

	Trie() {
		children = new Trie[26];
		wordCount = 0;	
	}
	
	void insert(String s) {
		Trie cur = this;
		for(int i = 0; i < s.length(); i++) {
			int at = s.charAt(i) - 'a';
			if (cur.children[at] == null) cur.children[at] = new Trie();
			cur = cur.children[at];
		}
		cur.wordCount++;
	}
	
	boolean contains(String s) {
		Trie cur = this;
		for(int i = 0; i < s.length(); i++) {
			int at = s.charAt(i) - 'a';
			if (cur.children[at] == null) return false;
			cur = cur.children[at];
		}
		return cur.wordCount > 0;
	}
	
	void delete(String s) {
		delete(s, 0);
	}
	
	boolean delete(String s, int depth) {
		if(depth == s.length()) {
			wordCount--;
			if (wordCount != 0) return false;				// still has words ending here
			for (Trie child : children) {
				if (child != null) return false;					// still has words going off here
			}
			return true;
		}
	
		int nextChar = s.charAt(depth) - 'a';
		if (children[nextChar] == null) {
			return false;
		}
		if (children[nextChar].delete(s, depth + 1)) {
			children[nextChar] = null;
			return wordCount == 0;
		}
		return false;
	}
}
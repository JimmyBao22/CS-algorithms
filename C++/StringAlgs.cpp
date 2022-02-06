#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <algorithm>
#include <deque>
#include <queue>
#include <stack>
#include <set>
#include <string>
#include <string.h>
#include <array>
#include <unordered_map>
#include <unordered_set>
#include <iomanip>
using namespace std;
typedef long long ll;
typedef long double ld;
#define pb push_back

	// In addition to "Hashing" and "Trie"

const int MaxN = 1e5+1;

    // find lexicographically smaller in O(logn), with both hashes already calculated
            // when normal compareTo is O(n)
ll pref1[MaxN], pref2[MaxN];				// pref1 is hash of "one", pref2 is hash of "two" 
int smaller(string one, string two) {
    int mn = -1;
    int mx = min(one.length(), two.length()) - 1;
    while (mn < mx) {
        int middle = (mn + mx + 1)/2;
        if (pref1[middle] == pref2[middle]) mn = middle;
        else mx = middle-1;
    }
    if (mn == one.length()-1 && mn == two.length()-1) return 0;		    // equal
    else if (mn == one.length()-1) return -1;							// one < two
    else if (mn == two.length()-1) return 1;							// two < one
    else {
        if (one[mn+1] < two[mn+1]) return -1;			                // one < two
        else return 1;													// two < one
    }
}

	// constructs z-array in O(n)
    // z-array = for each index contains the length of the longest substring of s that
        // begins at that position and is a prefix of s
vector<int> Zalgorithm(string s) {
    int n = s.length();
    vector<int> z(n);
    int x = 0, y = 0;
    for (int i=1; i<n; i++) {
        z[i] = max(0, min(z[i - x], y - i + 1));
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
            x = i; y = i + z[i]; z[i]++;
        }
    }
    return z;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    
}
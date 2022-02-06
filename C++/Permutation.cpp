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

// ABC --> [A, B, C], [A, C, B], [B, A, C], [B, C, A], [C, A, B], [C, B, A]
void permutation(char arr[], int n) {
    sort(arr, arr+n);
    do {
        for (int i=0; i<n; i++) cout << arr[i] << " ";
        cout << "\n";
    }
    while(next_permutation(arr, arr + n));
}

// ABC --> 	[], [A], [B], [C], [A, B], [A, C], [B, C], [A, B, C]
void combination(char arr[], int n, vector<char> include, int i) {
    if (i == n) {
        for (int j=0; j<include.size(); j++) cout << include[j] << " ";
        cout << "\n";
        return;
    }
    combination(arr, n, include, i+1);		// don't include
    include.push_back(arr[i]); 	            // include
    combination(arr, n, include, i+1);
    include.erase(include.end()-1);		    // undo
}

// ABC (length_needed = 2) --> [A, B], [A, C], [B, C]
void combination_size(char arr[], int n, vector<char> include, int i, int length_needed) {
    if (include.size() == length_needed) {
        for (int j=0; j<include.size(); j++) cout << include[j] << " ";
        cout << "\n";
        return;
    }
    if (i >= n || n - i + include.size() < length_needed) return;
    combination_size(arr, n, include, i+1, length_needed);		// don't include
    include.push_back(arr[i]); 	                                // include
    combination_size(arr, n, include, i+1, length_needed);
    include.erase(include.end()-1);		                        // undo
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

}
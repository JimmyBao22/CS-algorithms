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

const int MaxN = 1e5+1;
int heights[MaxN];
int n;
stack<int> s;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    for (int i=0; i<n; i++) {
        cin >> heights[i];
    }
    
    int maxArea = 0;
    int i=0;
    while (i<n) {
        if (s.empty() || heights[i] > heights[s.top()]) {
            s.push(i++);
        }
        else {
            int cur = s.top();
            s.pop();
            int left = 0;
            if (!s.empty()) left = s.top()+1;
            int right = i-1;
            maxArea = max(maxArea, heights[cur] * (right - left + 1));
        }
    }
    while (!s.empty()) {
        int cur = s.top();
        s.pop();
        int left = 0;
        if (!s.empty()) left = s.top()+1;
        int right = n-1;
        maxArea = max(maxArea, heights[cur] * (right - left + 1));
    }
}
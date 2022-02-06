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

const int MaxN = 1e5+10;
vector<int> g[MaxN], queries[MaxN];
int ans[MaxN], value[MaxN];
vector<map<int, int>> vals (MaxN);
int n, m;

void smalltolarge(int node, int parent) {
    int biggest = node;			// node with biggest length
    for (auto to : g[node]) {
        if (to == parent) continue;
        smalltolarge(to, node);
        if (vals[to].size() > vals[biggest].size()) {
            biggest = to;
        }
    }
    
        // swap over so don't need to copy
    swap(vals[node], vals[biggest]);
    
    for (auto to : g[node]) {
        if (to == parent || to == biggest) continue;
        for (auto a : vals[to]) {		// copy over
            vals[node][a.first] += a.second;
        }
    }
            
    // add/remove values at node
    vals[node][value[node]]++;
    
    // answer queries
    for (auto i : queries[node]) {
        ans[i] = (*vals[node].begin()).first;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for (int i=0; i<n-1; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    for (int i=0; i<m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        queries[a].push_back(b);
    }

    smalltolarge(0,-1);

}
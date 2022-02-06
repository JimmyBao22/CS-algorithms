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
vector<int> g[MaxN], reverseg[MaxN];
bool visited[MaxN];
stack<int> s;
vector<vector<int>> SCC;      // stores all the components
int n, m;

void dfsRev(int cur, vector<int>& curarr) {
    visited[cur] = true;
    curarr.pb(cur);
    for (auto i : reverseg[cur]) {
        if (!visited[i]) dfsRev(i, curarr);
    }
}

void fillstack(int cur) {
    visited[cur] = true;
    for (auto i : g[cur]) {
        if (!visited[i]) fillstack(i);
    }
    s.push(cur);
}

void findSCC() {
    for (int i=0; i<n; i++) {
        if (!visited[i]) fillstack(i);
    }
    
    fill(visited, visited+n, false);
    
    while (!s.empty()) {
        int cur = s.top();
        s.pop();
        if (!visited[cur]) {
            vector<int> curarr;
            dfsRev(cur, curarr);
            SCC.pb(curarr);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for (int i=0; i<m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].pb(b);
        reverseg[b].pb(a);
    }

    findSCC();
    
}
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
const int MaxM = 1e5+10;
vector<int> g[MaxN];
bool visited[MaxN], open[MaxN];
vector<int> cycle;
int n, m;

    // true = cycle, false = no cycle
bool dfsCheck(int node) { 
    if (open[node]) return true;
    if (visited[node]) return false;
    visited[node] = true;
    open[node] = true;
    for (auto i : g[node]) {
        if (dfsCheck(i)) return true;
    }
    open[node] = false;
    return false;
}

bool findCycle(int node) {
    visited[node] = true;
    open[node] = true;
    for (auto i : g[node]) {
        if (open[i]) {
            cycle.pb(node);     						// start cycle
            open[node] = false;
            open[i] = false;
            return true;
        }
        else if (!visited[i]) {
            if (findCycle(i)) {         				// continue cycle
                if (open[node]) {
                    cycle.pb(node);
                    open[node] = false;
                    return true;
                }
                else {
                    cycle.pb(node);     				// end cycle
                    return false;
                }
            }
            if (!cycle.empty()) return false;       	// finished cycle
        }
    }
    open[node] = false;
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].push_back(b);
        // g[b].push_back(a);       // if undirected
    }
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) dfsCheck(i);
    }

    fill(visited, visited+n,false);
    fill(open, open+n, false);

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            findCycle(i);
            if (!cycle.empty()) break;
        }
    }
    reverse(cycle.begin(), cycle.end());

}
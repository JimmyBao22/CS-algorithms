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

const int MaxN = 1e5+10, LOG = 31;
vector<int> g[MaxN];
int parent[MaxN][LOG], depth[MaxN];
int n, m;

void dfs(int node, int p) {
    parent[node][0] = p;
    for (auto i : g[node]) {
        if (i == p) continue;
        depth[i] = depth[node] + 1;
        dfs(i, node);
    }
}

void precomp() {
    parent[0][0] = -1;	// parent of root = -1
    for (int i=1; i<LOG; i++) {
        for (int j=0; j<n; j++) {
            if (parent[j][i-1] != -1) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
            else parent[j][i] = -1;
        }
    }
}

int lca(int u, int v) {	// lca of u and v
    if (depth[u] < depth[v]) {
        swap(u,v);
    }
    // depth[u] >= depth[v];
    int diff = depth[u] - depth[v];
    for (int i=0; i<LOG; i++) {
        if (((1 << i) & diff) > 0) {
            u = parent[u][i];
        }
    }
    if (u == v) return u;
    for (int i=LOG-1; i>=0; i--) {
        if (parent[u][i] != parent[v][i]) {
            u = parent[u][i]; v = parent[v][i];
        }
    }
    return parent[u][0];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    dfs(0, -1);	// start from root node
    precomp();

}
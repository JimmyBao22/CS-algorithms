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
vector<int> g[MaxN];
stack<int> s;
vector<int> finalsequence;
bool visited[MaxN];
int n, m;

void fillstack(int cur) {
    visited[cur] = true;
    for (auto i : g[cur]) {
        if (!visited[i]) fillstack(i);
    }
    s.push(cur);
}

void toposort() {
    for (int i=0; i<n; i++) {
        if (!visited[i]) fillstack(i);
    }
    
    while (!s.empty()) {
        finalsequence.pb(s.top());
        s.pop();
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
    }

    toposort();

}
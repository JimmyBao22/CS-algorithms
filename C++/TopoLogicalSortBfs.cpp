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
queue<int> q;
vector<int> fs;
int indegree[MaxN];
bool visited[MaxN];
int n, m;

void toposort() {
    for (int i=0; i<n; i++) {
        if (indegree[i] == 0) {
            q.push(i);
            visited[i] = true;
        }
    }
    
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        for (auto a : g[cur]) {
            indegree[a]--;
            if (indegree[a] == 0 && !visited[a]) {
                q.push(a);
                visited[a] = true;
            }
        }
        fs.push_back(cur);
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
        indegree[b]++;
    }

    toposort();

}
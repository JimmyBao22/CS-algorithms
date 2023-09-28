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
vector<pair<ll, int>> g[MaxN];      // length, dest
ll dist[MaxN];
int parent[MaxN];
ll INF = 1e18;
int n, m;

    // O((N+M)logM)
void dijkstras(int start) {
    using T = pair<ll, int>;
    priority_queue<T, vector<T>, greater<T>> pq;
    bool visited[n];
    fill(dist, dist+n, INF);
    fill(visited, visited+n, false);
    dist[start] = 0;
    parent[start] = -1;
    
    pq.push({0, start});
    while (!pq.empty()) {
        T cur = pq.top();
        pq.pop();
        int node = cur.second;
        if (visited[node]) continue;
        visited[node] = true;
        
        for (auto i : g[node]) {
            if (visited[i.second]) continue;
            
            if (cur.first + i.first < dist[i.second]) {
                dist[i.second] = cur.first + i.first;
                parent[i.second] = node;
                pq.push({dist[i.second], i.second});
            }
        }
    }
}

    // O(N^2)
void dijkstras2(int start) {
    bool visited[n];
    fill(dist, dist+n, INF);
    fill(visited, visited+n, false);
    dist[start] = 0;
    parent[start] = -1;
    
    while (true) {
        // find the smallest one
        int smallest=-1;
        ll minval=INF;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && dist[j] < minval) {
                minval = dist[j];
                smallest = j;
            }
        }
        if (smallest == -1) break;
        
        for (auto a : g[smallest]) {
            if (!visited[a.second] && dist[a.second] > minval + a.first) {
                dist[a.second] = minval + a.first;
                parent[a.second] = smallest;
            }
        }
        visited[smallest] = true;
    }
}

vector<int> Backtrack(int dest) {
    vector<int> path;
    if (dist[dest] == INF) return path;
    while (dest != -1) {
        path.push_back(dest);
        dest = parent[dest];
    }
    return path;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int a, b; ll weight;
        cin >> a >> b >> weight;
        a--; b--;

        g[a].pb({weight, b});
        g[b].pb({weight, a});
    }
}
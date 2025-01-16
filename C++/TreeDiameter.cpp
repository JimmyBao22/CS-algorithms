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
 
const int MaxN = 2e5+10;
vector<int> g[MaxN];
int n, dist[MaxN], parent[MaxN], maxDist[MaxN];

// gets max distance of node to all other nodes
void getMaxDistances(int node, int p1, int p2, int d) {
    maxDist[node] = d;
    for (auto to : g[node]) {
        if (to != p1 && to != p2) {
            getMaxDistances(to, node, node, d+1);
        }
    }
}

void dfs(int node, int p) {
    parent[node] = p;
    for (auto to : g[node]) {
        if (to != p) {
            dist[to] = dist[node]+1;
            dfs(to, node);
        }
    }
}
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
 
    cin >> n;
    for (int i = 0; i < n-1; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].push_back(b);
        g[b].push_back(a);
    }
 
    // first dfs - get node with maximum distance from node 0
    dfs(0, -1);
 
    int furthestNode = 0;
    int maxDistance = dist[0];
    for (int i = 0; i < n; i++) {
        if (dist[i] > maxDistance) {
            maxDistance = dist[i];
            furthestNode = i;
        }
        // reset distance array
        dist[i] = 0;
    }
 
    // second dfs - get the node at the end of the diameter
    dfs(furthestNode, -1);

    furthestNode = 0;
    maxDistance = dist[0];
    for (int i = 0; i < n; i++) {
        if (dist[i] > maxDistance) {
            furthestNode = i;
            maxDistance = dist[i];
        }
    }

    // sequence stores the vector of nodes representing the diameter
    // sequence.size() = maxDistance + 1
    vector<int> sequence;
    while (furthestNode != -1) {
        sequence.push_back(furthestNode);
        furthestNode = parent[furthestNode];
    }
 
    // maxDist[i] = max distance of node i to another other node
    maxDist[sequence[0]] = maxDistance;
    maxDist[sequence[sequence.size()-1]] = maxDistance;
    for (int i = 1; i < (int)sequence.size()-1; i++) {
        getMaxDistances(sequence[i], sequence[i-1], sequence[i+1], max(i, maxDistance-i));
    }
    

}

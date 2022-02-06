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

struct Edge {
    int start, dest; ll weight;
};

const int MaxN = 1e5+10, MaxM = 1e5+10;
ll INF = 1e18;
Edge edges[MaxM];
ll dist[MaxN];
int n, m;

bool BF(int start) {
    for (int i=0; i<n; i++) dist[i] = INF;
    dist[start] = 0;
    for (int i=0; i<n-1; i++) {
        for (int j=0; j<m; j++) {
            int from = edges[j].start;
            int to = edges[j].dest;
            ll weight = edges[j].weight;
            dist[to] = min(dist[to], dist[from]+weight);
        }
    }
    
    // check for negative cycles
    for (int j=0; j<m; j++) {
        int from = edges[j].start;
        int to = edges[j].dest;
        ll weight = edges[j].weight;
        if (dist[from]+weight < dist[to]) return false;	// negative cycle
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    for (int i=0; i<m; i++) {
        cin >> edges[i].start >> edges[i].dest >> edges[i].weight;
        edges[i].start--; edges[i].dest--;
    }
    
    int start;
    cin >> start;
    start--;
    BF(start);
}
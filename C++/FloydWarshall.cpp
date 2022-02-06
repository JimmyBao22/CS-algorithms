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
ll INF = 1e18;
vector<pair<ll, int>> g[MaxN];      // length, dest
ll dist[MaxN][MaxN];
int n, m;

void FW () {
    for (int k=0; k<n; k++) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            dist[i][j] = INF;
        }
        dist[i][i] = 0;
    }

    for (int i=0; i<m; i++) {
        int one, two; ll weight;
        cin >> one >> two >> weight;
        one--; two--;
        g[one].push_back({weight, two});
        g[two].push_back({weight, one});
        dist[one][two] = weight;
        dist[two][one] = weight;
    }

    FW();
    
}
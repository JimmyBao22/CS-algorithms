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

struct DSU {
    int n;
    vector<int> parent;
    vector<int> size;
    
    DSU(int n) : n(n) {
        for (int i = 0; i < n; i++) {
            parent.push_back(i);
            size.push_back(1);
        }
    }

    void makeSet(int a) {
        n++;
        parent.push_back(a);
        size.push_back(1);
    }

    int findSet(int a) {
        if (a == parent[a]) return a;
        return parent[a] = findSet(parent[a]);
    }
    
    void dsuUnion(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b) return;
        
        if (size[a] < size[b]) {
            parent[a] = b;
            size[b] += size[a];
        }
        else {
            parent[b] = a;
            size[a] += size[b];
        }
    }
};

struct DSU2D {
    int n, m;
    int mult = 1e4;
    vector<vector<int>> parent;
    vector<vector<int>> size;
    
    DSU2D (int n, int m) : n(n), m(m) {
        for (int i = 0; i < n; i++) {
            vector<int> p, s;
            for (int j = 0; j < m; j++) {
                p.push_back(i*mult+j);
                s.push_back(1);
            }
            parent.push_back(p);
            size.push_back(s);
        }
    }

    int findSet(int i, int j) {
        if (i*mult+j == parent[i][j]) return i*mult+j;
        return parent[i][j] = findSet(parent[i][j]/mult, parent[i][j]%mult);
    }
    
    bool dsuUnion(int i1, int j1, int i2, int j2) {
        int a = findSet(i1,j1);
        int b = findSet(i2,j2);
        i1 = a/mult;
        j1 = a%mult;
        i2 = b/mult;
        j2 = b%mult;
        if (a == b) { 			// already grouped
            return false;
        }
        
        if (size[i1][j1] < size[i2][j2]) {
            parent[i1][j1] = b;
            size[i2][j2] += size[i1][j1];
        }
        else {
            parent[i2][j2] = a;
            size[i1][j1] += size[i2][j2];
        }
        return true;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}
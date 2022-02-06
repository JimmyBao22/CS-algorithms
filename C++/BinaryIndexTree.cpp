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

struct BIT {
    int n;
    vector<ll> f;   // 1 base indexing
    
    BIT (int nn) {
        n = nn;
        f.resize(n+1, 0);
    }

    // sum from i to 0
    ll sum (int i) {		
        i++;
        ll ret=0;
        while (i>0) {
            ret += f[i];
            i -= i&-i;
        }
        return ret;
    }

    // sum from l to r
    ll sum (int l, int r) {	
        return sum(r) - sum(l-1);
    }
    
    // add value to index i
    void add(int i, ll value) {	
        i++;
        while (i<=n) {
            f[i] += value;
            i += i&-i;
        }
    }

    // add value to indices l to r --> arr[i] = sum(i);
    void range_add(int l, int r, ll value) {	
        add(l,value); add(r+1,-value);
    }
};

const int MaxM = 1e3+10;

struct BIT2D {
    int n,m;
    vector<vector<ll>> f;
    ll f[MaxN][MaxM];		// 1 base indexing
    
    BIT2D (int nn, int mm) {
        n = nn; m = mm;
        for (int i=0; i<n; i++) {
            vector<ll> ff(m+1, 0);
            f.pb(ff);
        }
    }

    // sum from (i,j) to (0,0)
    ll sum (int i, int j) {
        i++; j++;
        ll ret=0;
        while (i>0) {
            int y=j;
            while (y>0) {
                ret += f[i][y];
                y -= y&-y;
            }
            i -= i&-i;
        }
        return ret;
    }
    
    // sum from (i1, j1) to (i2, j2)
    ll sum(int i1, int j1, int i2, int j2) {
        return sum(i2, j2) - sum(i1-1, j2) - sum(i2, j1-1) + sum(i1-1, j1-1);
    }
    
    // add value to (i,j)
    void add(int i, int j, ll val) {
        i++; j++;
        while (i<=n) {
            int y=j;
            while (y<=m) {
                f[i][y] += val;
                y += y&-y;
            }
            i += i&-i;
        }
    }
    
    // add value from (i1, j1) to (i2, j2) --> arr[i][j] = sum(i, j);
    void range_add(int i1, int j1, int i2, int j2, ll val) {
        add(i1, j1, val);
        add(i1, j2+1, -val);
        add(i2+1, j1, -val);
        add(i2+1, j2+1, val);
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    

}
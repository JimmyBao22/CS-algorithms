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
ll arr[MaxN];

struct SparsetableRMQ {
    
    int len, n;
    vector<vector<ll>> st;
    
    SparsetableRMQ (int x) {
        len = 31; n = x;
        st.assign(len, vector<ll>(n));
        
        for (int i=0; i<n; i++) {
            st[0][i] = arr[i];
        }
        
        for (int log=1; (1 << log)<=n; log++) {
            for (int i=0; i + (1 << log) < n+1; i++) {
                st[log][i] = min(st[log-1][i], st[log-1][i + (1 << (log-1))]);
            }
        }
    }
    
    // query l to r
    ll query(int l, int r) {
        int j = log2(r - l + 1);
        return min(st[j][l], st[j][r - (1 << j) + 1]);
    }
};

struct Sparsetable {
    int len, n;
    vector<vector<ll>> st;
    
    Sparsetable (int x) {
        len = 31; n = x;
        st.assign(len, vector<ll>(n));

        for (int i=0; i<n; i++) {
            st[0][i] = arr[i];
        }
        
        for (int log=1; (1 << log)<=n; log++) {
            for (int i=0; i + (1 << log) < n+1; i++) {
                st[log][i] = st[log-1][i] + st[log-1][i + (1 << (log-1))];
            }
        }
    }
    
    // query l to r
    ll query(int l, int r) {
        long sum=0;
        for (int log=len; log>=0; log--) {
            if ((1 << log) <= r - l + 1) {
                sum += st[log][l];
                l += (1 << log);
            }
        }
        return sum;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}
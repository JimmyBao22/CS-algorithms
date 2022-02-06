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

const int MaxN = 1e3+10, MaxM = 1e3+10;
int dp[MaxN][MaxM];
int n, m;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    string a, b;
    cin >> a >> b;
    n = a.length();
    m = b.length();
    if (a[0] == b[0]) dp[0][0] = 1;
    for (int i=1; i<n; i++) {
        if (a[i] == b[0]) dp[i][0] = 1;
        else dp[i][0] = dp[i-1][0];
    }
    for (int i=1; i<m; i++) {
        if (a[0] == b[i]) dp[0][i] = 1;
        else dp[0][i] = dp[0][i-1];
    }
    for (int i=1; i<n; i++) {
        for (int j=1; j<m; j++) {
            if (a[i] == b[j]) {
                dp[i][j] = dp[i-1][j-1]+1;
            }
            dp[i][j] = max(dp[i][j], max(dp[i-1][j], dp[i][j-1]));
        }
    }

    int ans=0;
    for (int i=0; i<n; i++ ) {
        for (int j=0; j<m; j++) {
            ans = max(ans, dp[i][j]);
        }
    }
}

static int memo[MaxN][MaxM];

int recdp(string a, string b, int posa, int posb) {
    if (posa == n || posb == m) return 0;
    if (memo[posa][posb] != -1) return memo[posa][posb];
    int ans=0;
    if (a[posa] == b[posb]) {
        ans = recdp(a, b, posa+1, posb+1) + 1;
    }
    ans = max(ans, max(recdp(a, b, posa+1, posb), recdp(a, b, posa, posb+1)));
    return memo[posa][posb] = ans;
}

struct A {
    int val=0;
    int x; int y;		// where you are coming from
    int curx; int cury;
    A () {}
    A (int a, int b, int c) {
        val = a; x = b; y = c;
    }
    A (int a, int b, int c, int d, int e) {
        val = a; x = b; y = c; curx = d; cury = e;
    }
    void print() {
        cout << val << " " << x << " " << y << "\n";
    }
};

A dp2[MaxN][MaxM];

int find() {
    string a, b;
    cin >> a >> b;
    n = a.length();
    m = b.length();
    if (a[0] == b[0]) dp2[0][0] = A(1, 0, 0);
    else dp2[0][0] = A(0,0,0);
    
    for (int i=1; i<n; i++) {
        if (a[i] == b[0]) dp2[i][0] = A(1, i, 0);
        else dp2[i][0] = A(dp2[i-1][0].val, i-1, 0);
    }
    for (int i=1; i<m; i++) {
        if (a[0] == b[i]) dp2[0][i] = A(1, 0, i);
        else dp2[0][i] = A(dp2[0][i-1].val, 0, i-1);
    }
    
    for (int i=1; i<n; i++) {
        for (int j=1; j<m; j++) {
            if (a[i] == b[j]) {
                dp2[i][j] = A(dp2[i-1][j-1].val+1, i-1, j-1);
            }
            if (dp2[i-1][j].val > dp2[i][j].val) {
                dp2[i][j] = A(dp2[i-1][j].val, i-1, j);
            }
            if (dp2[i][j-1].val > dp2[i][j].val) {
                dp2[i][j] = A(dp2[i][j-1].val, i, j-1);
            }
        }
    }
    
    int ans=0;
    for (int i=0; i<n; i++ ) {
        for (int j=0; j<m; j++) {
            ans = max(ans, dp2[i][j].val);
        }
    }

    if (ans == 0) {
        cout << "";
        return 0;
    }
    
    A cur = A(0,0,0);
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            if (dp2[i][j].val == ans) {
                cur = A(dp2[i][j].val, dp2[i][j].x, dp2[i][j].y, i, j);
            }
        }
    }

    vector<char> f;
    while (true) {
        
        if (cur.x+1 == cur.curx && cur.y + 1 == cur.cury) {
            // came from a diagonal
            f.push_back(a[cur.curx]);
        }
        
        if (dp2[cur.curx][cur.cury].x == cur.curx && dp2[cur.curx][cur.cury].y == cur.cury) {
            break;
        }
        cur = A(dp2[cur.x][cur.y].val, dp2[cur.x][cur.y].x, dp2[cur.x][cur.y].y, cur.x, cur.y);
    }
    
    if (cur.val == 1) {
        f.push_back(a[cur.curx]);
    }
    
    for (int i=f.size()-1; i>=0; i--) {
        cout << f[i];
    }

    return 0;
}
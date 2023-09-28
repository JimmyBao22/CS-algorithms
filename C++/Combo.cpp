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

const int MaxN = 1e4+10;
const int MaxK = 1e4+10;
const ll mod = 1e9+7;
ll choose[MaxN][MaxK];

ll fact[MaxN], invFact[MaxN];

void build() {
    for (int i = 0; i < MaxN; i++) {
        for (int j = 0; j < MaxK && j <= i; j++) {
            if (j == 0 || j == i) {
                choose[i][j] = 1;
            }
            else {
                choose[i][j] = choose[i-1][j-1] + choose[i-1][j];
                choose[i][j] %= mod;
            }
        }
    }
}

    // top! / bottom! (top - bottom)!
ll C(int top, int bottom) {
    return fact[top] * invFact[bottom] % mod * invFact[top - bottom] % mod;
}

ll pow(ll a, ll b, ll m) {
    ll ans = 1;
    while (b > 0) {
        if ((b & 1) == 1) {
            ans *= a;
            ans %= m;
        }
        a *= a;
        a %= m;
        b >>= 1;
    }
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    fact[0] = invFact[0] = 1;
    for (int i=1; i<MaxN; i++) {
        fact[i] = fact[i-1] * i;
        fact[i] %= mod;
        invFact[i] = pow(fact[i], mod-2, mod);
    }
    
}
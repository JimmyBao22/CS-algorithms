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

const int MaxN = 1e5 + 1;
ll inv_power[MaxN], power[MaxN], mod = 1e9+7, pref[MaxN];
ll p = 911382323, p1 = 972663749;

void calc_power0() {
    power[0] = 1;
    for (int i=1; i<MaxN; i++) {
        power[i] = power[i-1] * p;
    }
}

void prefHash0(string s) {
    if (s.length() > 0) pref[0] = s[0] - 'a' + 1;
    for (int i=1; i<s.length(); i++) {
        pref[i] = pref[i-1]*p + (s[i] - 'a' + 1);
    }
}

ll SubstringHash0(int i, int j) {
    if (i != 0) {
        return pref[j] - (pref[i-1] * power[j - i + 1]);
    }
    else {
        return pref[j];
    }
}

void calc_power1() {
    power[0] = 1;
    for (int i=1; i<MaxN; i++) {
        power[i] = power[i-1] * p;
        power[i] %= mod;
    }
}

void prefHash1(string s) {
    if (s.length() > 0) pref[0] = s[0] - 'a' + 1;
    for (int i=1; i<(int)s.length(); i++) {
        pref[i] = (pref[i-1]*p + (s[i] - 'a' + 1))%mod;
    }
}

    // hash of substring from i to j
    // pref is array calculated in prefhash. Therefore only use this if you need
        // to calculate hash of a lot of substrings
ll SubstringHash1(int i, int j) {
    if (i != 0) {
        return (((pref[j] - pref[i-1] * power[j - i + 1])%mod+mod)%mod);
    }
    else {
        return pref[j];
    }
}


ll pow(ll a, ll b, ll m) {
    ll ans=1;
    while (b > 0) {
        if (b%2 == 1) {
            ans *= a;
            ans %= m;
        }
        a *= a;
        a %= m;
        b >>= 1;
    }
    return ans;
}

void calc_power2() {
    power[0] = inv_power[0] = 1;
    for (int i=1; i<MaxN; i++) {
        power[i] = power[i-1] * p;
        power[i] %= mod;
        inv_power[i] = pow(power[i], mod-2, mod);
    }
}

void prefHash2(string s) {
    if (s.length() > 0) pref[0] = s[0] - 'a' + 1;
    for (int i=1; i<(int)s.length(); i++) {
        pref[i] = (pref[i-1] + (s[i] - 'a' + 1) * power[i])%mod;
    }
}

    // hash of substring from i to j
    // pref is array calculated in prefhash. Therefore only use this if you need
        // to calculate hash of a lot of substrings
ll SubstringHash2(int i, int j) {
    if (i != 0) {
        return ((((pref[j] - pref[i-1])* inv_power[i])%mod+mod)%mod);
    }
    else {
        return (pref[j] * inv_power[i])%mod;
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // use the right ones together; either all '0', '1', or '2'



}
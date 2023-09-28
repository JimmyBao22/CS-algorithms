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

// a ^ b
ll pow(ll a, ll b) {
    ll ans = 1;
    while (b > 0) {
        if ((b & 1) == 1) {
            ans *= a;
        }
        a *= a;
        b >>= 1;
    }
    return ans;
}

// (a ^ b) % m
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

    
}
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

ll modInverse(ll a, ll m) {
    return pow(a, m - 2, m);
}

ll x,y;

ll gcdExtended(ll a, ll b) { 
    // Base Case 
    if (a == 0) { 
        x = 0; 
        y = 1;
        return b; 
    }

    ll gcd = gcdExtended(b%a, a); 
    ll x1=x, y1=y;  
    // Update x and y 
    x = y1 - (b/a) * x1; 
    y = x1; 

    return gcd; 
}

ll modInverse1(ll a, ll m) {         
    x=y=1;
    ll g = gcdExtended(a, m);
    if (g!=1) return -1; 	// if a and m are not coprime, there is not an inverse
    ll inverse = (x%m + m)%m;
    return inverse;
}

                    // x = remainders[0] mod mods[0]
                    // x = remainders[1] mod mods[1]
                    // x = remainders[2] mod mods[2] ... 
ll CRT(int m, ll remainders[], ll mods[]) {
    ll modAll = 1;
    for (int i=0; i<m; i++) modAll *= mods[i];
    
    ll ans=0;
    for (int i=0; i<m; i++) {
        ll x = modAll/mods[i];
                                        // use better modinverse if mods[i] not prime
        ans += remainders[i] * x * modInverse(x, mods[i]);
    }
    
    return ans;			// all ans + modAll * k for some k work
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}
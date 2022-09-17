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
int totient[MaxN];
vector<int> primes;

void buildtotient() {
    for (int i=1; i<MaxN; i++) {
        totient[i] = i;
    }
    for (int i=2; i<MaxN; i+=2) {
        totient[i] = totient[i]/2;
    }
    for (int i=3; i<MaxN; i+=2) {
        if (totient[i] == i) {
            for (int j= i; j < MaxN; j+= i) {
                totient[j] /= i; totient[j] *= (i-1);
            }
        }
    }
}

void getprimes() {
    for (int i=1; i<MaxN; i++) {
        totient[i] = i;
    }		
    primes.pb(2);
    for (int i=2; i<MaxN; i+=2) {
        totient[i] = totient[i]/2;
    }
    for (int i=3; i<MaxN; i+=2) {
        if (totient[i] == i) {
            primes.pb(i);
            for (int j= i; j < MaxN; j+= i) {
                totient[j] /= i; totient[j] *= (i-1);
            }
        }
    }
}

ll tot(ll n) {
    ll ret = 1;
    if (n%2 == 0) {
        n>>= 1;
        while (n%2 == 0) {
            n >>= 1;
            ret <<= 2;
        }
    }
    for (ll i = 3; i*i<=n; i+=2)  { 
        if (n%i==0) { 
            ret *= (i-1);
            n /= i;
            while (n%i==0) {
                ret *= i;
                n /= i;
            }
        } 
    }
    if (n!=1) {
        ret *= (n-1);
    }
    return ret;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    buildtotient();
    
}

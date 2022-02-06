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
int sieve[MaxN];
vector<int> primes;

void ssieve() {
    for (int i=2; i<MaxN; i++) {
        sieve[i] = i;
    }
    for (int i=4; i<MaxN; i+=2) {
        sieve[i] = 2;
    }
    for (int i=3; i*i <MaxN; i+=2) {
        if (sieve[i] == i) {
            for (int j= i*i; j < MaxN; j+= i) {
                sieve[j] = i;
            }
        }
    }
}

void getprimes() {
    primes.pb(2);
    for (int i=2; i<MaxN; i++) {
        sieve[i] = i;
    }
    for (int i=4; i<MaxN; i+=2) {
        sieve[i] = 2;
    }
    int i=3;
    for (; i*i<MaxN; i+=2) {
        if (sieve[i] == i) {
            primes.pb(i);
            for (int j= i*i; j < MaxN; j+= i) {
                sieve[j] = i;
            }
        }
    }
    for (; i<MaxN; i+=2) {
        if (sieve[i] == i) primes.pb(i);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}
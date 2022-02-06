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

ll gcd(ll a, ll b) { 
    if (b == 0) return a; 
    return gcd(b, a%b); 
} 

ll lcm(ll a, ll b) { 
    return a/gcd(a, b) * b; 
}

vector<int> getDivisors(int n) { 
    vector<int> a;
    for (int i = 1; i*i<=n; i++)  { 
        if (n%i==0) { 
            if (n/i == i) a.push_back(i);
            else {
                a.push_back(i);
                a.push_back(n/i);
            }
        } 
    } 
    return a;
}

vector<int> getDivisorsinOrder(int n) { 
    vector<int> a;
    for (int i = 1; i*i<=n; i++)  { 
        if (n%i==0) { 
            a.push_back(i);
        } 
    } 
    for (int i = a.size()-1; i >= 0; i--) {
        if (n/a[i] != a[i]) a.push_back(n/a[i]);
    }
    return a;
}

vector<int> getprimeDivisors(int n) { 
    vector<int> a;
    if (n%2 == 0) {
        a.push_back(2);
        while (n%2 == 0) n >>=1;
    }
    for (int i = 3; i*i<=n; i+=2)  { 
        if (n%i==0) { 
            a.push_back(i);
            while (n%i ==0) n/=i;
        } 
    }
    if (n!=1) a.push_back(n);
    return a;
}

bool prime(int n) {
    if (n<=1) return false;
    if (n<=3) return true;
    if (n%2==0 || n%3==0) return false;
    for (int i=5; i*i <= n; i+=6) {
        if (n%i==0 || n%(i+2)==0) return false;
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}
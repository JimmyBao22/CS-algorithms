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

    // O(n)
ll calculate(int n, ll k, ll arr[]) {
    ll ans=0;
    
        // one = left one, two = right one
    queue<ll> one, onemin, onemax, two, twomin, twomax;
    
    for (int i=0; i<n; i++) {
        two.push(arr[i]);
        if (twomin.size() > 0) twomin.push(min(arr[i], twomin.front()));
        else twomin.push(arr[i]);
        if (twomax.size() > 0) twomax.push(max(arr[i], twomax.front()));
        else twomax.push(arr[i]);
        
        if (one.empty()) {
            // move everything over to one
            while (!two.empty()) {
                one.push(two.front());
                two.pop();
                twomin.pop(); twomax.pop();
                if (onemin.size() > 0) onemin.push(min(one.front(), onemin.front()));
                else onemin.push(one.front());
                if (onemax.size() > 0) onemax.push(max(one.front(), onemax.front()));
                else onemax.push(one.front());
            }	
        }
        
        ll mn = onemin.front();
        if (twomin.size() > 0) mn = min(mn, twomin.front());
        ll mx = onemax.front();
        if (twomax.size() > 0) mx = max(mx, twomax.front());
        
        while (mx - mn > k) {
            one.pop();
            onemin.pop(); onemax.pop();
            
            if (one.empty()) {
                // move everything over to one
                while (!two.empty()) {
                    one.push(two.front());
                    two.pop();
                    twomin.pop(); twomax.pop();
                    if (onemin.size() > 0) onemin.push(min(one.front(), onemin.front()));
                    else onemin.push(one.front());
                    if (onemax.size() > 0) onemax.push(max(one.front(), onemax.front()));
                    else onemax.push(one.front());
                }
            }
            
            mn = onemin.front();
            if (twomin.size() > 0) mn = min(mn, twomin.front());
            mx = onemax.front();
            if (twomax.size() > 0) mx = max(mx, twomax.front());
        }
        
        ans = max(ans, (ll)(one.size() + two.size()));
    }
    return ans;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    
}